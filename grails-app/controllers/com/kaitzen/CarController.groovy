package com.kaitzen

//@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')
import wslite.rest.*

class CarController {

    Set cars = []

    def restClient = new RESTClient("http://localhost:8080/carsktz/car/api")

    def index() {
        def query = params.findAll {it.value && it.value != 'null'}
        def response = restClient.get(query: query, accept: ContentType.JSON)

        [tableModel: response.json]
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

    def save() {
        def car = new Car(params)

        if (car.hasErrors()) {
            respond status: 400
            return
        }

        def response = restClient.post(accept: ContentType.JSON) {
            type: ContentType.JSON
            charset "UTF-8"
            urlenc params
        }

        render (template: 'carEntry', bean: response.json, var: "car")
    }

    def update() {

        def response = restClient.put(path:"${params.id}", accept: ContentType.JSON) {
                type: ContentType.JSON
                charset "UTF-8"
                json params
            }

        render (template: 'carEntry', bean: response.json, var: "car")
    }

    def delete(Integer id) {
        if (!Car.exists(id)) {
            respond status: 404
            return
        }

        restClient.delete(path: "/${id}", accept: ContentType.JSON)

        respond status: 200
    }

    def findCarsAjax() {
        def query = params.findAll {it.value && it.value != 'null'}
        def response = restClient.get(query: query, accept: ContentType.JSON)

        render (template: 'carTable', model: response.json)
    }

    def getFromFor(Integer id) {
        def car = Car.get(id)

        if (car)
            render (template: 'editForm', bean: car, var: 'car')
        else
            render status: 404
    }
}