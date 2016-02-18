package com.kaitzen

class CarController {

    static scaffold = true

    Set cars = []

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