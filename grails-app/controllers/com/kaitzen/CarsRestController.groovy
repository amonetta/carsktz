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
        def cars = carService.list(params);
        respond cars
    }

    def show(Integer id){
        def car = carService.show(id)
        if (car)
            respond car, status: 200
        else
            render status: 404, message: "Car #${id} not found"
    }

    def save(Car car) {
        if (car.hasErrors())
            respond car, status: 400
        else {
            def savedCar = carService.save(car.id, car)
            if (savedCar)
                respond savedCar, status: 201
            else
                render status: 500, message: "Car #${car.id} not saved"
        }
    }

    def update(Integer id, Car car) {
        if (car.hasErrors())
            respond car, status: 400
        else
            respond carService.update(id, car), status: 200
    }

    def delete(Integer id) {
        respond carService.delete(id), body: "Car with ID ${id} deleted", status: 200
    }
}
