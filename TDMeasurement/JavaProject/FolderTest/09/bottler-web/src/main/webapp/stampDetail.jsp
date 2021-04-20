<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="stamps" page="stamps">
    <s:layout-component name="header">
        <!-- adding to header -->
    </s:layout-component>
    <s:layout-component name="body">

        <s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.StampsActionBean" var="actionBean"/>

        <h2><strong><f:message key="stamp.number_of_stamp" />:</strong> ${actionBean.stamp.numberOfStamp}</h2>

        <p><strong><f:message key="stamp.issued_date" />:</strong>
            <time class="timeago" datetime="<c:out value="${actionBean.stamp.issuedDate}"/>">
                <c:out value="${actionBean.stamp.issuedDate}"/>
            </time>
        </p>

    </s:layout-component>
</s:layout-render>