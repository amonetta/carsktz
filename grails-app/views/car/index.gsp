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
        }
    </g:javascript>
</head>
<body>
    <div id="carFilter" >
        <g:form>
            <g:textField id="carYearFrom" name="yearFrom" datatype="integer"></g:textField>
            <g:textField id="carYearUntil" name="yearUntil" datatype="integer"></g:textField>
            <g:submitToRemote value="CarSearchForm"
                url="[controller: car, action: findCarsAjax]"
                update="carList"
                onSuccess="clearForm(data)"/>
        </g:form>
    </div>
    <div id="carList">
        <div class="carEntry" ng-repeat="car in allCars">
            <div class="carYear">{{car.year}}</div>
            <div class="carMake">{{car.make}}</div>
            <div class="carModel">{{car.model}}</div>
        </div>
    </div>
</body>
</html>