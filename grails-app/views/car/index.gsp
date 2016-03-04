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
        <div class="col-md-12 col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <!-- Form Name -->
                    Search
                </div>
                <div class="panel-body">
                    <g:form class="form-horizontal" onsubmit="nullFunction(); return false;">
                        <fieldset>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-2 col-lg-2 control-label" for="carYearFrom">Year from:</label>
                                <div class="col-md-9 col-lg-9">
                                    <g:field id="carYearFrom" class="form-control input-md" name="from" type="number" placeholder="Minimun year"></g:field>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 col-lg-2 control-label" for="carYearUntil">Year until:</label>
                                <div class="col-md-9 col-lg-9">
                                    <g:field id="carYearUntil" class="form-control input-md" name="to" type="number" placeholder="Maximun year"></g:field>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 col-lg-2 control-label" for="carMake">Maker:</label>
                                <div class="col-md-9 col-lg-9">
                                    <g:textField id="carMake" class="form-control input-md" name="make" datatype="text" placeholder="Car maker"></g:textField>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 col-lg-2 control-label" for="carModel">Model:</label>
                                <div class="col-md-9 col-lg-9">
                                    <g:textField id="carModel" class="form-control input-md" name="model" datatype="text" placeholder="Car model"></g:textField>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 col-lg-2 control-label" for="carPlate">Plate:</label>
                                <div class="col-md-9 col-lg-9">
                                    <g:textField id="carPlate" class="form-control input-md" name="plate" datatype="text" placeholder="Car plate"></g:textField>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 col-lg-2 control-label" for="carOwner">Owner:</label>
                                <div class="col-md-9 col-lg-9">
                                    <g:textField id="carOwner" class="form-control input-md" name="owner" datatype="text" pattern="(\\d{7,8})|(\\w*)" placeholder="Car owner: DNI or name propose"></g:textField>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 col-lg-2 control-label" for="max">Results p/page: </label>
                                <div class="col-md-9 col-lg-9">
                                    <g:select id="max" class="form-control" from="${[20,50,100,500,1000, 'all']}" name="max" value="${tableModel?.filters?.max}" noSelection="${['null':20]}"/>
                                </div>
                            </div>
                            <!-- Button -->
                            <div class="form-group">
                                <div class="col-md-offset-2"/>
                                <div class="col-md-9 col-lg-9">
                                    <g:submitToRemote value="Search" url="[controller: 'Car', action: 'findCarsAjax']" update="grid" class="btn btn-primary"/>
                                </div>
                            </div>
                        </fieldset>

                    </g:form>
                </div>
            </div>
        </div>

        <div id="grid" class="grid">
            <g:render template="carTable" model="${tableModel}"/>
        </div>

        <div id="editForm" class="collapse">
            <form id="carInputForm" class="form-horizontal">
                <fieldset>
                    <!-- Form Name -->
                    <legend>Car</legend>

                    <div class="form-group">
                        <g:hiddenField id="idInput" name="id"/>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 col-lg-4 control-label" for="year">Year</label>
                        <div class="col-md-4 col-lg-4">
                            <g:field id="yearInput" name="year" type="number" min="1768" max="2016" placeholder="Car Year" class="form-control input-md" required=""/>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 col-lg-4 control-label" for="makeInput">Make</label>
                        <div class="col-md-4 col-lg-4">
                            <g:field id="makeInput" name="make" type="text" maxlength="50" placeholder="Car maker" class="form-control input-md" required=""/>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 col-lg-4 control-label" for="modelInput">Model</label>
                        <div class="col-md-4 col-lg-4">
                            <g:field id="modelInput" name="model" type="text" maxlength="50" placeholder="Car Model" class="form-control input-md" required=""/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 col-lg-4 control-label" for="plateInput">Plate</label>
                        <div class="col-md-4 col-lg-4">
                            <g:field id="plateInput" name="plate" type="text" placeholder="Car Plate" pattern='([A-Z]{3})(\\d{3})|((D|C|I|M|A)\\d{3}(CP|DM|RX|AC|DC)[A-Z])' class="form-control input-md" required=""/>
                        </div>
                    </div>

                    <div class="form-group">
                        <g:field id="ownerInput" name="owner" type="hidden" placeholder="Owner" class="form-control input-md" required=""/>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 col-lg-4 control-label" for="ownerDescription">Owner</label>
                        <div class="input-group col-md-4 col-lg-4">
                            <input disabled="true" id="ownerDescription" autocomplete="off" type="text" maxlength="50" name="ownerDescription" class="form-control" placeholder="Owner"/>
                            <span class="input-group-btn">
                                <button class="btn btn-primary" type="button" id="editOwner">Edit</button>
                            </span>
                        </div>

                        <div class="col-md-offset-4 col-md-4 col-lg-4">
                            <div id="ownerList" class="btn-group">
                            </div>
                        </div>
                    </div>
                    <!-- Button -->
                    <div class="form-group">
                        <div class="col-md-offset-4 col-lg-offset-4 col-md-4 col-lg-4">
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-primary" onclick='
                                    document.getElementById("idInput").setAttribute("value","");
                                    document.getElementById("yearInput").setAttribute("value","");
                                    document.getElementById("makeInput").setAttribute("value","");
                                    document.getElementById("modelInput").setAttribute("value","");
                                    document.getElementById("plateInput").setAttribute("value","");
                                    setActionNew("submitInputButton")'>
                                    <span class="glyphicon glyphicon-plus"/> New
                                </button>
                                <g:submitToRemote class="btn btn-primary" id="submitInputButton"
                                                  name="submitInputButton" value="Confirm"/>
                            </div>
                        </div>
                    </div>
                </fieldset>

            </form>
        </div>
        <button type="button" data-toggle="collapse" data-target="#editForm" class="btn btn-primary">View form</button>

    </div>
</body>
</html>