package com.kaitzen.pages

import geb.Page

/**
 * Created by amonetta on 07/04/16.
 */
class CarPage extends Page {

    static url = "car"

    static content = {
        carTable { module CarTable }
        searchForm { module SearchForm }
        carForm(require: false) { module CarForm, $(".ch-popover").find("form")}
    }

    static at = {
        carTable
    }
}
