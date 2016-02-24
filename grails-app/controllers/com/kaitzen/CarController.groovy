package com.kaitzen

//@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.rest.*

class CarController {

    Set cars = []

    def restClient = new RESTClient("http://localhost:8080/carsktz/car/api")

    def index() {
        def response = restClient.get(path: '', accept: ContentType.JSON)

        [carList: response.json.cars]
    }

    def edit(Integer id) {
        def response = restClient.get(path: "/${id}", accept: ContentType.JSON)

        if (!response) {
            respond status: 404
        }

        [ carInstance: response.json ]
    }

    def create() {
        return [carInstance: new Car()]
    }

    def save(Car car) {
        if (car.hasErrors()) {
            respond status: 400
            return
        }

        restClient.post() {
            type: ContentType.JSON
            charset "UTF-8"
            urlenc year: car.year, make: car.make, model: car.model
        }
        return
    }

    def update() {
        def car = new Car(params)

        if (car.hasErrors()) {
            respond status: 400
            return
        }

        def response = restClient.post(path:"/${params.id}", accept: ContentType.JSON) {
                type: ContentType.JSON
                charset "UTF-8"
                urlenc id: params.id, year: params.year, make: params.make, model: params.model
            }

        render (template: 'carCols', bean: response.json, var: "car")
    }

    def delete(Integer id) {
        if (!Car.exists(id)) {
            respond status: 404
            return
        }

        restClient.delete(path: "/${id}", accept: ContentType.JSON)

        //respond status: 200
        render status: 200
    }

    def findCarsAjax() {
        def response = restClient.get(path: '', accept: ContentType.JSON, query: [from: params.carFrom, to: params.carTo, make: params.carMake, model: params.carModel])

        def carList = response.json.cars
        render (template: 'carEntry', collection : carList, var:"car")
    }
}