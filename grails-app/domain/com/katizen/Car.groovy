package com.katizen

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
}
