<%--
  Created by IntelliJ IDEA.
  User: Mohammed Imran
  Date: 3/4/14
  Time: 2:27 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="oros">
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        Manage Role
        <small>
            <i class="icon-double-angle-right"></i>
            Role Name must start with 'ROLE_'. i.e ROLE_ADMIN, ROLE_BANK_ADMIN
        </small>
    </h1>
</div><!-- /.page-header -->
<sec:access controller='manageRole' action='index'>
        <div class="row">
                <div class="col-xs-12">
                    <g:if test='${flash.message}'>
                        <div class='alert alert-success '>
                            <i class="icon-bell green"> <b> ${flash.message} </b> </i>
                        </div>
                    </g:if>
                    <g:hasErrors bean="${role}">
                        <div class='alert alert-danger'>
                            <g:renderErrors bean="${role}"/>
                        </div>
                    </g:hasErrors>
                    <!-- PAGE CONTENT BEGINS -->
                    <g:form class="form-horizontal" method="post" role="form" url="[action:'create',controller:'manageRole']">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="authority"> Role Name </label>

                            <div class="col-sm-9">
                                <input type="text" id="authority" name="authority" placeholder="ROLE_ADMIN.." class="col-xs-10 col-sm-5" />
                            </div>
                        </div>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <g:submitButton name="Save" id="saveBtn"  class="btn btn-info"/>
                                %{--<button class="btn btn-info" type="button">
                                    <i class="icon-ok bigger-110"></i>
                                    Submit
                                </button>--}%
                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="icon-undo bigger-110"></i>
                                    Reset
                                </button>
                            </div>
                        </div>
                    </g:form>
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            <div class="row">
                <h4>Here is Role list grid</h4>
            </div>
        </div><!-- /.row -->
    </sec:access>


</body>
</html>