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

        //"/assets/$imageName" (controller: "owner", action: "getImage")

        "/car/api"(resources: "carsRest")
        "/owner/api"(resources: "ownerRest")
	}
}