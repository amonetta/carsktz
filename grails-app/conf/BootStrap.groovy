import com.kaitzen.*
import grails.converters.*

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(Car) { car ->
            return [id: car.id] + car.properties.findAll { k, v -> k != 'class' }
        }
    }
    def destroy = {
    }
}
