<%-- 
    Document   : main
    Created on : Nov 21, 2013, 4:41:32 PM
    Author     : Matej
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<s:layout-render name="/layout.jsp" titlekey="readers.title">    
    <s:layout-component name="tab">
        <li><a href="${pageContext.request.contextPath}/index.jsp"><span><f:message key="welcome.title"/></span></a></li>
        <li><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean"><span><f:message key="impression.title"/></span></s:link></li>
        <li class="active"><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean"><span><f:message key="readers.title"/></span></s:link></li>      
        </s:layout-component>
        <s:layout-component name="body">
        <h2><f:message key="customer.customers"/></h2>
         <%@include file="list.jsp"%> 
        <br/>
        <h2><f:message key="customer.newCustomer"/></h2>
        <s:errors/>        
        <s:form beanclass="cz.muni.fi.pa165.web.CustomerEditBean">
            <s:hidden name="customer.id"/>
            <fieldset>
                <%@include file="form.jsp"%>
            </fieldset>
            <s:submit name="create"><f:message key="insert"/></s:submit>
        </s:form>
    </s:layout-component>
</s:layout-render>