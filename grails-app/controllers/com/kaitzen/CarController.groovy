package com.kaitzen

//@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.rest.*

class CarController {

    static scaffold = false

    Set cars = []

    def carList = []

    def index() {
        [carList: carList]
    }

    def findCarsAjax() {
        def response

        withRest(url: 'http://localhost:8080/carsktz/carsRest/') {
            response = get(path: '/index', accept: ContentType.JSON)//, query: [year: params.year, make: params.make, model: params.model])
        }

        carList = response.json.cars
        render (template: 'carEntry', collection : carList, var:"car")
        //render (template: 'plainResponse', bean: response.json, var:"plainResponse")
    }
}