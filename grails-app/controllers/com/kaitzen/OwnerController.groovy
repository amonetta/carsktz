package com.kaitzen

import wslite.rest.ContentType
import wslite.rest.RESTClient

class OwnerController {

    static scaffold = true

    def restClient = new RESTClient("http://localhost:8080/carsktz/owner/api")

    def index() {}

    def autocomplete() {
        println "reach autocomplete, params: " + params

        def response = restClient.get(query: [nombre: params.query, apellido: params.query, type: 'include'], accept: ContentType.JSON).json

        def model = [owners: response]

        render(contentType: 'application/json'){model}
    }
}
