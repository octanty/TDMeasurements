<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="auth" page="auth">
    <s:layout-component name="header">
    </s:layout-component>

    <s:layout-component name="body">
        <h3>You are now logged in.</h3>
       
        <p><s:link href="/auth/logout">logout</s:link></p>
    </s:layout-component>
</s:layout-render>