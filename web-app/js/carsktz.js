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
    document.getElementById("submitInputButton").setAttribute("update","carID" + car.id);
    document.getElementById("submitInputButton").setAttribute("url","[controller: 'car', action: 'update/" + car.id +"]");

}