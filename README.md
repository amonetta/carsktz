# CarsKTZ
Tecnologies
- Tomcat 7
- MySQL 5.6.29
- SDK 1.7
- Grails 2.3.7
- JQuery 1.4.2
- HTML5
- Bootstrap 3

Relevant plugins
- Tomcat 7.0.55.3
- JQuery Plugin 1.11.1

Data source provided by [n8bar](https://github.com/n8barr) at [automotive-model-year-data](https://github.com/n8barr/automotive-model-year-data)

CarsKTZ is a REST API and simple CRUD end-user application also, that id provides a simple way to get cars models from database as a simple JSON format.

Builded with Grails, this app is a traing for the use of Grails and have also may interesting examples.

REST API
---
For develop your own consumer app for this API, here a simple guide

For accessing the API use:

    http://host/carsktz/car/api

For getting a full list of entities, use:

    http://host/carsktz/car/api/indext

and the response will have the format:

    {
    "cars": [
        {
            "id": 1,
            "make": "Ford",
            "plate": "AAA000",
            "year": 1909,
            "owner": {
                "class": "com.kaitzen.Owner",
                "id": 1,
                "apellido": "Apellido",
                "cars": [
                    {
                        "class": "Car",
                        "id": 1
                    }
                ],
                "dni": 36547326,
                "nacionalidad": "Argentino",
                "nombre": "Nombre"
            },
            "model": "Model T"
        }, ...
    }

Pay attention that there is an array of cars so, if you try to access in code would be like:

    response.json.car[0]

and NOT:

    response.json[0]

Also you are able to add parameter to restrict result:
- from
- to
- make
- model
- owner

For the first for ones, there is nothing tricky: 

`from` and `to` limit min and max year response like:

    http://host/carsktz/car/api/index?from=1950&to=1980

this will return something like:
    
