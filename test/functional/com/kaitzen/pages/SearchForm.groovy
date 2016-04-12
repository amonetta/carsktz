package com.kaitzen.pages

import geb.Module

/**
 * Created by amonetta on 08/04/16.
 */
class SearchForm extends Module {

    static content = {
        from { $("#carYearFrom") }
        to {$("#carYearUntil") }
        model { $("#carModel") }
        make { $("#carMake") }
        plate { $("#carPlate") }
        owner { $("#carOwner") }
        maxSelector { $("#max") }
        bsubmit { $("#btnSearch") }
        btoggle{ $("#toggleSearchBtn") }
    }
}
