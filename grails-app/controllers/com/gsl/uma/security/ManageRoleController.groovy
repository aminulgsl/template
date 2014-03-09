package com.gsl.uma.security

class ManageRoleController {

    def index() {
    render (view: 'createRole')
    }
    def create() {
        println params
        if (request.method == 'POST') {
            String authority = params.authority
            if(authority && authority.toUpperCase().startsWith('ROLE_')){
                Role role = new Role()
                role.authority = authority.toUpperCase()

                if (!role.save()) {
                    // validation failed, render registration page again
                    render (view: 'createRole',model: [role: role])
                    return
                } else {
                    flash.message = "Role saved successfully"
                    render (view: 'createRole')
                    return
                }
            } else {
                flash.message = "Role name must start with ROLE_"
                render (view: 'createRole')
                return
            }
        }
        render (view: 'createRole')
    }
    def mapUserRole(){
        render (view: 'mapUserRole')
    }
    def saveUserRole(){

    }
    def update() { }
    def delete() { }
    def list() { }
    def listShowOnly() { }
}
