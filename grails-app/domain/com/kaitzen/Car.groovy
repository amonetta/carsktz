package com.kaitzen

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = "metaClass,class")
class Car {

    Integer year
    String make
    String model

    static mapping = {
        table "VehicleModelYear"
    }

    static constraints = {
        year min: 1768, max: Calendar.getInstance().get(Calendar.YEAR);
        make maxSize: 50
        model maxSize: 50
    }

    static searchable = true
}
