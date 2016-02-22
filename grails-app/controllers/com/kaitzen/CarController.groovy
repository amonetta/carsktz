package com.kaitzen

//@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.rest.*

class CarController {

    static scaffold = false

    Set cars = []

    def index() {
        def response

        withRest(url: 'http://localhost:8080/carsktz/carsRest') {
            response = get(path: 'index', accept: ContentType.JSON)
        }

        return [cars: response.json.cars, plainResponse: response.json]
    }

    def findCarsAjax(CarSearchForm carSearchForm) {
        def allCars = []
        allCars = Car.where {
            year >= carSearchForm.yearFrom
            year <= carSearchForm.yearUntil
            make == carSearchForm.Make
            model == carSearchForm.Model
        }.list()
        render template: 'carEntry', collection: allCars, var: 'allCars'
    }
}

class CarSearchForm {
    Integer yearFrom, yearUntil
    String Make
    String Model

    static contrains = {
        yearFrom max: yearUntil
    }
}