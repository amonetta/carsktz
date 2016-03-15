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

        "/car/api"(resources: "carsRest")
	}
}
