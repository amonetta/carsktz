package com.kaitzen

import grails.transaction.*

@Transactional
class CarService {

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
                    if (params.owner)
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
                if (params.sort)
                    order(params.sort, params.order == 'desc' ? 'desc' : 'asc')
            }
        }
    ]

    @Transactional(readOnly = true)
    def list(Map params = [max: 20, offset: 0]) {
        def criteria = Car.createCriteria()
        if (params.max.toString().toUpperCase() == 'ALL')
            params.max = null
        else
            params.max = Math.min(params.max? params.int('max') : 20, 1000)

        def cars
        try {
            cars = criteria.list(query.search(params), max: params.max, offset: params.offset)
        } catch (Exception e) {
            throw new CarServiceException(cause: e, message: "Could not get list of cars, see nested exception", status: 500)
        }
        def filters = [from: params.from, to: params.to, make: params.make, model: params.model, plate: params.plate, max: params.max? params.max: cars.totalCount, sort: params.sort, order: params.order]

        def model = [cars: cars, carsTotal: cars.totalCount, filters: filters]

        return model
    }

    @Transactional(readOnly =true)
    def Car show(Long id) {
        return Car.get(id)
    }

    def Car save(Long id, Car newCar) {
        if (id)
            return update(id, newCar)
        else
            return performSave(new Car(newCar.properties))
    }

    def Car update(Long id, Car updatedCar) {

        if (!updatedCar.validate())
            throw new CarServiceException(status: 400,message: "Car is invalid, check contrains", car: updatedCar)
        Car oldCar = Car.get(id)
        if (!oldCar)
            throw new CarServiceException(status: 404, message: "Car (id: ${id}) not found")
        oldCar.properties = updatedCar.properties
        return performSave(oldCar)
    }

    def Car delete(Long id) {
        def car = Car.get(id)
        if (!car)
            throw new CarServiceException(status: 404, message:  "Car (id: ${id}) not found")
        car.delete()
        return car;
    }

    private performSave(Car car) {
        if (!(car.validate() && car.save()))
            throw new CarServiceException(status: 400, message: "Could not save car #${car.id}", car: car)
        return car;
    }
}

class CarServiceException extends RuntimeException {
    def Integer status
    def Car car
    def String message
}