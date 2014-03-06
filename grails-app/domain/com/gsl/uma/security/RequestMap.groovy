package com.gsl.uma.security

import org.springframework.http.HttpMethod

class RequestMap {

	String url
	String configAttribute
	HttpMethod httpMethod
    Long referenceId        //ModuleId, FeatureId, FeventsId
    String referenceType    //[Module, Feature, Events]

	static mapping = {
		cache true
	}

	static constraints = {
		url blank: false, unique: 'httpMethod'
		configAttribute blank: false
		httpMethod nullable: true
        referenceId nullable: true
        referenceType nullable: true
	}
}
