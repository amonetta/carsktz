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
        respond carService.show(id)
    }

    def save(Car car) {
        if (car.hasErrors()) {
            respond car, status: 400
        } else {
            car = carService.save(car.id, car)
            respond car, status: 201
        }
    }

    def update(Integer id, Car car) {
        respond carService.update(id, car), status: 200
    }

    def delete(Integer id) {
        respond carService.delete(id), body: "Car with ID ${id} deleted", status: 200
    }

    /*def delete(Integer id) {
        if (!Car.exists(id)) {
            respond body: "Not found", status: 404
        } else {
            def car = Car.load(id)
            car.delete()
            respond car, body: "Car with ID ${id} deleted", status: 200
        }
    }*/
}
