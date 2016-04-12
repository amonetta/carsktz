package com.kaitzen

import spock.lang.*

/**
 *
 */
class CarsRestControllerIntegrationSpec extends Specification {

    def carsRestController = new CarsRestController()

    def setup() {
    }

    def cleanup() {
    }

    void "test save valid cars"() {
        given: "Given parameters with no id"
        carsRestController.params.year = year
        carsRestController.params.model = model
        carsRestController.params.make = make
        carsRestController.params.plate = plate
        carsRestController.params.owner = owner

        when: "Call save via POST"
        carsRestController.request.method = "POST"
        carsRestController.save()

        then: "Response status 'Created'(200) and get created entity at json response"
        carsRestController.response.status == 201
        !carsRestController.response.json.id.equals(null)
        carsRestController.response.json.year.equals(year)
        carsRestController.response.json.model.equals(model)
        carsRestController.response.json.make.equals(make)
        carsRestController.response.json.plate.equals(plate)
        // Important! Use 'equals' because is safest way to compare, ex. when comparing 'json.field == null' cause error.
        carsRestController.response.json.owner.equals(owner)

        where:
        year | model        | make          | plate     | owner
        2000 | 'testModel1' | 'testMake1'   | 'AAA000'  | null
        1899 | 'testModel2' | 'testMake2'   | 'A001DMH' | null
    }

    void "test save invalid cars"() {
        given: "Given invalid combination of properties for new entity"
        carsRestController.params.year = year
        carsRestController.params.model = model
        carsRestController.params.make = make
        carsRestController.params.plate = plate
        carsRestController.params.owner = owner

        when: "Call save via POST"
        carsRestController.request.method = "POST"
        carsRestController.save()

        then: "Response statud 'Bad Request'(400)"
        carsRestController.response.status == 400

        where:
        year | model        | make          | plate     | owner
        2000 | 'testModel1' | 'testMake1'   | 'AAA00'   | null
        3000 | 'testModel2' | 'testMake2'   | 'A001DMH' | null
    }

    void "test delete cars"() {
        given:
        def id = new Car(year: year, model: model, make: make, plate: plate, owner: owner).save().id

        when: "Call method DELETE"
        carsRestController.params.id = id
        carsRestController.request.method = "DELETE"
        carsRestController.delete()

        then: "Response status 'OK'(200)"
        carsRestController.response.status == 200
        !Car.exists(id)

        where:
        year | model        | make          | plate     | owner
        2000 | 'testModel1' | 'testMake1'   | 'AAA000'  | null
        1899 | 'testModel2' | 'testMake2'   | 'A001DMH' | null
    }

    void "test update cars"() {
        given: "Given a new Car and a property changed"
        def params = new Car(year: year, model: model, make: make, plate: plate, owner: owner).save().properties
        params.put(updateField, updateValue)

        and:
        carsRestController.params.id = params.id
        carsRestController.params.year = params.year
        carsRestController.params.model = params.model
        carsRestController.params.make = params.make
        carsRestController.params.plate = params.plate
        carsRestController.params.owner = params.owner

        when: "Call Update via PUT"
        carsRestController.request.method = 'PUT'
        carsRestController.update()

        then: "Response status 'ok'(200) and get updated properties"
        carsRestController.response.status == 200
        carsRestController.response.json.id.toString().equals(params.id.toString())
        carsRestController.response.json.year.equals(params.year)
        carsRestController.response.json.model.equals(params.model)
        carsRestController.response.json.make.equals(params.make)
        carsRestController.response.json.plate.equals(params.plate)
        carsRestController.response.json.owner.equals(params.owner)

        where:
        year | model        | make          | plate     | owner | updateField   | updateValue
        2000 | 'testModel1' | 'testMake1'   | 'AAA000'  | null  | 'plate'       | 'AAA111'
        1899 | 'testModel2' | 'testMake2'   | 'A001DMH' | null  | 'make'        | 'TestMake2'
        1899 | 'testModel3' | 'testMake3'   | 'A001DMH' | null  | 'model'       | 'TestModel2'
        1899 | 'testModel4' | 'testMake4'   | 'A001DMH' | null  | 'year'        | 2010
    }
}