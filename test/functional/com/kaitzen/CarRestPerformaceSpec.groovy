package com.kaitzen

import spock.lang.Specification;
import wslite.rest.ContentType
import wslite.rest.RESTClient

/**
 * Created by amonetta on 20/04/16.
 */
class CarRestPerformaceSpec extends Specification {

    def restClient = new RESTClient("http://localhost:8080/carsktz/api/car")
    def memcachedService = new MemcachedService()

    def "Changing pages"() {
        setup:
        memcachedService.afterPropertiesSet()
        memcachedService.clear()

        when:
        for (int i = 0; i < tries; i++) {
            Thread.sleep(Math.round(10000 * getRandom(timeFactor)))
            def page = getRandom(pages)
            def query = [max : max, offset : (page - 1) * max]
            def response = restClient.get(query: query, accept: ContentType.JSON)
        }

        then:
        true

        where:
        pages = [1, 5, 7, 54, 100, 190, 201, 243, 300]
        max = 20
        tries = 30
        timeFactor = [0.1d, 0.3d, 0.5d, 0.8d, 1d]
    }

    private getRandom(List list) {
        def i = new Random().nextInt(list.size())
        return list[i]
    }
}
