<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="liquors" page="liquors">
    <s:layout-component name="header">
    </s:layout-component>
    <s:layout-component name="body">

        <s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.LiquorsActionBean" var="liquorActionBean"/>

        <h2>${actionBean.liquor.name}</h2>

        <p><strong><f:message key="liquor.name" />:</strong> ${liquorActionBean.liquor.name}</p>
        <p><strong><f:message key="liquor.ean" />:</strong> ${liquorActionBean.liquor.ean}</p>
        <p><strong><f:message key="liquor.alcoholPercentage" />:</strong> ${liquorActionBean.liquor.alcoholPercentage} %</p>
        <p><strong><f:message key="liquor.volume" />:</strong> ${liquorActionBean.liquor.volume} %</p>
        <p><strong><f:message key="liquor.producer" />:</strong> ${liquorActionBean.liquor.producer.name}</p>

    </s:layout-component>
</s:layout-render>