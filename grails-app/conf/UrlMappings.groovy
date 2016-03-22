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

        "/assets/$imageName.$format" (redirect: "/assets/images/$imageName.$format") {
            constraints {
                format (validator: {
                    it in ['gif', 'png']
                })
            }
        }

        "/car/api"(resources: "carsRest")
        "/owner/api"(resources: "ownerRest")
	}
}