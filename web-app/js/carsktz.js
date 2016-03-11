/**
 * Created by amonetta on 16/02/16.
 */

var inputDialog

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

function editCarAjax(car_id) {
    //preventDefault()
    var dat = $('#carInputForm').serialize()
    $.ajax({
        type:'POST',
        //data:$(this).parents('form:first').serialize(),
        data:dat,
        url:'/carsktz/car/update',
        success:function(data,textStatus){
            $('#carID' + car_id).replaceWith(data);
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){}
    })
    inputDialog.destroy()
    return false
}

function edit(car) {
    var rootRow = $('#carID' + car.id)
    var owner = {id:'', description:''}
    var ownercol = rootRow.find('td.carOwner')
    if (!ownercol.is(':empty')) {
        owner.id = ownercol.find('.ownerId').html();
        owner.description = ownercol.find('.ownerDescription').html();
    }
    var form = templates.buildTemplate(templates.temps._EDITCAR, {
            "::id" : rootRow.find('td.carId').html(),
            "::year" : rootRow.find('td.carYear').html(), //car.year,
            "::make" : rootRow.find('td.carMake').html(),
            "::model" : rootRow.find('td.carModel').html(),
            "::plate" : rootRow.find('td.carPlate').html(),
            "::ownerId" : owner.id,
            "::ownerDescription" : owner.description,
            "::submitLabel" : "Save",
            "::submitAction" : "editCarAjax(" + car.id + ")"
        })
    inputDialog = $('#editForm').modal()
    inputDialog.show(form)
}

function setActionNew(submitButtonName) {
    var submitButton = $("#" + submitButtonName)
    submitButton.on('click', function () {
        jQuery.ajax({
            type: "POST",
            data: jQuery(this).parents("form:first").serialize(),
            url: "/carsktz/car/save/",
                 success:function(data,textStatus){$("#carsTable tr:last").after(data);},
                 error:function(XMLHttpRequest,textStatus,errorThrown){}
        })
        return false
    })
}


/***
 * OnClick event for owner edit button
 */
$("#editOwner").click(function(){
    $("#ownerDescription").prop('disabled',false);
    $("#ownerDescription").val('');
    $("#ownerDescription").trigger('keyup',13);
});

/***
 * OnClick event for owner text box
 */
$("#ownerDescription").keyup(function(event){
    loadOwnerList($("#ownerDescription"),$("#ownerList"));
});

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


/* function showInputDialog() {
    inputDialog.show($('#editForm').html())
} */

//var autocomplete = new AutoComplete($('#carOwner'))