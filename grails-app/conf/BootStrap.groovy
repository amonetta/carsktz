import com.kaitzen.*
import grails.converters.*

class BootStrap {

    def searchableService

    def init = { servletContext ->

        JSON.registerObjectMarshaller(Car) { car ->
            return [id: car.id] + car.properties.findAll { k, v -> k != 'class'  && !(k =~ /.+Id/)}
        }

        // Manually start the mirroring process to ensure that it comes after the automated migrations.
        println "Performing bulk index"
        searchableService.reindex()
        println "Starting mirror service"
        searchableService.startMirroring()

        /*environments {
            development {
                fillPlates()
            }
            test {
                fillPlates()
            }
        }*/ // Comentado para hacer pruebas y reducir el tiempo de inicio.
    }

    def destroy = {
    }

    def fillPlates() {
        println "Generating plates"
        Car.where{plate == null}.each {
            it.plate = generatePlate()
            it.save(failOnError: true)
        }
        println "Finish generating plates"
        return
    }

    def generatePlate() {
        return generateLetter() + generateNumbers()
    }

    def lettersSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    def generateLetter() {
        def letters = ""
        def random = new Random()

        (1..3).each {
            letters = letters + lettersSet[random.nextInt(lettersSet.size())]
        }

        return letters
    }

    def generateNumbers() {
        def numbers = ""
        def random = new Random()

        (1..3).each {
            numbers = numbers + random.nextInt(10).toString()
        }

        return numbers
    }
}
