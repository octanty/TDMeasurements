<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">

        <h1 style="text-align: center"><f:message key="logout.title"/></h1>
        <div style="text-align: center">         
            <p class="text-info"><f:message key="logout.headline1"/></p>
            <p class="text-danger"><f:message key="logout.headline2"/></p>
            <a href="${pageContext.request.contextPath}/login.jsp" ><span class="glyphicon glyphicon-off"></span>&nbsp;<f:message key="navigation.login"/></a>
        </div>
            


    </s:layout-component>
</s:layout-render>

