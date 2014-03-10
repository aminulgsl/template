package com.gsl.uma.saas

class Events {
    String name
    String description
    String fmenuText
//    String requestUrl
    String controllerName
    String actionName
//    String iconClassName;
    boolean showOnMenu = false
    boolean isPermitToAll = false
    boolean status =true
    //    Long userId         //companyId
    static constraints = {
        name nullable: false, unique: true
        fmenuText nullable: false
    }
}
