package com.kaitzen

import com.kaitzen.Car

class CarsRestController {
    static responseFormats = ["json", "xml"]

    //def carService

    def list() {
        def query = {
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
            if (params.sort && params.order in ['asc', 'desc'])
                order(params.sort, params.order)
        }

        def criteria = Car.createCriteria()
        if (params.max.toString().toUpperCase() == 'ALL')
            params.max = null
        else
            params.max = Math.min(params.max? params.int('max') : 20, 1000)
        def cars = criteria.list(query, max: params.max, offset: params.offset)
        def filters = [from: params.from, to: params.to, make: params.make, model: params.model, plate: params.plate, max: params.max? params.max: cars.totalCount, sort: params.sort, order: params.order]

        def model = [cars: cars, carsTotal: cars.totalCount, filters: filters]

        respond model
    }

    /**
     * For simple search perform: GET /<baseurl>?from=InitialYear&to=EndYear&make=CarMaker&model=CarModel
     * Every parameter is optional and no parameters provided returns available all cars.
     * Any other parameter is ignored, only 'from' 'to' 'make' 'model' are valid.
     * @return selected cars.
     */
    def index() {
        def cars = list();
        respond cars
    }

    def show(Integer id){
        respond Car.get(id)
    }

    def save(Car car) {
        if (car.hasErrors()) {
            respond car, status:400
        } else {
            car = car.save(failOnError: true)
            respond car, status:201
        }
    }

    def update(Integer id, Car car) {
        if (!Car.exists(id)) {
            respond message: "Not found car", status: 404
        }

        car.id = id;
        if (car.validate() && car.save(failOnError: true))
            respond car, status: 200
        else
            respond status: 406
    }

    def delete(Integer id) {
        if (!Car.exists(id)) {
            render body: "Not found", status: 404
        } else {
            def car = Car.load(id)
            car.delete()
            respond car, body: "Car with ID ${id} deleted", status: 200
        }
    }
}
