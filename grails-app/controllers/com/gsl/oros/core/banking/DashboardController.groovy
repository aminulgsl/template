package com.gsl.oros.core.banking

import com.gsl.uma.saas.Feature

class DashboardController {

    def index() {
        def featureList = Feature.withCriteria {
            eq 'status', true
            eq 'showOnMenu', true
        }
        println featureList
        render(view: 'index')
    }
}
