import static org.codehaus.groovy.grails.web.mapping.DefaultUrlMappingEvaluator.*

import org.codehaus.groovy.grails.web.mapping.DefaultUrlMappingEvaluator

class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                controller ( validator: {
                    it != api
                })
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        /*"/assets/$imageName.$format" (redirect: "/assets/images/$imageName.$format") {
            constraints {
                format (validator: {
                    it in ['gif', 'png']
                })
            }
        }*/

        group "/api", {
            "/car(.$format)?"(controller: "carsRest") {
                action = [GET: "index", POST: "save"]
            }
            "/car(.$format)?"(controller: "carsRest") {
                action = [GET: "index", POST: "save"]
            }
            "/car/$id(.$format)?"(controller: "carsRest") {
                action = [GET: "show", PUT: "update", DELETE: "delete"]
            }
            "/car/$carId/owner(.$format)?"(controller: "ownerRest") {
                action = [GET: "index"]
            }
            "/owner(.$format)?"(controller: "ownerRest") {
                action = [GET: "index", POST: "save"]
            }
            "/owner/$id(.$format)?"(controller: "ownerRest") {
                action = [GET: "show", PUT: "update", DELETE: "delete"]
            }
            "/owner/$ownerId/cars(.$format)?"(controller: "carsRest") {
                action = [GET: "index", POST: "save"]
            }
            "/owner/$ownerId/cars/$id(.$format)?"(controller: "carsRest", parseRequest: true) {
                action = [GET: "show", POST: "save", PUT: "update", DELETE: "delete"]
            }
        }
	}
}