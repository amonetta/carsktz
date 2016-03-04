package com.kaitzen

import grails.transaction.Transactional

@Transactional
class OwnerService {

    Owner addOwner(Owner owner) {
        if (owner.hasErrors()) {
            throw new OwnerException(
                    message: "Invalid or empty owner", owner: owner)
        }
        else {
            owner.save(failOnError: true)
            return owner
        }
    }

    def searchOwner(params) {
        def query = Owner.createCriteria()
        def ownerList = query.list(max: 10) {
            and {
                if (params.nombre) {
                    like("nombre", params.nombre + '%')
                }
                if (params.apellido) {
                    and { like("apellido", params.apellido + '%') }
                }
                if (params.nacionalidad) {
                    and { like("nacionalidad", params.nacionalidad + '%') }
                }
                if (params.dni) {
                    def dniIni = params.dni.padRight(8, '0').toInteger()
                    def dniEnd = params.dni.padRight(8, '0').toInteger() + 10**(8 - params.dni.length()) - 1
                    between("dni", dniIni, dniEnd)
                }
            }
        }
        return ownerList
    }
}

class OwnerException extends RuntimeException{ //Forces transaction to roll back if exception occur
    String message
    Car owner
}
