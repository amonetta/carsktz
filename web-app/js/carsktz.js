/**
 * Created by amonetta on 16/02/16.
 */
var inputDialog
var autocompletOwner

function createAutocomplete(ownerFieldId) {
    /*autocompletOwner = new $(ownerFieldId).autocomplete({
            source: '/owner/api/autocomplete',
            minLength: 2,
            type: 'GET',
            dataType: 'json',
            select: function(event, ui) {
                $('#ownerInput').val(ui.id)
                $('#ownerDescription').val(ui.label)
            }
        });*/
    //autocompletOwner.disable()
    autocompletOwner = new $(ownerFieldId).autocomplete({
        delay: 500,
        minLength: 2,
        source: function (request,response) {
            getAutocomplete(request, response)
        }
        /*focus: function(event, ui) {
            // prevent autocomplete from updating the textbox
            //event.preventDefault();
        },
        select: function(event, ui) {
            // prevent autocomplete from updating the textbox
            //event.preventDefault();
            // navigate to the selected item's url
            //window.open(ui.item.url);
        }*/
    })
}

function getAutocomplete(request, response) {
    $.getJSON("/carktz/owner/autocomplete", {
        query: request.term
    }, function (data) {
        // data is an array of objects and must be transformed for autocomplete to use
        var array = data.error ? [] : $.map(data.owners, function (owner) {
            return {
                id: owner.id,
                label: owner.label,
                value: owner.value
            };
        });
        response(array);
    });
}

function focusAutoselect(event, ui) {
    // prevent autocomplete from updating the textbox
    //event.preventDefault();
}

function selectAutoselect(event, ui) {
    // prevent autocomplete from updating the textbox
    //event.preventDefault();
    $('#ownerInput').val(ui.item.id)
    $('#ownerDescription').val(ui.item.label)
}

function applyElementUpdates(json) {
    var updates;
    var script;

    if(json.updates) {
        updates = json.updates;
        var element;
        var scripts = new Array();

        for(element in updates) {
            element = updates[element];

            switch(element.mode) {
                case 'execute':
                    scripts.push(element.script);
                    break;
                case 'replace':
                    jQuery(element.target).html(element.content);
                    break;
                case 'prepend':
                    jQuery(element.target).prepend(element.content);
                    break;
                case 'append':
                    jQuery(element.target).append(element.content);
                    break;
            }
        }

        // Run scripts.
        for(script in scripts) {
            script = scripts[script];
            eval(script);
        }

    } // if(json.updates)
} // applyElementUpdates

function setSearchBtn() {
    $("#btnSearch").val("<span class='glyphicon glyphicon-search'/> Search")
}

function loadingTable(tableId) {
    $(tableId).html('<div class="text-center"> <span class="ch-loading-small"/> Wait a moment please... </div>')
}

/***
 * OnClick event for owner edit button
 */
function ownerEdit() {
    $("#ownerDescription").prop('disabled',false);
    $("#ownerDescription").val('');
    $("#ownerDescription").trigger("keyup",13);
}

function btnToggleSearch(collapseDivId) {
    if ($(collapseDivId).attr('class').indexOf('collapse in') > -1)
        $("#toggleSearchBtn").find("span").attr("class", "glyphicon glyphicon-menu-down")
    else
        $("#toggleSearchBtn").find("span").attr("class", "glyphicon glyphicon-menu-up")
}

function editCarAjax(car_id) {
    //preventDefault()
    $.ajax({
        async: false,
        type:'POST',
        //data:$(this).parents('form:first').serialize(),
        data: $('#carInputForm').serialize(),
        url: $('#carInputForm').attr("action"),
        beforeSend: function(){
            inputDialog.show("<span class='ch-loading'/>")
        },
        success:function(data,textStatus){
            $('#carID' + car_id).replaceWith(data);
        }/*,
        error:function(XMLHttpRequest,textStatus,errorThrown){}*/
    })
    inputDialog.destroy()
    return false
}

