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
    }

    static at = {
        carTable
    }
}
