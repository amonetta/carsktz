package com.kaitzen

class OwnerRestController {
    static responseFormats = ["json","xml"]

    def ownerService

    def index() {
        respond params.carId ? ownerService.getOwnerForCar(params.long("carId")) : ownerService.searchOwner(params)
    }

    def show(Integer id) {
        Owner owner = Owner.findById(id)
        respond owner
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
        if (newOwner.hasErrors()) {
            respond message: "Error on owner data", status: 400
        } else {
            def owner = Owner.get(id)
            if (!owner) {
                respond message: "Not found", status: 404
                return
            }
            owner.properties = newOwner.properties
            owner.save(failOnError: true)
            respond owner
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
