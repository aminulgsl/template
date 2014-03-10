<%--
  Created by IntelliJ IDEA.
  User: Mohammed Imran
  Date: 3/4/14
  Time: 2:27 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.gsl.uma.security.Role" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="oros">
    <title></title>
</head>
<body>
<div class="row">
    <div class="col-xs-12">
        <div class="row">
            <div class="col-sm-7">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4>Load a Role to Map Right</h4>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main">
                            <g:form class="form-inline" method="post" name="roleRightMap" url="[action:'roleRight',controller:'manageRole']">
                                <g:select class="width-60 chosen-select" id="roleId" name='roleId'
                                          noSelection="${['':'Select Role...']}"
                                          from='${Role.list()}'
                                          optionKey="id" optionValue="authority"></g:select>

                                <button type="submit" class="btn btn-info btn-sm">
                                    <i class="icon-key bigger-110"></i>
                                    Load
                                </button>
                            </g:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
        <div id="accordion" class="accordion-style1 panel-group">

            <div class="panel panel-default" id="generalSetting">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle blue" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseOne">
                            <i class="icon-angle-down bigger-110" data-icon-hide="icon-angle-down"
                               data-icon-show="icon-angle-right"></i>
                            &nbsp;General Information
                        </a>
                    </h4>
                </div>

                <div class="panel-collapse collapse in" id="collapseOne">
                    <div class="panel-body">
                        %{--<g:each in="${bankSetupData}" var="bankSetting">--}%
                        <p>some text hrere</p>

                    </div>
                </div>
            </div>
        </div>
        </div>
        <!-- PAGE CONTENT ENDS -->
    </div><!-- /.col -->
</div><!-- /.row -->
<r:script>
    $(".chosen-select").chosen();
</r:script>
</body>
</html>