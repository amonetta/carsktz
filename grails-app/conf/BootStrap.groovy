import com.kaitzen.*
import grails.converters.*

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(Car) {
            return it.properties.findAll { k, v -> k != 'class' }
        }
    }
    def destroy = {
    }
}
