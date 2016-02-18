modules = {
    application {
        resource url:'js/application.js'
    }

    ngRoute {
        resource url: 'js/angular-route-1.4.9.js'
    }

    angularjs {
        dependsOn 'ngRoute'
        resource url:'js/angular-1.4.9.js', disposition: 'head'
    }

    restangular {
        dependsOn 'angularjs'
        resource url:'js/restangular-1.4.0.js'
    }

    lodashjs {
        resource url: 'js/lodash-2.2.0.js'
    }

    smarttable {
        // Documentation: http://lorenzofox3.github.io/smart-table-website/#section-pagination
        // Source: https://github.com/lorenzofox3/Smart-Table
        dependsOn 'angularjs'
        resource url: 'js/smart-table.js'
    }

    allangular {
        dependsOn 'angularjs,restangular,smarttable'
    }

    baseCss {
        resource url:'/css/main.css'
    }

    core {
        dependsOn 'baseCss'
        dependsOn 'allangular,lodashjs,application'
        resource url: '/js/carsktz.js'
    }
}