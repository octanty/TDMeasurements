<%-- 
    Document   : impression_edit
    Created on : 10.11.2013, 20:00:38
    Author     : Mjartan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>


<s:layout-render name="/layout.jsp" titlekey="impression.title">    
    <s:layout-component name="tab">
        <li><a href="${pageContext.request.contextPath}/index.jsp"><span><f:message key="welcome.title"/></span></a></li>
        <li class="active"><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean"><span><f:message key="impression.title"/></span></s:link></li>
        <sec:authorize access="hasRole('ROLE_USER')">
            <li><s:link beanclass="cz.muni.fi.pa165.web.LoanEditBean" event="myLoans"><span><f:message key="myloans.title"/></span></s:link></li>      
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean"><span><f:message key="readers.title"/></span></s:link></li>      
        </sec:authorize>
        </s:layout-component>
        <s:layout-component name="body">
        <h2><f:message key="impression.impressions"/></h2>
            <%@include file="list.jsp"%> 
        <br/>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <s:errors/>
        <h2><f:message key="impression.newImpression"/></h2>
            <s:form beanclass="cz.muni.fi.pa165.web.ImpressionEditBean">
                 <s:hidden name="impression.id"/>
                 <fieldset>
                     <%@include file="form.jsp"%>
                 </fieldset>
                 <s:submit name="create"><f:message key="insert"/></s:submit>
             </s:form>
       </sec:authorize>
    </s:layout-component>
</s:layout-render>