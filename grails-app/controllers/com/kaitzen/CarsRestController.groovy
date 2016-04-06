package com.kaitzen

import com.kaitzen.Car

class CarsRestController {
    static responseFormats = ["json", "xml"]

    def carService

    /**
     * For simple search perform: GET /<baseurl>?from=InitialYear&to=EndYear&make=CarMaker&model=CarModel&max=all&sort=SortingProperty&order=(asc|desc)&offset=Offset
     * Every parameter is optional and no parameters provided returns available max 20 cars.
     * max = ([1-1000] | 'all') : Define maximum results returned.
     * offset: First element returned from query.
     * Sort = ('id'|'year'|'model'|'make'|'plate') : Property used to sort result set.
     * Order = ('asc'|'desc') : Default: 'desc'. Define order for sorting.
     * Any other parameter is ignored, only given parameters are valid.
     * @return selected cars.
     */
    def index() {
        if (params.per_page && !params.max)
            params.max = params.per_page
        def cars = carService.list(params)
        respond cars
    }

    def show(Integer id) {
        def car = carService.show(id)
        if (car && params.ownerId && car.owner.id != params.long("ownerId"))
            car = null
        if (car)
            respond car, status: 200
        else
            render status: 404, message: "Car #${id} not found"
    }

    def save(Car car) {
        // It's necessary to copy properties into a new entity to test for validation errors
        if (new Car(car.properties).hasErrors()) {
            render status: 400, errorMessage: "Car not saved"
        } else {
            def carResponse
            try {
                carResponse = carService.save(car.id, car, params.long("ownerId"))
            } catch (CarServiceException e) {
                render status: 500, errorMessage: "Car not saved"
            }
            if (carResponse)
                respond carResponse, status: 201

        }
    }

    def update(Integer id, Car car) {
        if (car.hasErrors())
            render status: 400, errorMessage: "Car has validation errors"
        else
            try {
                respond carService.update(id, car, params.long("ownerId")), status: 200
            } catch (CarServiceException e) {
                render status: e.status, message: e.message
            }
    }

    def delete(Integer id) {
        try {
            respond carService.delete(id, params.long("ownerId")), body: "Car with ID ${id} deleted", status: 200
        } catch (CarServiceException e) {
            render status: e.status, message: e.message
        }
    }
}
