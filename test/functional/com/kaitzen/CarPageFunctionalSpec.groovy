package com.kaitzen

import com.kaitzen.pages.*
import geb.spock.GebReportingSpec;

/**
 * Created by amonetta on 07/04/16.
 */
public class CarPageFunctionalSpec extends GebReportingSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "Test load index"() {
        when:
        to CarPage

        then:
        carTable.carCount        == 20
        carTable.cars(0)
        carTable.cars(0).id      == 1
        carTable.cars(0).year    == 1909
        carTable.cars(0).model   == "Ford"
        carTable.cars(0).make    == "Model T"
        carTable.cars(0).plate   == "AAA000"
        carTable.cars(1)
        carTable.cars(1).id      == 2
        carTable.cars(19)
        carTable.cars(19).id     == 20
    }

    void "Test form search"() {
        given:
        to CarPage

        when:
        searchForm.btoggle.click()
        if (from)
            searchForm.from.value( from )
        if (to)
            searchForm.to.value( to )
        searchForm.bsubmit.click()

        then:
        waitFor {
            from ? carTable.cars(0).year                     >= from : true
            from ? carTable.cars(carTable.carCount - 1).year >= from : true
            to   ? carTable.cars(0).year                     <= to   : true
            to   ? carTable.cars(carTable.carCount - 1).year <= to   : true
        }

        where:
        from | to
        1950 | null
        1980 | null
        2000 | null
        null | 1950
        null | 1980
        null | 2000
        1950 | 1980
        1950 | 2000
        1980 | 2000
    }

    void "Test form search last results"() {
        given:
        to CarPage

        when:
        searchForm.btoggle.click()
        if (from)
            searchForm.from.value( from )
        if (to)
            searchForm.to.value( to )
        searchForm.bsubmit.click()
        searchForm.btoggle.click()
        waitFor("quick") { carTable.pagination }
        def stepText
        if (carTable.pagination.totalSteps > 0) {
            stepText = carTable.pagination.lastStep.text()
            carTable.pagination.lastStep.click()
        }

        then:
        waitFor {
            carTable.pagination.totalSteps > 0 ? carTable.pagination.actualStep.text().equals(stepText) : true
            from ? carTable.cars(0).year                     >= from : true
            from ? carTable.cars(carTable.carCount - 1).year >= from : true
            to   ? carTable.cars(0).year                     <= to   : true
            to   ? carTable.cars(carTable.carCount - 1).year <= to   : true
        }

        where:
        from | to
        1950 | null
        1980 | null
        2000 | null
        null | 1950
        null | 1980
        null | 2000
        1950 | 1980
        1950 | 2000
        1980 | 2000
    }

    void "Test navigation by page parameter"() {
        when:
        to CarPage, page: 2

        then:
        carTable.cars(0).id == 21
        carTable.cars(carTable.carCount - 1).id == 40
    }

    void "Test open edit dialog"() {
        given:
        to CarPage

        when:
        carTable.cars(0).click()

        then:
        waitFor {
            carForm != null
            carForm.title.equals("Edit Car")
            carForm.action.equals("http://localhost:8080/carsktz/car/update/")
            carForm.year.value().equals("1909")
            carForm.model.value().equals("Model T")
            carForm.make.value().equals("Ford")
            carForm.plate.value().equals("AAA000")
            carForm.ownerId.value().equals("1")
            carForm.ownerDescription.value().equals("Monetta, Agustín Ignacio")
        }
    }
}
