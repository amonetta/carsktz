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

    chicoui {
        resource url: 'css/chico.min.css', disposition: head
        resource url: 'css/chico_mesh.min.css', disposition: head
        resource url: 'js/chico.min.js', disposition: body
    }

    core {
        dependsOn 'baseCss'
        dependsOn 'lodashjs,application, jquery, jquery-ui, jqueryRest'
        dependsOn 'chicoui'
        resource url: 'js/carsktz.js', disposition: head
        resource url: 'js/templates.js', disposition: head
    }
}