package com.kaitzen

import spock.lang.Shared
import spock.lang.Specification
import wslite.rest.ContentType
import wslite.rest.RESTClient

class CarsRestControllerFunctionalSpec extends Specification {
    @Shared def restClient = new RESTClient("http://localhost:8080/carsktz/car/api")

    def setup() {

    }

    void "GET a list of car with no filters"() {

        when:"Asking for a car"
        def response = restClient.get(
                path: "/1",
                accept: ContentType.JSON
        )

        then: "Return status code 200 and first 10 objects"
        response.statusCode == 200
        response.json.id == 1
        response.json.year == 1907
    }

    void "Testing a Post"() {

        when:"Posting a new car"
        def response = restClient.post(accept: ContentType.JSON){
            type: ContentType.JSON
            charset "UTF-8"
            json make: "Chevrolet", model: "Classic", year: "1995", plate: "ZZZ605", owner: 1
        }

        then: "Return status code 201 and id != null"
        response.statusCode == 201
        response.json.year == 1995
        response.json.model == "Classic"
        response.json.make == "Chevrolet"
        response.json.plate == "ZZZ605"

    }
}