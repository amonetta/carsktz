package com.kaitzen

import grails.transaction.*

@Transactional
class CarService {

    static def query = [
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
                                eq("dni", params.owner as Integer)
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
    def list(Map params) {
        def criteria = Car.createCriteria()
        if (params.max.toString().toUpperCase() == 'ALL')
            params.max = null
        else
            params.max = Math.min(params.max? params.int('max') : 20, 1000)

        if (params.page)
            params.offset = params.max * (params.max - 1)

        def cars = criteria.list(query.search(params), max: params.max, offset: params.offset)
        def filters = [from: params.from, to: params.to, make: params.make, model: params.model, plate: params.plate, max: params.max? params.max: cars.totalCount, sort: params.sort, order: params.order]

        def model = [cars: cars, carsTotal: cars.totalCount, filters: filters]

        return model
    }

    @Transactional(readOnly =true)
    def Car show(Integer id) {
        return Car.get(id)
    }

    def Car save(Integer id, Car newCar) {
        Car car = new Car(id:id)
        car.properties = newCar.properties
        return performSave(car)
    }

    def Car update(Integer id, Car updatedCar) {

        if (!updatedCar.validate())
            throw new Exception(message: "Car is invalid, check contrains")
        Car oldCar = Car.get(id)
        if (!oldCar)
            throw new Exception(message: "Car (id: ${id}) not found")
        oldCar.properties = updatedCar.properties
        return performSave(oldCar)
    }

    def Car delete(Integer id) {
        def car = Car.get(id)
        if (!car)
            throw new Exception("Car not found")
        car.delete()
        return car;
    }

    private performSave(Car car) {
        if (!(car.validate() && car.save()))
            throw new Exception(message: "Could not save car: ${newCar}")
        return car;
    }
}