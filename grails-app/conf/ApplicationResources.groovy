modules = {
    application {
        resource url:'js/application.js'
    }

    lodashjs {
        resource url: 'js/lodash-2.2.0.js'
    }

    baseCss {
        resource url:'/css/main.css'
    }

    jqueryRest {
        dependsOn 'jquery'
        resource url:'js/jquery.rest.js'
    }

    core {
        dependsOn 'baseCss'
        dependsOn 'lodashjs,application, jquery, jquery-ui, jqueryRest'
        resource url: '/js/carsktz.js'
    }
}