# CarsKTZ
Technologies
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

Building with Grails, this app is a traing for the use of Grails and have also may interesting examples.

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
    
    {
        "cars": [
            {
                "id": 4,
                "model": "Minx Magnificent",
                "plate": "DIE437",
                "year": 1950,
                "owner": null,
                "make": "Hillman"
            },
            {
                "id": 5,
                "owner": null,
                "model": "Corvette",
                "make": "Chevrolet",
                "plate": "ENY077",
                "year": 1953
            }, ...
    }

`make` and `model` are proposes of values like:

    http://host/carsktz/car/api/index?make=fo

returns any car hows contains `make` property similar to "fo" without case sense. 

    {
        "cars": [
        ...
            {
                "id": 9,
                "model": "Thunderbird",
                "plate": "EGW705",
                "owner": null,
                "make": "Ford",
                "year": 1955
            }, ...
    }

