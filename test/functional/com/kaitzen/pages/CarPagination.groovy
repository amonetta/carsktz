package com.kaitzen.pages

import geb.Module

/**
 * Created by amonetta on 11/04/16.
 */
class CarPagination extends Module {

    static content = {
        actualStep { $(".active span") }
        hasPrev { $(".prev").not(".disable") ? true : false }
        hasNext { $(".next").not(".disable") ? true : false }
        prevStep { $(".prev").not(".disable").find(".step") }
        nextStep { $(".next").not(".disable").find(".step") }
        totalSteps { $(".step").size() - (hasPrev ? 1 : 0) - (hasNext ? 1 : 0) }
        step { index -> $(".step", index + (hasPrev ? 1 : 0)) }
        firstStep { totalSteps > 0 ? step(0) : null }
        lastStep { totalSteps > 0 ? step(totalSteps - 1) : null}
    }
}
