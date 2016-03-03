package com.kaitzen

class Owner {

    Integer dni
    String nombre
    String apellido
    String nacionalidad

    static hasMany = [
            cars : Car
    ]

    static mapping = {
        table "owner"
        nombre column: 'name'
        apellido column: 'last_name'
        nacionalidad column: 'nationality'
    }

    static constraints = {
        dni unique: true, nullable: false, blank:false, min: 1, max: 150000000
        nombre nullable: false, blank: false
        apellido nullable: false, blank: false
    }
}
