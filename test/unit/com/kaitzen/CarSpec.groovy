package com.kaitzen

import com.kaitzen.Car
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Car)
class CarSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test all constrains"() {
        when:
        def car = new Car("$field": val)

        then:
        validateConstraints(car, field, errorCode)

        where:
        errorCode           | field     | val
        'max.exceeded'      | 'year'    | Calendar.getInstance().get(Calendar.YEAR) + 1
        'min.notmet'        | 'year'    | 1757
        'nullable'          | 'year'    | null
        'nullable'          | 'model'   | ''
        'nullable'          | 'model'   | null
        'nullable'          | 'make'    | ''
        'nullable'          | 'make'    | null
        'matches.invalid'   | 'plate'   | 'AAA00'
        'nullable'          | 'plate'   | ''
        'nullable'          | 'plate'   | null
    }

    void validateConstraints(obj, field, error) {
        def validated = obj.validate()
        if (error && error != 'valid') {
            assert !validated
            assert obj.errors[field]
            assert error == obj.errors[field].code
        } else {
            assert !obj.errors[field]
        }
    }
}
