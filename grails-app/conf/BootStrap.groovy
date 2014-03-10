import com.gsl.uma.saas.Events
import com.gsl.uma.saas.Feature
import com.gsl.uma.saas.Events
import com.gsl.uma.saas.Module
import com.gsl.uma.security.Role
import com.gsl.uma.security.User
import com.gsl.uma.security.UserRole
import grails.plugin.springsecurity.SpringSecurityUtils

class BootStrap {
    def grailsApplication
    def init = { servletContext ->
        createUserWithRole()
        createAccessMapping()
        createRequestMap()
    }
    void createUserWithRole(){
        def superAdmin = Role.findByAuthority('ROLE_SUPER_ADMIN') ?: new Role(authority: 'ROLE_SUPER_ADMIN').save(failOnError: true)
        User supAdminUser = User.findByUsername('admin')
        if(!supAdminUser){
            supAdminUser = new User(username: 'admin', password: 'password', enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false)
            supAdminUser.save(flush: true)
            new UserRole(user: supAdminUser, role: superAdmin).save(flush: true)
        }

        //Role admin
        def roleAdmin = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        User adminUser = User.findByUsername('admin2')
        if(!adminUser){
            adminUser = new User(username: 'admin2', password: 'password', enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false)
            adminUser.save(flush: true)
            new UserRole(user: adminUser, role: roleAdmin).save(flush: true)
        }

    }
    def createAccessMapping(){
        //  1.  user Management Module
        Module userMgmtModule = Module.findByName('USER_MGMT_MODULE') ?: new Module(name: 'USER_MGMT_MODULE',description:'User Management',menuText:'User Mgmt',showOnMenu: false, status: true).save(failOnError: true)

        // 1.1  user CURD
        Feature userMgmt = Feature.findByName('USER_MGMT')
            if(!userMgmt){
                userMgmt = new Feature(name: 'USER_MGMT',description:'Manage User with role and access',fmenuText:'Manage User',controllerName: 'manageUser',actionName:'index', showOnMenu: true, status: true).save(failOnError: true)
                userMgmtModule.addToFeature(userMgmt)
            }
                    // 1.1.1    Create User
                    Events createUser = Events.findByName('CREATE_USER')
                    if(!createUser){
                        createUser = new Events(name: 'CREATE_USER',description:'Create user with user List at bottom',fmenuText:'Create User',controllerName: 'manageUser',actionName:'create', showOnMenu: true,isPermitToAll:false, status: true).save(failOnError: true)
                        userMgmt.addToEvents(createUser)
                    }
                    Events createUserSubmit = Events.findByName('SAVE_CREATE_USER')
                    if(!createUserSubmit){
                        createUserSubmit = new Events(name: 'SAVE_CREATE_USER',description:'Create user with user List at bottom',fmenuText:'Save',controllerName: 'manageUser',actionName:'save', showOnMenu: false,isPermitToAll:true, status: true).save(failOnError: true)
                        userMgmt.addToEvents(createUserSubmit)
                    }
                    // 1.1.2    Update User
                    Events updateUser = Events.findByName('UPDATE_USER')
                    if(!updateUser){
                        updateUser = new Events(name: 'UPDATE_USER',description:'Update user',fmenuText:'Update',controllerName: 'manageUser',actionName:'update', showOnMenu: false,isPermitToAll:false, status: true).save(failOnError: true)
                        userMgmt.addToEvents(updateUser)
                    }
                    // 1.1.3    Delete User
                    Events deleteUser = Events.findByName('DELETE_USER')
                    if(!deleteUser){
                        deleteUser = new Events(name: 'DELETE_USER',description:'Delete user',fmenuText:'Delete',controllerName: 'manageUser',actionName:'delete', showOnMenu: false,isPermitToAll:false, status: true).save(failOnError: true)
                        userMgmt.addToEvents(deleteUser)
                    }
                    // 1.1.4    List User for pagination only
                    Events listUserForPagination = Events.findByName('LIST_USER_PAGINATION')
                    if(!listUserForPagination){
                        listUserForPagination = new Events(name: 'LIST_USER_PAGINATION',description:'List method for pagination',fmenuText:'List',controllerName: 'manageUser',actionName:'list', showOnMenu: false,isPermitToAll:true, status: true).save(failOnError: true)
                        userMgmt.addToEvents(listUserForPagination)
                    }
                    // 1.1.5    Show User List only. i.e no CURD permission allowed
                    Events userListShowOnly = Events.findByName('USER_LIST_SHOW_ONLY')
                    if(!userListShowOnly){
                        userListShowOnly = new Events(name: 'USER_LIST_SHOW_ONLY',description:'This is a dumy link to show user List only',fmenuText:'User List',controllerName: 'manageUser',actionName:'listShowOnly', showOnMenu: true,isPermitToAll:false, status: true).save(failOnError: true)
                        userMgmt.addToEvents(userListShowOnly)
                    }


                // 1.2  role CURD
                /*Feature roleMgmt = Feature.findByName('ROLE_MGMT')
                if(!roleMgmt){
                    roleMgmt = new Feature(name: 'ROLE_MGMT',description:'Manage Role CURD',fmenuText:'Manage Role',controllerName: 'manageRole',actionName:'index', showOnMenu: true, status: true).save(failOnError: true)
                    userMgmtModule.addToFeature(roleMgmt)
                }*/
                    // 1.2.1    Create User
                    Events createRole = Events.findByName('CREATE_ROLE')
                    if(!createRole){
                        createRole = new Events(name: 'CREATE_ROLE',description:'Create role with role List at bottom',fmenuText:'Create Role',controllerName: 'manageRole',actionName:'index', showOnMenu: true,isPermitToAll:false, status: true).save(failOnError: true)
                        userMgmt.addToEvents(createRole)
                    }
                    Events createRoleSubmit = Events.findByName('SAVE_CREATE_ROLE')
                    if(!createRoleSubmit){
                        createRoleSubmit = new Events(name: 'SAVE_CREATE_ROLE',description:'Create role with role List at bottom',fmenuText:'Save',controllerName: 'manageRole',actionName:'save', showOnMenu: false,isPermitToAll:true, status: true).save(failOnError: true)
                        userMgmt.addToEvents(createRoleSubmit)
                    }

                    // 1.2.2    Update User
                    Events updateRole = Events.findByName('UPDATE_ROLE')
                    if(!updateRole){
                        updateRole = new Events(name: 'UPDATE_ROLE',description:'Update role',fmenuText:'Update',controllerName: 'manageRole',actionName:'update', showOnMenu: false,isPermitToAll:false, status: true).save(failOnError: true)
                        userMgmt.addToEvents(updateRole)
                    }
                    // 1.2.3    Delete User
                    Events deleteRole = Events.findByName('DELETE_ROLE')
                    if(!deleteRole){
                        deleteRole = new Events(name: 'DELETE_ROLE',description:'Delete role',fmenuText:'Delete',controllerName: 'manageRole',actionName:'delete', showOnMenu: false,isPermitToAll:false, status: true).save(failOnError: true)
                        userMgmt.addToEvents(deleteRole)
                    }
                    // 1.2.4    List User for pagination only
                    Events listRolePagination = Events.findByName('LIST_ROLE_PAGINATION')
                    if(!listRolePagination){
                        listRolePagination = new Events(name: 'LIST_ROLE_PAGINATION',description:'List method for pagination',fmenuText:'List',controllerName: 'manageRole',actionName:'list', showOnMenu: false,isPermitToAll:true, status: true).save(failOnError: true)
                        userMgmt.addToEvents(listRolePagination)
                    }
                    // 1.2.5    Show User List only. i.e no CURD permission allowed
                    Events roleListShowOnly = Events.findByName('ROLE_LIST_SHOW_ONLY')
                    if(!roleListShowOnly){
                        roleListShowOnly = new Events(name: 'ROLE_LIST_SHOW_ONLY',description:'This is a dumy link to show List only',fmenuText:'Role List',controllerName: 'manageRole',actionName:'listShowOnly', showOnMenu: true,isPermitToAll:false, status: true).save(failOnError: true)
                        userMgmt.addToEvents(roleListShowOnly)
                    }

                    Events showRoleRightMap = Events.findByName('SHOW_ROLE_RIGHT_MAP')
                    if(!showRoleRightMap){
                        showRoleRightMap = new Events(name: 'SHOW_ROLE_RIGHT_MAP',description:'Show role right map',fmenuText:'Role Right Map',controllerName: 'manageRole',actionName:'roleRight', showOnMenu: true,isPermitToAll:false, status: true).save(failOnError: true)
                        userMgmt.addToEvents(showRoleRightMap)
                    }
                    Events saveRoleRightMap = Events.findByName('SAVE_ROLE_RIGHT_MAP')
                    if(!saveRoleRightMap){
                        saveRoleRightMap = new Events(name: 'SAVE_ROLE_RIGHT_MAP',description:'Save role right map',fmenuText:'Update',controllerName: 'manageRole',actionName:'saveRoleRight', showOnMenu: false,isPermitToAll:true, status: true).save(failOnError: true)
                        userMgmt.addToEvents(saveRoleRightMap)
                    }


        //2.    Core Banking part here
        Module coreBank = Module.findByName('CORE_BANKING') ?: new Module(name: 'CORE_BANKING',description:'Core banking with reports',menuText:'Banking',showOnMenu: false, status: true).save(failOnError: true)
            Feature dashboard = Feature.findByName('DASHBOARD')
            if(!dashboard){
                dashboard = new Feature(name: 'DASHBOARD',description:'dashboard description',fmenuText:'Home',controllerName: 'dashboard',actionName:'index', showOnMenu: true, status: true).save(failOnError: true)
                coreBank.addToFeature(dashboard)
            }



        //3.    Insurance part here
        Module insurance = Module.findByName('INSURANCE') ?: new Module(name: 'INSURANCE',description:'All Insurance here',menuText:'Insurance',status: true).save(failOnError: true)



    }
    def createRequestMap(){
        if (SpringSecurityUtils.securityConfigType != 'Requestmap') {
            return
        }
        // set the below variable to true if requestMap updated
        // and also delete/truncate your database requestmap table to update take effect
        boolean isRequestMapUpdated = true;
        if(!isRequestMapUpdated){
            return
        }
        String requestMapClassName = SpringSecurityUtils.securityConfig.requestMap.className

        //default configuration entry required for static resources
//        '/', '/index', '/index.gsp',
        def Requestmap = grailsApplication.getClassForName(requestMapClassName)
        for (url in ['/**/js/**', '/**/css/**', '/**/images/**', '/**/favicon.ico',
                '/login', '/login/**', '/logout', '/logout/**']) {
            Requestmap.newInstance(url: url, configAttribute: 'permitAll').save(flush: true, failOnError: true)
        }
        def featureList = Feature.list()
        featureList.each {Feature feature ->
            Requestmap.newInstance(url: "/"+feature.controllerName+"/"+feature.actionName,referenceId:feature.id, referenceType:'Feature', configAttribute: 'ROLE_SUPER_ADMIN,ROLE_ADMIN').save(flush: true, failOnError: true)
        }
        def eventList = Events.list()
        eventList.each {Events events ->
            if(events.isPermitToAll){
                Requestmap.newInstance(url: "/"+events.controllerName+"/"+events.actionName,referenceId:events.id, referenceType:'Events', configAttribute: 'permitAll').save(flush: true, failOnError: true)
            } else {
                Requestmap.newInstance(url: "/"+events.controllerName+"/"+events.actionName,referenceId:events.id, referenceType:'Events', configAttribute: 'ROLE_SUPER_ADMIN').save(flush: true, failOnError: true)
            }
        }

        println "Request Map code complete"


    }
    def destroy = {
    }
}
