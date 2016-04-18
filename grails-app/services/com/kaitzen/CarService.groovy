package com.kaitzen

import grails.transaction.*
import grails.converters.JSON

import java.security.MessageDigest

@Transactional
class CarService {

    def memcachedService
    def redisService

    private static final query = [
        search: { params -> return {
                and {
                    if (params.from && params.from.toString().isInteger())
                        ge("year", params.from as Integer)
                    if (params.to && params.to.toString().isInteger())
                        le("year", params.to as Integer)
                    if (params.make)
                        like("make", '%' + params.make + '%')
                    if (params.model)
                        like("model", '%' + params.model + '%')
                    if (params.plate)
                        like("plate", '%' + params.plate + '%')
                    if (params.owner) {
                        owner {
                            if (params.owner.toString().isInteger())
                                sqlRestriction("('' + dni) LIKE '%${params.owner}%'")
                            else
                                or {
                                    like("apellido", '%' + params.owner.trim() + '%')
                                    like("nombre", '%' + params.owner.trim() + '%')
                                }
                        }
                    }
                    if (params.ownerId) {
                        owner {
                            eq("id", params.long('ownerId'))
                        }
                    }
                }
                if (params.sort)
                    order(params.sort, params.order == 'desc' ? 'desc' : 'asc')
            }
        }
    ]

    @Transactional(readOnly = false)
    def list(Map params = [max: 20, offset: 0]) {
        if (params.max.toString().toUpperCase() == 'ALL')
            params.max = null
        else
            params.max = Math.min(params.max? params.int('max') : 20, 1000)

        def filters = [from: params.from, to: params.to, make: params.make, model: params.model, plate: params.plate, owner: params.owner, ownerId: params.ownerId, max: params.max, offset: params.offset, sort: params.sort, order: params.order]

        MessageDigest md = MessageDigest.getInstance("SHA")
        md.update(filters.toString().getBytes('UTF-8'))
        def key = md.digest().encodeBase64().encodeAsSHA1()

        println "access key: $key"

        def cachedModel = memcachedService.get(key) {
            println "Looking into database (filtersKey: $key)"
            def criteria = Car.createCriteria()
            def carsList

            try {
                carsList = criteria.list(query.search(params), max: filters.max, offset: filters.offset)
            } catch (Exception e) {
                throw new CarServiceException(message: "Could not get list of cars, see nested exception", status: 500)
            }

            def serializableList = new ArrayList(filters.max)

            carsList.each {
                serializableList.add([id: car.id] + car.properties.findAll { k, v -> k != 'class'  && !(k =~ /.+Id/) && k != 'owner'} + [owner: car.owner? [
                        id: car.owner.id,
                        nombre: car.owner.nombre,
                        apellido: car.owner.apellido
                ] : null])
            }

            def carsKeyList = memcachedService.get(":carsKeyList") ?: []
            carsKeyList.add(key)
            memcachedService.set(":carsKeyList", carsKeyList)

            def model = [cars: serializableList, carsTotal: carsList.totalCount, filters: filters]

            return model
        }

        return cachedModel
    }

    //@Transactional(readOnly = true)
    def Car show(Long id) {
        def car = cacheRedisGet(id)
        if (car)
            return car
        car = Car.get(id)
        if (!car)
            return null
        cacheRedisSet(car)
        return car
    }

    def Car save(Long id, Car newCar, Long ownerId = null) {
        if (ownerId)
            newCar.owner = Owner.get(ownerId)
        if (id)
            return update(id, newCar)
        else
            return performSave(new Car(newCar.properties))
    }

    def Car update(Long id, Car updatedCar, Long ownerId = null) {

        if (!updatedCar.validate())
            throw new CarServiceException(status: 400,message: "Car is invalid, check contrains", car: updatedCar)
        Car oldCar = Car.get(id)
        if (!oldCar)
            throw new CarServiceException(status: 404, message: "Car (id: ${id}) not found")
        oldCar.properties = updatedCar.properties
        if(ownerId)
            oldCar.owner = Owner.get(ownerId)
        return performSave(oldCar)
    }

    def Car delete(Long id, Long ownerId = null) {
        def car = Car.get(id)
        if (!car)
            throw new CarServiceException(status: 404, message:  "Car (id: ${id}) not found")
        if (ownerId) {
            if (car.owner.id == owner.id) {
                car.owner = null
                car.save(failOnError: true)
            }
        } else {
            car.delete(failOnError: true)
        }
        cacheRedisDel(id)
        invalidateCached()
        return car;
    }

    private performSave(Car car) {
        if (!(car.validate() && car.save()))
            throw new CarServiceException(status: 400, message: "Could not save car #${car.id}", car: car)
        cacheRedisSet(car)
        invalidateCached()
        return car;
    }

    private cacheRedisSet(Car car) {
        println car.properties
        redisService.del("Car:${car.id}")
        redisService.memoizeHash("Car:${car.id}") { return [
                id    : car.id.toString(),
                year  : car.year.toString(),
                model : car.model,
                make  : car.make,
                plate : car.plate
        ] }
        redisService.hdel("Car:${car.id}", "ownerId")
        redisService.del("Car:${car.id}:Owner")
        if (car.owner) {
            redisService.memoizeHashField("Car:${car.id}", "ownerId") {[ ownerId : car.owner.id.toString() ]}
            redisService.memoizeHash("Car:${car.id}:Owner") { return [
                    id       : car.owner.id.toString(),
                    nombre   : car.owner.nombre,
                    apellido : car.owner.apellido
            ] }
        }
    }

    private cacheRedisGet(Long id) {
        def carMap = redisService.memoizeHash("Car:$id") {}
        if (!carMap)
            return null
        def car = new Car(carMap)
        car.id = Integer.parseInt(carMap.id)
        if (carMap.ownerId) {
            def ownerMap = redisService.memoizeHash("Car:${car.id}:Owner") {}
            car.owner = new Owner(ownerMap)
            car.owner.id = Integer.parseInt(ownerMap.id)
        }
        return car
    }

    private cacheRedisDel(Long id) {
        redisService.del("Car:${id}")
        redisService.del("Car:${id}:Owner")
    }

    private invalidateCached() {
        def carsKeyList = memcachedService.get(":carsKeyList")
        if (carsKeyList) {
            carsKeyList.each {
                memcachedService.delete(it)
            }
            memcachedService.delete(":carsKeyList")
        }
    }
}

class CarServiceException extends RuntimeException {
    def Integer status
    def Car car
    def String message
}