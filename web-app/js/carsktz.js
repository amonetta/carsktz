/**
 * Created by amonetta on 16/02/16.
 */

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

function edit(car) {
    document.getElementById("idInput").setAttribute("value",car.id)
    document.getElementById("yearInput").setAttribute("value",car.year);
    document.getElementById("makeInput").setAttribute("value",car.make);
    document.getElementById("modelInput").setAttribute("value",car.model);
    document.getElementById("plateInput").setAttribute("value",car.plate);
    $("#ownerInput").val(car.owner.id);
    $("#ownerDescription").val(car.owner.nombre + ' ' + car.owner.apellido);
    document.getElementById("submitInputButton").setAttribute("onclick",
        'jQuery.ajax({' +
            'type:"POST",' +
            'data:jQuery(this).parents("form:first").serialize(),' +
            ' url:"/carsktz/car/update/' + car.id + '",' +
            'success:function(data,textStatus){jQuery("#carID' + car.id + '").html(data);},' +
            'error:function(XMLHttpRequest,textStatus,errorThrown){}' +
        '});' +
        'return false'
    );
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