<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="headers" page="headers">
    <s:layout-component name="header">
    </s:layout-component>
    <s:layout-component name="body">
        <ul>
            <c:forEach items="${header}" var="h">
                <li><strong>${h.key}: </strong>${h.value}</li>
            </c:forEach>
        </ul>
    </s:layout-component>
</s:layout-render>
