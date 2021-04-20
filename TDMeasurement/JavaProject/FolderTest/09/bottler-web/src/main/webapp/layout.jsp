<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<s:layout-definition>

    <s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.BaseActionBean" var="baseActionBean"/>

    <!doctype html>
    <html lang="${pageContext.request.locale}">
    <head>
        <title><f:message key="${titlekey}"/></title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

        <link rel="stylesheet" type="text/css" media="screen"
              href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" media="screen"
              href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" type="text/css" media="screen"
              href="${pageContext.request.contextPath}/static/lib/bootstrap-datetimepicker-2.1.11/css/bootstrap-datetimepicker.min.css"/>
        <link rel="stylesheet" type="text/css" media="screen"
              href="${pageContext.request.contextPath}/static/style.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/lib/tablesorter/css/theme.bootstrap.css">

        <script src="${pageContext.request.contextPath}/static/lib/jquery/jquery-1.10.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/lib/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/lib/bootstrap-datetimepicker-2.1.11/js/bootstrap-datetimepicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/lib/bootbox.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/lib/tablesorter/js/jquery.tablesorter.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/lib/tablesorter/js/jquery.tablesorter.widgets.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/lib/jquery.timeago.js"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script>
            // bootstrap theme for tablesorter
            $.extend($.tablesorter.themes.bootstrap, {
                // these classes are added to the table. To see other table classes available,
                // look here: http://twitter.github.com/bootstrap/base-css.html#tables
                table: 'table table-bordered',
                header: 'bootstrap-header', // give the header a gradient background
                footerRow: '',
                footerCells: '',
                icons: '', // add "icon-white" to make them white; this icon class is added to the <i> in the header
                sortNone: 'bootstrap-icon-unsorted',
                sortAsc: 'icon-chevron-up glyphicon glyphicon-chevron-up', // includes classes for Bootstrap v2 & v3
                sortDesc: 'icon-chevron-down glyphicon glyphicon-chevron-down', // includes classes for Bootstrap v2 & v3
                active: '', // applied when column is sorted
                hover: '', // use custom css here - bootstrap class may not override it
                filterRow: '', // filter row class
                even: '', // odd row zebra striping
                odd: ''  // even row zebra striping
            });


            $(document).ready(function () {

                $("tr.row-link td").click(function () {

                    if ($(this).hasClass("no-link")) {
                        return;
                    }

                    var link = $(this).parent().find("a").first().attr("href");
                    if (link) {
                        window.open(link, "_self");
                    }
                });
                if ($("html").attr("lang").indexOf("cs") != -1) {
                    // Czech
                    jQuery.timeago.settings.strings = {
                        prefixAgo: "před",
                        prefixFromNow: null,
                        suffixAgo: null,
                        suffixFromNow: null,
                        seconds: "méně než minutou",
                        minute: "minutou",
                        minutes: "%d minutami",
                        hour: "hodinou",
                        hours: "%d hodinami",
                        day: "1 dnem",
                        days: "%d dny",
                        month: "1 měsícem",
                        months: "%d měsíci",
                        year: "1 rokem",
                        years: "%d roky"
                    };
                }
                jQuery("time.timeago").timeago();

            });
        </script>


        <s:layout-component name="header"/>

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
    </head>

    <body>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                            <span class="pull-right">
                                <shiro:user>
                                    <f:message key="index.user"/>
                                    ${baseActionBean.user.givenName} ${baseActionBean.user.surname}
                                    (<s:link href="/auth/logout"><f:message key="index.logout"/></s:link>)
                                </shiro:user>
                                <shiro:guest>
                                    <s:link href="/login.jsp"><f:message key="index.login"/></s:link>
                                </shiro:guest>

                            </span>

                    <h1>
                        <f:message key="${titlekey}"/>
                        <small><f:message key="centralBottleRegistry"/></small>
                    </h1>
                </div>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-md-3 column">
                <ul class="nav nav-stacked nav-pills">
                    <c:forEach items="${baseActionBean.menuItems}" var="item">

                        <c:choose>
                            <c:when test="${item == page}">
                                <li class="active">
                            </c:when>
                            <c:otherwise>
                                <li>
                            </c:otherwise>
                        </c:choose>

                        <s:link href="/${item}"><f:message key="${item}"/></s:link>
                        </li>

                    </c:forEach>


                </ul>
            </div>
            <div class="col-md-9 column">

                <c:choose>
                    <c:when test="${pageContext.request.getParameter('modal_form') != null}">
                        <script>
                            $(document).ready(function ($) {
                                $('.modal_form').modal();
                            });
                        </script>

                    </c:when>
                    <c:otherwise>

                        <s:messages/>
                        <s:errors/>

                    </c:otherwise>
                </c:choose>

                <s:layout-component name="body"/>

            </div>
        </div>

    </div>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <hr/>
                <p class="text-muted"><f:message key="headers.desc"/> <s:link href="headers.jsp"><f:message key="headers"/></s:link>. </p>
            </div>
        </div>
    </div>


    </body>
    </html>
</s:layout-definition>
