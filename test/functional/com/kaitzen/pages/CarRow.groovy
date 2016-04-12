package com.kaitzen.pages

import geb.Module

/**
 * Created by amonetta on 07/04/16.
 */
class CarRow extends Module {
    static content = {
        cell { index -> $("td", index) }
        id { cell(0).text().toLong() }
        year { cell(1).text().toInteger() }
        model { cell(2).text() }
        make { cell(3).text() }
        plate { cell(4).text() }
        ownerId { cell(5).find("#ownerId").text() }
        ownerName { cell(5).find("ownerDescription").text() }
    }
}