function newCarAjax(e) {
    e.preventDefault()
    e.stopPropagation()
    var form = $("#carInputForm")
    jQuery.ajax({
        async: false,
        type: "POST",
        data: form.serialize(),
        url: form.attr("action"),
        beforeSend: function(jqXHR, settings){
            inputDialog.destroy()
            inputDialog = $('#editForm').modal()
            inputDialog.show('<div class="text-center"> <span class="glyphicon glyphicon-hourglass"/> Processing... </div>')},
        success:function(data,textStatus){$("#carsTable tr:last").after(data);},
        error:function(XMLHttpRequest,textStatus,errorThrown){},
        complete: function(jqXHR, textStatus) {inputDialog.destroy()}
    })
    inputDialog.destroy()
    return false
}

function deleteCarAjax(e, carid) {
    e.preventDefault()
    e.stopPropagation()
    jQuery.ajax({
        type:"DELETE",
        url:"/carsktz/car/delete/" + carid,
        success:function(data,textStatus){
            $("#carID" + carid).remove()
        },error:function(XMLHttpRequest,textStatus,errorThrown){}
    });
    return false
}

function showNewCarDialog() {
    var form = templates.buildTemplate(templates.temps._EDITCAR, {
        "::formTitle" : "Create Car",
        "::formAction" : "/carsktz/car/save/",
        "::id" : '',
        "::year" : new Date().getFullYear(), //car.year,
        "::make" : '',
        "::model" : '',
        "::plate" : '',
        "::ownerId" : '',
        "::ownerDescription" : '',
        "::submitLabel" : "Confirm",
        "::submitAction" : "newCarAjax(event)"
    })
    inputDialog = $('#editForm').modal()
    inputDialog.on('ready', function () {
        createAutocomplete('#ownerDescription')
    })
    inputDialog.show(form)
}

function edit(carEntryId) {
    var rootRow = $(carEntryId)
    var owner = {id:'', description:''}
    var ownercol = rootRow.find('td.carOwner')
    if (!ownercol.is(':empty')) {
        owner.id = ownercol.find('.ownerId').html();
        owner.description = ownercol.find('.ownerDescription').html();
    }
    var car_id = rootRow.find('td.carId').html()
    var form = templates.buildTemplate(templates.temps._EDITCAR, {
            "::formTitle" : "Edit Car",
            "::formAction" : "/carsktz/car/update/" + car_id,
            "::id" : car_id,
            "::year" : rootRow.find('td.carYear').html(), //car.year,
            "::make" : rootRow.find('td.carMake').html(),
            "::model" : rootRow.find('td.carModel').html(),
            "::plate" : rootRow.find('td.carPlate').html(),
            "::ownerId" : owner.id ? owner.id : "",
            "::ownerDescription" : owner.description ? owner.description : "",
            "::submitLabel" : "Confirm",
            "::submitAction" : "editCarAjax(" + car_id + ")"
        })
    inputDialog = $('#editForm').modal()
    inputDialog.on('ready', function () {
        createAutocomplete('#ownerDescription')
    })
    inputDialog.show(form)
}

/***
 * OnClick event for owner text box
 */
function keyupOwner(event){
    loadOwnerList($("#ownerDescription"),$("#ownerList"));
}

/***
 * @param ownerInputElement
 * @param ownerListElement
 */
function loadOwnerList(ownerInputElement, ownerListElement){

    $(ownerListElement).empty();
    var queryString = "/carsktz/ownerRest/" + "?nombre=" + ownerInputElement.val();
    ownerListElement.show();

    $.getJSON(queryString, function(data){
        var len = ((data.length <= 8)? data.length : 8);
        for (var i = 0; i < len; i++){
            var item=data[i];
            ownerListElement.append($('<a>')
                .attr('attr-id',item.id)
                .attr('class','btn btn-success btn-sm')
                .text(item.nombre + ' ' + item.apellido));
        }

        ownerListElement.find("a").bind("click", function(e){
            e.preventDefault();
            var row = $(this);
            $("#ownerDescription").val(row.text());
            $("#ownerInput").val(row.attr('attr-id'));
            $("#ownerDescription").prop('disabled',true);
            ownerListElement.hide();
        });
    });
}