package com.gsl.uma.security

class ManageUserController {
    def springSecurityService
    def index() {
        redirect(action: 'create')
    }
    def create() {
        render (view: 'showUserCreate')
    }
    def save() {
        RequestMap requestMap = RequestMap.get(11)
        requestMap.configAttribute = "ROLE_SUPER_ADMIN,ROLE_ADMIN"
        requestMap.save(flush: true)
        springSecurityService.clearCachedRequestmaps()
        redirect(controller: 'dashboard',action: 'index')
    }
    def update() { }
    def delete() { }
    def list() { }
    def listShowOnly() { }

}
