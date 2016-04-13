package com.kaitzen.pages

import geb.Module

/**
 * Created by amonetta on 07/04/16.
 */
class CarTable extends Module {

    static content = {
        cars(wait: true) {index -> module CarRow, $("#carsTable tr", index + 1)}
        carCount(wait: true) { $('#carsTable tr').size() - 1}
        pagination(wait: true) { module CarPagination, $(".pagination")}
    }
}
