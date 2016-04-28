package com.kaitzen

import wslite.rest.ContentType
import wslite.rest.RESTClient
import org.codehaus.groovy.grails.core.io.ResourceLocator

class OwnerController {

    ResourceLocator grailsResourceLocator

    //static scaffold = true

    def restClient = new RESTClient("http://localhost:8080/carsktz/api/owner")

    def autocomplete() {
        def response = restClient.get(query: [nombre: params.query, apellido: params.query, type: 'include'], accept: ContentType.JSON).json

        def model = [owners: response]

        render(contentType: 'application/json'){model}
    }

    /*def getImage(String imageName) {
        println imageName
        final Resource image = grailsResourceLocator.findResourceForURI('/images/' + imageName)
        render file: image.inputStream, contentType: 'image/gif'
    }*/
}
