package com.kaitzen

import com.kaitzen.Car

class CarsRestController {
    static responseFormats = ["json", "xml"]

    def carService

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
        def cars = criteria.findAll().toArray()
        // respond {cars: carService.list(params)}
        respond {cars: cars}
    }

    def show(Integer id){
        respond Car.get(id)
    }

    def save(Car car) {
        if (car.hasErrors()) {
            respond car, status:400
        } else {
            car = carService.save(car)
            respond car, status:201
        }
    }

    def update(Integer id, Car car) {
        if (!Car.exists(id)) {
            respond message: "Not found car", status: 404
        }

        respond carService.update(id,car), status: 200
    }

    def delete(Integer id) {
        def body
        def status
        if (!Car.exists(id)) {
            render body: "Not found", status: 404
        } else {
            body = "Car with ID ${id} deleted"
            respond carService.delete(id), body: "Car with ID ${id} deleted", status: 200
        }
    }
}
