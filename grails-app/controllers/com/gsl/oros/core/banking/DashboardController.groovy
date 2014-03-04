package com.gsl.oros.core.banking

import org.springframework.security.access.annotation.Secured

@Secured('ROLE_SUPER_ADMIN')
class DashboardController {

    def index() {
        render(view: 'index')
    }
}
