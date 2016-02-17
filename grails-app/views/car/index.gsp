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
                            <label class="col-md-2 control-label" for="singlebutton"></label>
                            <div class="col-md-9">
                                <g:field type="button" class="btn btn-default" name="submit" value="Submit" ng-click="refreshCars()"/>
                            </div>
                        </div>
                    </fieldset>

                </g:form>
            </div>
        </div>

            <table id="carList" class="table table-striped" at-table at-paginated at-list="allCars" at-config="pag_config">
                <thead></thead>
                <tbody>
                    <tr>
                        <td at-implicit at-sortable at-attribute="year" at-initial-sorting="asc"/>
                        <td at-implicit at-sortable at-attribute="make"/>
                        <td at-implicit at-sortable at-attribute="model"/>
                    </tr>
                </tbody>
            </table>

            <at-pagination at-list="allCars" at-config="pag_config"></at-pagination>
    </div>
</body>
</html>