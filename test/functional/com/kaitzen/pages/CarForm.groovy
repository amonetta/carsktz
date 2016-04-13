package com.kaitzen.pages

import geb.Module

/**
 * Created by amonetta on 13/04/16.
 */
class CarForm extends Module {

    static content = {
        title   { $().closest(".panel").find(".panel-heading b").text() }
        action  { $().getAttribute("action") }
        year    { $("input[name='year']") }
        model   { $("input[name='model']") }
        make    { $("input[name='make']") }
        plate   { $("input[name='plate']") }
        submit  { $("input[name='submit']") }
        ownerId { $("input[name='owner']") }
        ownerDescription { $("input#ownerDescription") }
        bedit { $("button#editOwner ") }
    }
}
