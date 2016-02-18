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
<body>
    <div ng-controller="CarsCtrl">
        <div class="panel-primary">
            <div class="panel-heading">
                <!-- Form Name -->
                Busqueda
            </div>
            <div class="panel-body">
                <g:form class="form-horizontal" onsubmit="nullFunction(); return false;">
                    <fieldset>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-2 control-label" for="carYearFrom">Year from:</label>
                            <div class="col-md-9">
                                <g:field id="carYearFrom" class="form-control input-md" name="{{carFrom}}" type="number" ng-model="carFrom" placeholder="Minimun year"></g:field>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label" for="carYearUntil">Year until:</label>
                            <div class="col-md-9">
                                <g:field id="carYearUntil" class="form-control input-md" name="{{carTo}}" type="number" ng-model="carTo" placeholder="Maximun year"></g:field>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label" for="carMake">Maker:</label>
                            <div class="col-md-9">
                                <g:textField id="carMake" class="form-control input-md" name="{{carMake}}" datatype="text" ng-model="carMake" placeholder="Car maker"></g:textField>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label" for="carModel">Model:</label>
                            <div class="col-md-9">
                                <g:textField id="carModel" class="form-control input-md" name="{{carModel}}" datatype="text" ng-model="carModel" placeholder="Car model"></g:textField>
                            </div>
                        </div>

                        <!-- Button -->
                        <div class="form-group">
                            <div class="col-md-offset-2"/>
                            <div class="col-md-9">
                                <g:field type="button" class="btn btn-default" name="submit" value="Search" ng-click="refreshCars()"/>
                            </div>
                        </div>
                    </fieldset>

                </g:form>
            </div>
        </div>

        <table st-safe-src="safeCollection" st-table="allCars" class="table table-striped">
            <thead>
            <tr>
                <th st-sort="year">Year</th>
                <th st-sort="make">Make</th>
                <th st-sort="model">Model</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="car in allCars">
                <td>{{car.year}}</td>
                <td>{{car.make}}</td>
                <td>{{car.model}}</td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="3" class="text-center">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-4">
                                <span class="pull-right">
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="itemsByPageMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                            {{itemsByPage}}
                                            <span class="caret"/>
                                        </button>
                                            <ul class="dropdown-menu" aria-labelledby="itemsByPageMenu">
                                                <li ng-repeat="option in itemsByPageOptions">
                                                    <a ng-click="selectedItemsByPage(option)">{{option}}</a>
                                                </li>
                                            </ul>
                                    </div>
                                </span>
                            </div>
                        <div class="col-md-6">
                            <span class="pull-left">
                                <div st-pagination="allCars" st-items-by-page="itemsByPage" st-displayed-pages="7"></div>
                            </span>
                        </div>
                        </div>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>

        <div class="panel panel-info">
            <div class="panel-heading">JSON Response</div>
            <div class="panel-body" style="max-height: 500px; overflow-y: scroll;">{{plainResponde}}</div>
        </div>
    </div>
</body>
</html>