package com.kaitzen
import grails.converters.JSON

class OwnerRestController {
    static responseFormats = ["json","xml"]

    def ownerService

    def index() {
        def ownerList = ownerService.searchOwner(params)

        JSON.use('Owner') {
            respond ownerList
        }
    }

    def show(Integer id) {
        Owner owner = Owner.findById(id)
        JSON.use('Owner'){
            respond owner
        }
    }

    def save(Owner owner) {
        def newOwner = ownerService.addOwner(owner)
        if (newOwner) {
            respond newOwner, status: 201
        } else {
            respond owner, status: 404
        }
    }

    def update(Long id, Owner newOwner) {
        if (!newOwner.hasErrors()) {
            def owner = Owner.get(id)

            if (!owner) {
                respond message: "Not found", status: 404
                return
            }

            owner.properties = newOwner.properties
            owner.validate() && owner.save()


            respond owner
        }
        else {
            respond newOwner
        }
    }

    def delete(Long id) {
        def status
        def message

        if (Owner.exists(id)) {
            Owner.load(id).delete(failOnError: true)
            status = 200
            message="Owner with id ${id} was deleted"
        }
        else {
            message="Not Found"
            status = 404
        }

        respond message: message, status: status
    }
}
