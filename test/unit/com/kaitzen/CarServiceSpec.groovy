package com.kaitzen

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CarService)
@Mock(Car)
class CarServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test save a car with auto-generated ID"() {
        given:
        def newCar = new Car(year: year, model: model, make: make, plate: plate, owner: owner)

        when:
        def car = service.save(null, newCar)

        then:
        car.id != null
        car.year == year
        car.model == model
        car.make == make
        car.plate == plate
        car.owner == owner

        where:
        year | model        | make        | plate       | owner
        2000 | 'testModel0' | 'testMake0' | 'BBB111'    | null
        2001 | 'testModel0' | 'testMake2' | 'D001CPA'   | null
        2002 | 'testModel1' | 'testMake1' | 'A002CPA'   | null
    }

    void "Test fail saves"() {
        given:
        def newCar = new Car(year: year, model: model, make: make, plate: plate, owner: owner)

        when:
        service.save(null, newCar)

        then:
        thrown CarServiceException

        where:
        year | model        | make        | plate       | owner
        2010 | 'testModel1' | 'testMake1' | 'AAA01'     | null
        1757 | 'testModel6' | 'testMake6' | 'AAA000'    | null
        1999 | ''           | 'testMake6' | 'AAA000'    | null
        1998 | 'testModel6' | ''          | 'AAA000'    | null
        2002 | 'testModel1' | 'testMake3' | 'B002CPA'   | null
    }

    void "Test retrieve car"() {
        setup:
        def testId = new Car(year: year, model: model, make: make, plate: plate, owner: owner).save().id

        when:
        def car = service.show(testId)

        then:
        car.id      == testId
        car.year    == year
        car.model   == model
        car.make    == make
        car.plate   == plate
        car.owner   == owner

        where:
        year | model        | make        | plate       | owner
        1989 | 'testModel2' | 'testMake2' | 'CCC222'    | null
        2010 | 'testModel3' | 'testMake3' | 'AAA010'    | null
    }

    void "Test delete car"() {
        setup:
        def deleteId = new Car(year: year, model: model, make: make, plate: plate, owner: owner).save().id

        when:
        def car = service.delete(deleteId)

        then:
        car.id      == deleteId
        car.year    == year
        car.model   == model
        car.make    == make
        car.plate   == plate
        car.owner   == owner

        where:
        year | model        | make        | plate       | owner
        1898 | 'testModel4' | 'testMake4' | 'DDD333'    | null
        2015 | 'testModel5' | 'testMake5' | 'ABC015'    | null
    }
}
