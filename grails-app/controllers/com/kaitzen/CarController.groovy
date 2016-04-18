package com.kaitzen

import wslite.rest.*

class CarController {

    private def restClient = new RESTClient("http://localhost:8080/carsktz/api/car")

    def index() {
        if (params.page && params.page.toString().isInteger()) {
            def newParams = params.findAll {it.key != 'page'}
            if (params.per_page)
                params.max = params.int("per_page")
            params.offset = (params.max? params.int('max') : 20) * (params.int('page') - 1)
            //redirect(params: newParams)
        }
        def query = params.findAll {it.value && it.value != 'null'}
        def response = restClient.get(query: query, accept: ContentType.JSON)

        render (view: "index", model: [tableModel: response.json])
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
            render status: 400
            return
        }

        def response = restClient.post(accept: ContentType.JSON) {
            type: ContentType.JSON
            charset "UTF-8"
            json params
        }

        render (template: 'carEntry', bean: response.json, var: "car")
    }

    def update(Long id) {

        def response = restClient.put(path:"${id}", accept: ContentType.JSON) {
                type: ContentType.JSON
                charset "UTF-8"
                json params
            }

        render (template: 'carEntry', bean: response.json, var: "car")
    }

    def delete(Long id) {
        println id;
        def response = restClient.delete(path: "/${id}", accept: ContentType.JSON)

        render status: response.statusCode
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