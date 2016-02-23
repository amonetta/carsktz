class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                action( validator: {
                    it != api
                })
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        "/car/api/"(controller: "carsRest", parseRequest: true) {
            action = [GET: "index", POST: "save"]
        }
        "/car/api/$id"(resource: "carsRest") {
            action = [GET: "show", POST: "update", PUT: "update", DELETE: "delete"]
        }
	}
}
