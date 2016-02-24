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
    <g:javascript library="jquery" plugin="jquery"/>
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
    <div>
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
                                <g:field id="carYearFrom" class="form-control input-md" name="carFrom" type="number" placeholder="Minimun year"></g:field>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label" for="carYearUntil">Year until:</label>
                            <div class="col-md-9">
                                <g:field id="carYearUntil" class="form-control input-md" name="carTo" type="number" placeholder="Maximun year"></g:field>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label" for="carMake">Maker:</label>
                            <div class="col-md-9">
                                <g:textField id="carMake" class="form-control input-md" name="carMake" datatype="text" placeholder="Car maker"></g:textField>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label" for="carModel">Model:</label>
                            <div class="col-md-9">
                                <g:textField id="carModel" class="form-control input-md" name="carModel" datatype="text" placeholder="Car model"></g:textField>
                            </div>
                        </div>

                        <!-- Button -->
                        <div class="form-group">
                            <div class="col-md-offset-2"/>
                            <div class="col-md-9">
                                <g:submitToRemote value="Search" url="[controller: 'Car', action: 'findCarsAjax']" update="carsTable" class="btn btn-primary"/>
                            </div>
                        </div>
                    </fieldset>

                </g:form>
            </div>
        </div>

        <table st-safe-src="safeCollection" st-table="allCars" class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Year</th>
                <th>Make</th>
                <th>Model</th>
                <th></th>
                <!--<th>JSON</th>-->
            </tr>
            </thead>
            <tbody id="carsTable">
                <g:render template="carEntry" collection="${carList}" var="car"/>
            </tbody>
        </table>

        <div id="editForm" class="collapse">
            <form class="form-horizontal">
                <fieldset>
                    <!-- Form Name -->
                    <legend>Car</legend>

                    <div class="form-group">
                        <g:hiddenField id="idInput" name="id"/>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="year">Year</label>
                        <div class="col-md-4">
                            <g:field id="yearInput" name="year" type="number" placeholder="Car Year" class="form-control input-md" required=""/>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="maleInput">Make</label>
                        <div class="col-md-4">
                            <g:field id="makeInput" name="make" type="text" placeholder="Car maker" class="form-control input-md" required=""/>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="modelInput">Model</label>
                        <div class="col-md-4">
                            <g:field id="modelInput" name="model" type="text" placeholder="Car Model" class="form-control input-md" required=""/>
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-4">
                            <g:submitToRemote class="btn btn-primary"
                                              id="submitInputButton" name="submitInputButton" value="Submit"/>
                        </div>
                    </div>
                </fieldset>

            </form>
        </div>
        <button type="button" data-toggle="collapse" data-target="#editForm" class="btn btn-primary">View form</button>

    </div>
</body>
</html>