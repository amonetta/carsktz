package com.kaitzen

import spock.lang.Shared
import spock.lang.Specification
import wslite.rest.ContentType
import wslite.rest.RESTClient

class CarsRestControllerFunctionalSpec extends Specification {

    def restClient = new RESTClient("http://localhost:8080/carsktz/car/api")

    def setup() {
    }

    def cleanup() {
    }

    void "GET a list of car with no filters"() {

        when:"Asking for a car"
        def response = restClient.get(
                path: "/${id}",
                accept: ContentType.JSON
        )

        then: "Return status code 200 and first 10 objects"
        response.statusCode == 200
        response.json.id.toString().equals(id.toString())
        response.json.year.equals(year)
        response.json.model.equals(model)
        response.json.make.equals(make)
        response.json.plate.equals(plate)

        where:
        id    | year | model            | make          | plate
        1     | 1909 | "Model T"        | "Ford"        | "AAA000"
        2804  | 1998 | "Intrigue"       | "Oldsmobile"  | ""
        3854  | 2002 | "Bravada"        | "Oldsmobile"  | ""
        5844  | 2008 | "Quattroporte"   | "Maserati"    | ""
        7245  | 2013 | "LX"             | "Lexus"       | ""
    }

    void "Testing save via POST"() {

        given:
        def params = [make: make, model: model, year: year, plate: plate, owner: owner]

        when:"Posting a new car"
        def response = restClient.post(accept: ContentType.JSON){
            type: ContentType.JSON
            charset "UTF-8"
            json params
        }

        then: "Return status code 201 and id != null"
        response.statusCode == 201
        response.json.year.equals(year)
        response.json.model.equals(model)
        response.json.make.equals(make)
        response.json.plate.equals(plate)
        //response.json.owner.id.toString().equals(owner.toString())

        where:
        year  | model           | make          | plate     | owner
        1995  | "Classic1"      | "Chevrolet1"  | "ZBZ680"  | 1
        1977  | "TestModel2"    | "TestMake2"   | "ABC123"  | 1
        1982  | "TestModel3"    | "TestMake3"   | "ACF321"  | null
        1799  | "TestModel4"    | "TestMake4"   | "HWO947"  | null
    }
}