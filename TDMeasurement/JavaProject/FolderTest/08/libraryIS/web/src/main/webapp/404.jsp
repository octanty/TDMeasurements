<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:layout-render name="/layout.jsp" titlekey="main.title">    
    <s:layout-component name="tab">
        <li class="active"><a href="${pageContext.request.contextPath}/index.jsp"><span><f:message key="error"/></span></a></li>
        <li><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean"><span><f:message key="impression.title"/></span></s:link></li>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean"><span><f:message key="readers.title"/></span></s:link></li>      
        </sec:authorize>
        </s:layout-component>
        <s:layout-component name="body">
        <h1>HTTP error 404</h1>
        <div class="post">
            <b>Page not found</b>
        </div>          
    </s:layout-component>
</s:layout-render>