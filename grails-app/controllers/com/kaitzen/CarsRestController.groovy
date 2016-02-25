package com.kaitzen

import com.kaitzen.Car

class CarsRestController {
    static responseFormats = ["json", "xml"]

    //def carService

    /**
     * For simple search perform: GET /<baseurl>?from=InitialYear&to=EndYear&make=CarMaker&model=CarModel
     * Every parameter is optional and no parameters provided returns available all cars.
     * Any other parameter is ignored, only 'from' 'to' 'make' 'model' are valid.
     * @return selected cars.
     */
    def index() {
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
            }
        }
        def cars = criteria.findAll().toArray()
        respond {cars: cars}
    }

    def show(Integer id){
        respond Car.get(id)
    }

    def save(Car car) {
        if (car.hasErrors()) {
            respond car, status:400
        } else {
            car = car.save(failOnError: true, flush: true)
            respond car, status:201
        }
    }

    def update(Integer id, Car car) {
        def oldCar = Car.get(id)
        if (!oldCar) {
            respond message: "Not found car", status: 404
        }
        oldCar.properties = car.properties
        oldCar.save()
        render car, status: 200
    }

    def delete(Integer id) {
        def body
        def status
        if (Car.exists(id)) {
            Car.load(id).delete()
            status = 200
            body = "Car with ID ${id} deleted"
        } else {
            status = 404
            body = "Not found"
        }

        render body: body, status: status
    }
}
