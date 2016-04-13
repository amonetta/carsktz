/**
 * Created by amonetta on 10/03/16.
 */
var templates = {
    temps : {
        _EDITCAR : 0,
    },

    getTemplate: function(template) {
        switch (template) {
            case templates.temps._EDITCAR:
                return templates._templateEditCar();
                break;
            default:
                console.log("Template not found.");
                return "empty"
        }
    },

    buildTemplate: function (template, params) {
        var temp = templates.getTemplate(template);
        $.each(params, function(key, value){
            temp = temp.replaceAll(key, value);
        });

        return temp;
    },

    _templateEditCar : function () {
        return '<div class="panel panel-primary">' +
        '<div class="panel-heading">'+
            '<h2><b>::formTitle</b></h2>' +
        '</div>'+
        '<div class="panel-body">' +
            "<form id='carInputForm' class='form-horizontal' action='::formAction'>" +
                    '<fieldset>' +
                        '<div class="form-group">' +
                            "<input id='idInput' type='hidden' name='id' value='::id'/>" +
                        '</div>' +
                        '<div class="form-group">' +
                            '<label class="col-md-2 col-lg-2 control-label" for="year">Year</label>' +
                            '<div class="col-md-10 col-lg-10">' +
                                "<input id='yearInput' name='year' type='number' min='1768' max='2016' placeholder='Car Year' class='form-control input-md' required='' value='::year' />" +
                            '</div>' +
                        '</div>' +
                        '<div class="form-group">' +
                            '<label class="col-md-2 col-lg-2 control-label" for="makeInput">Make</label>' +
                            '<div class="col-md-10 col-lg-10">' +
                                '<input id="makeInput" name="make" type="text" maxlength="50" placeholder="Car maker" class="form-control input-md" required="" value="::make"/>' +
                            '</div>' +
                        '</div>'+
                        '<div class="form-group">' +
                            '<label class="col-md-2 col-lg-2 control-label" for="modelInput">Model</label>' +
                            '<div class="col-md-10 col-lg-10">' +
                                '<input id="modelInput" name="model" type="text" maxlength="50" placeholder="Car Model" class="form-control input-md" required="" value="::model"/>' +
                            '</div>' +
                        '</div>' +
                        '<div class="form-group">' +
                            '<label class="col-md-2 col-lg-2 control-label" for="plateInput">Plate</label>' +
                            '<div class="col-md-10 col-lg-10">' +
                                '<input id="plateInput" name="plate" type="text" placeholder="Car Plate" pattern="([A-Z]{3})(\\d{3})|((D|C|I|M|A)\\d{3}(CP|DM|RX|AC|DC)[A-Z])" class="form-control input-md" required="" value="::plate"/>' +
                            '</div>' +
                        '</div>' +
                        '<div class="form-group">' +
                            '<input id="ownerInput" type="hidden" name="owner" value="::ownerId"/>' +
                        '</div>' +
                        '<div class="form-group">' +
                            '<label class="col-md-2 col-lg-2 control-label" for="ownerDescription">Owner</label>' +
                            '<div class="col-md-10 col-lg-10">' +
                                '<div class="input-group">' +
                                    '<input disabled="true" id="ownerDescription" autocomplete="off" type="text" maxlength="50" class="form-control" placeholder="Owner" value="::ownerDescription" onkeyup="keyupOwner(event)"' +
                                    'onenable="autocompletOwner.enable()" ondisable="autocompletOwner.disable()"/>' +
                                    '<span class="input-group-btn">' +
                                        "<button class='btn btn-default' type='button' id='editOwner' onclick='ownerEdit()'>Edit</button>" +
                                    '</span>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="form-group">' +
                            '<div class="col-md-offset-2 col-lg-offset-2 col-md-10 col-lg-10">' +
                                '<div id="ownerList" class="btn-group">' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="form-group">' +
                            '<div class="col-md-offset-2 col-lg-offset-3 col-md-10 col-lg-3">' +
                                '<div class="btn-group" role="group">' +
                                    "<button id='submitInputButton' type='button' class='ch-btn' name='submit' value='Confirm' onclick='::submitAction'>::submitLabel</button>" +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</fieldset>'+
                '</form>' +
            '</div>' +
        '</div>'
    }
}

String.prototype.replaceAll = function(search, replacement) {
    var str = this;
    str = str.replace(search, replacement);
    if(str.indexOf(search) >= 0){
        str = str.replaceAll(search, replacement)
    }

    return str;
}