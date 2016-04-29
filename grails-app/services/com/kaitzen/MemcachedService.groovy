package com.kaitzen

import grails.util.Environment
import net.spy.memcached.AddrUtil
import net.spy.memcached.MemcachedClient
import org.springframework.beans.factory.InitializingBean

class MemcachedService implements InitializingBean {

    static final Object NULL = "NULL"
    static final defaults = [ exp: 600 ]
    MemcachedClient memcachedClient
    def grailsApplication

    void afterPropertiesSet() {
        if (Environment.current == Environment.DEVELOPMENT) {
            memcachedClient = new MemcachedClient(AddrUtil.getAddresses("localhost:11211"))
        }
        if (Environment.current == Environment.TEST) {
            memcachedClient = new MemcachedClient(AddrUtil.getAddresses("localhost:11211"))
        }
    }

    def get(String key) {
        def value = memcachedClient.get(key)

        value == NULL ? null : value
    }

    def set(String key, Object value) {
        memcachedClient.set(key, grailsApplication.config.carsktz?.memcached?.exp ?: defaults.exp , value)
    }

    def delete(String key) {
        memcachedClient.delete(key)
    }

    def clear() {
        memcachedClient.flush()
    }

    def update(key, function) {
        def value = function()
        if (value == null) { value = NULL }
        set(key, value)

        value
    }

    def get(key, function) {
        def value = get(key)
        if (value == null) {
            value = update(key, function)
        }

        value == NULL ? null : value
    }
}
