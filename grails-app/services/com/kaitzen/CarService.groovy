package com.kaitzen

import grails.transaction.*

@Transactional
class CarService {

    @Transactional(readOnly = true)
    def list(Map params) {
        def criteria = Car.withCriteria {
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
        }
        return criteria.toArray()
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
