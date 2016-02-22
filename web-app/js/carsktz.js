/**
 * Created by amonetta on 16/02/16.
 */

function writeTable(cars) {
    // cache <tbody> element:
    var tbody = $('#carsTable');
    for (var i = 0; i < cars.length; i++) {
        // create an <tr> element, append it to the <tbody> and cache it as a variable:
        var tr = $('<tr/>').appendTo(tbody);
        tr.append('<td>' + cars[i].year + '</td>');
        tr.append('<td>' + cars[i].make + '</td>');
        tr.append('<td>' + cars[i].model + '</td>');
        tr.append('<td>' + cars[i] + '</td>');
    }
}