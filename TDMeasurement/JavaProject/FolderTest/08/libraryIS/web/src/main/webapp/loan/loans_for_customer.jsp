<%-- 
    Document   : create
    Created on : Nov 22, 2013, 5:49:20 PM
    Author     : MiškoHu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<s:layout-render name="/layout.jsp" titlekey="loans.title">    
    <s:layout-component name="tab">
        <li><a href="${pageContext.request.contextPath}/index.jsp"><span><f:message key="welcome.title"/></span></a></li>
        <li><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean"><span><f:message key="impression.title"/></span></s:link></li>
        <sec:authorize access="hasRole('ROLE_USER')">
            <li class="active"><s:link beanclass="cz.muni.fi.pa165.web.LoanEditBean" event="myLoans"><span><f:message key="myloans.title"/></span></s:link></li>      
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="active"><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean"><span><f:message key="readers.title"/></span></s:link></li>      
         </sec:authorize>
        </s:layout-component>
        <s:layout-component name="body">
        <h3><f:message key="loan.loans"/></h3>
        <h1><c:out value="${actionBean.customer.name}"/></h1>
        <%@include file="loans_for_customer_list.jsp"%>              
    </s:layout-component>
</s:layout-render>