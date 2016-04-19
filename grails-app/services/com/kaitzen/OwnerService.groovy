package com.kaitzen

import grails.transaction.Transactional

@Transactional
class OwnerService {

    Owner addOwner(Owner owner) {
        if (owner.hasErrors()) {
            throw new OwnerException(message: "Invalid or empty owner", owner: owner)
        }

        owner.save(failOnError: true)
    }

    def searchOwner(params) {
        def criteria = Owner.createCriteria()
        if (params.max.toString().toUpperCase() == 'ALL') {
            params.max = null
        } else {
            params.max = Math.min(params.max ? params.int('max') : 20, 1000)
        }

        criteria.list(QueryTypeEnum.EXCLUDE.getQuery(params), max: params.max, offset: params.offset)
    }

    def getOwnerForCar(Long carId) {
        Car.findById(carId).owner
    }

    enum QueryTypeEnum {
        INCLUDE,
        EXCLUDE

        def getQuery(params) {
            def query
            switch (this) {
                case INCLUDE: query = {
                        or {
                            if (params.nombre)
                                like("nombre", '%' + params.nombre + '%')
                            if (params.apellido)
                                like("apellido", '%' + params.apellido + '%')
                            if (params.nacionalidad)
                                like("nacionalidad", '%' + params.nacionalidad + '%')
                            if (params.dni) {
                                def dniIni = params.dni.padRight(8, '0').toInteger()
                                def dniEnd = params.dni.padRight(8, '0').toInteger() + 10**(8 - params.dni.length()) - 1
                                between("dni", dniIni, dniEnd)
                            }
                        }
                    }
                    break
                default: query = {
                        and {
                            if (params.nombre) {
                                like("nombre", '%' + params.nombre + '%') }
                            if (params.apellido) {
                                like("apellido", '%' + params.apellido + '%') }
                            if (params.nacionalidad) {
                                like("nacionalidad", '%' + params.nacionalidad + '%') }
                            if (params.dni) {
                                def dniIni = params.dni.padRight(8, '0').toInteger()
                                def dniEnd = params.dni.padRight(8, '0').toInteger() + 10**(8 - params.dni.length()) - 1
                                between("dni", dniIni, dniEnd)
                            }
                        }
                    }
            }

            query
        }
    }
}

class OwnerException extends RuntimeException{ //Forces transaction to roll back if exception occur
    String message
    Car owner
}
