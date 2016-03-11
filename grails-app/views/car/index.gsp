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
                                    <g:textField id="carOwner" class="form-control input-md" name="owner" datatype="text" pattern="(\\d{7,8})|(\\w*)" placeholder="Car owner: DNI or name propose" onchange="carOwnerChange();"></g:textField>
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
                                    <g:submitToRemote value="Search" url="[controller: 'Car', action: 'findCarsAjax']" update="grid" class="ch-btn"/>
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

        <div id="editForm" style="display:none">
        </div>
        <button type="button" data-target="#editForm" class="ch-btn" onclick="showInputDialog()">View form</button>

    </div>
</body>
</html>