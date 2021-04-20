<%-- 
    Document   : edit
    Created on : 12.11.2013, 18:43:26
    Author     : Rex
--%>
%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<s:layout-render name="/layout.jsp" titlekey="editbook.title">    
    <s:layout-component name="tab">
        <li><a href="${pageContext.request.contextPath}/index.jsp"><span><f:message key="welcome.title"/></span></a></li>
        <li class="active"><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean"><span><f:message key="impression.title"/></span></s:link></li>
        <li><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean"><span><f:message key="readers.title"/></span></s:link></li>      
        </s:layout-component>
        <s:layout-component name="body">
        <s:errors/>
        <s:form beanclass="cz.muni.fi.pa165.web.BookEditBean">
            <s:hidden name="book.id"/>
            <s:hidden name="book.impression.id"/>
            <%@include file="form.jsp"%>
            <s:submit name="save"><f:message key="save"/></s:submit>
        </s:form>
    </s:layout-component>
</s:layout-render>
