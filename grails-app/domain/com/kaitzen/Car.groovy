package com.kaitzen

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = "metaClass,class")
class Car {

    static MIN_YEAR = 1768

    Integer year
    String make
    String model
    String plate
    Owner owner

    static mapping = {
        table "VehicleModelYear"
    }

    static constraints = {
        year min: 1768, max: Calendar.getInstance().get(Calendar.YEAR), nullable: false
        make maxSize: 50, nullable: false, blank: false
        model maxSize: 50, nullable: false, blank: false
        plate matches: /([A-Z]{3})(\d{3})|((D|C|I|M|A)\d{3}(CP|DM|RX|AC|DC)[A-Z])/
        owner nullable: true
    }

    static searchable = true
}
