<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="description" content="">
            <meta name="author" content="">
            <link rel="shortcut icon" href="${pageContext.request.contextPath}/docs-assets/ico/favicon.ico">

            <title><f:message key="${titlekey}"/></title>
            <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript">
                $(document).ready(function() {
                    $('ul.nav > li').click(function(e) {
                        e.preventDefault();
                        $('ul.nav > li').removeClass('active');
                        $(this).addClass('active');
                    });
                }
            </script>

            <!-- Bootstrap core CSS -->
            <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
            <!-- Bootstrap theme -->
            <link href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slider.css">
            <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
            <link rel="stylesheet" href="http://blueimp.github.io/Gallery/css/blueimp-gallery.min.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-image-gallery.min.css">

            <!-- Bootstrap core JavaScript
================================================== -->
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/docs-assets/js/holder.js"></script><script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap-slider.js"></script>
            <script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap-image-gallery.min.js"></script>

            <!-- Custom styles for this template -->
            <!--            <link href="theme.css" rel="stylesheet">-->

            <!-- Just for debugging purposes. Don't actually copy this line! -->
            <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

            <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
            <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
              <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
            <![endif]-->


            <s:layout-component name="header"/>

        </head>


        <body>

            <!-- Fixed navbar -->
            <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-leaf">&nbsp;</span><f:message key="navigation.core.title"/></a>
                    </div>
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">


                            <li ><a href="${pageContext.request.contextPath}/index.jsp"><f:message key="navigation.index"/></a></li>
                             <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><f:message key="navigation.monsters"/><b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li ><s:link beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean"><f:message key="navigation.monsters"/></s:link></li>
                                        <li class="divider"></li>
                                        <li ><s:link beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean" event="gallery"><f:message key="navigation.gallery"/></s:link></li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><f:message key="navigation.areas"/><b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><s:link beanclass="com.muni.fi.pa165.actions.area.AreaActionBean"><span class="glyphicon glyphicon-screenshot">&nbsp;<f:message key="navigation.areas.management"/></span></s:link></li>
                                        <li class="divider"></li>
                                        <li><s:link beanclass="com.muni.fi.pa165.actions.locations.LocationsActionBean"><span class="glyphicon glyphicon-eye-open">&nbsp;<f:message key="navigation.areas.locations"/></span></s:link></li>
                                    </ul>
                                </li>

                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><f:message key="navigation.weapons"/><b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><s:link beanclass="com.muni.fi.pa165.actions.weapon.WeaponActionBean"><span class="glyphicon glyphicon-screenshot">&nbsp;<f:message key="navigation.weapons.management"/></span></s:link></li>
                                        <li class="divider"></li>
                                        <li><s:link beanclass="com.muni.fi.pa165.actions.efficiencies.EfficienciesActionBean"><span class="glyphicon glyphicon-flash">&nbsp;<f:message key="navigation.weapons.efficiencies"/></span></s:link></li>
                                    </ul>
                                </li>
                        </sec:authorize>
                              <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
                              <li><s:link beanclass="com.muni.fi.pa165.actions.user.UserActionBean"><f:message key="navigation.users"/></span></s:link></li>
                               </sec:authorize>
                                   <sec:authorize ifAnyGranted="ROLE_ADMIN">
                              <li><s:link beanclass="com.muni.fi.pa165.actions.user.UserActionBean"><f:message key="navigation.users"/></span></s:link></li>
                               </sec:authorize>
                        </ul>
                                    
                                    

                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                <sec:authorize access="isAuthenticated()">
                                    <a><span  class="glyphicon glyphicon-user">&nbsp;</span> <sec:authentication property="principal.username"></sec:authentication></a>
                                </sec:authorize>

                            </li>
                            <sec:authorize var="loggedIn" access="isAuthenticated()" />
                            <c:choose>
                                <c:when test="${loggedIn}">
                                    <li><a href="<c:url value="/j_spring_security_logout" />" ><span class="glyphicon glyphicon-off"></span>&nbsp;<f:message key="navigation.logout"/></a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li><a href="${pageContext.request.contextPath}/login.jsp" ><span class="glyphicon glyphicon-off"></span>&nbsp;<f:message key="navigation.login"/></a></li>
                                    </c:otherwise>
                                </c:choose>



                        </ul>
                    </div><!--/.nav-collapse -->
                </div>
            </div>

            <div class="container theme-showcase">

                <!-- Main jumbotron for a primary marketing message or call to action -->
                <div class="jumbotron">
                    <image src="${pageContext.request.contextPath}/docs-assets/images/banner.png" />

                </div>


                <div id="content">   
                    <div class="alert" style="margin-top: 5px">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
                        <s:messages/></div>  

                    <s:layout-component name="body"/>
                </div>

            </div> <!-- /container -->


        </body>
    </html>
</s:layout-definition>
