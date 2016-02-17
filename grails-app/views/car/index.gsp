<%--
  Created by IntelliJ IDEA.
  User: amonetta
  Date: 16/02/16
  Time: 10:43
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cars</title>
    <meta name="layout" content="main"/>
    <content tag="htmlAttrs">ng-app=Carsktz</content>
    <r:require modules="core"/>
    <g:javascript>
        function clearForm(e) {
            $('#carYearFrom').val('')
            $('#carYearUntil').val('')
            $('#carMake').val('')
            $('#carModel').val('')
        }
    </g:javascript>
</head>
    <div ng-controller="CarsCtrl">
        <div id="carFilter" >
            <g:form>
                <input id="carYearFrom" name="{{carFrom}}" type="number" ng-model="carFrom"></input>
                <g:field id="carYearUntil" name="yearUntil" type="number" ng-model="car_to"></g:field>
                <g:textField id="carMake" name="carMake" datatype="text" ng-model="car_make"></g:textField>
                <g:textField id="carModel" name="carModel" datatype="text" ng-model="car_model"></g:textField>
                <g:submitButton name="Search"
                    ng-click="refreshCars()"
                    update="carList"
                    onSuccess="clearForm(data)"/>
            </g:form>
        </div>
        <div id="carList">
            <div class="carEntry" ng-repeat="car in allCars" ng-cloak>
                <div class="carYear">{{car.year}}</div>
                <div class="carMake">{{car.make}}</div>
                <div class="carModel">{{car.model}}</div>
            </div>
        </div>
    </div>
</body>
</html>