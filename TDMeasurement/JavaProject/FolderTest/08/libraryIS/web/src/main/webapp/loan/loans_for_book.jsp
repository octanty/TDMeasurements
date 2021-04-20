<%-- 
    Document   : loan_for_book
    Created on : Nov 27, 2013, 10:05:51 AM
    Author     : MiškoHu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="loans.title">    
    <s:layout-component name="tab">
        <li><a href="${pageContext.request.contextPath}/index.jsp"><span><f:message key="welcome.title"/></span></a></li>
        <li><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean"><span><f:message key="impression.title"/></span></s:link></li>
        <li class="active"><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean"><span><f:message key="readers.title"/></span></s:link></li>    
        </s:layout-component>
        <s:layout-component name="body">
        <h3>
            <f:message key="loan.exemplar"/>
            <c:out value="${actionBean.book.id}"/>
        </h3>
        <h1><c:out value="${actionBean.book.impression.name}"/></h1>
        <%@include file="loans_for_book_list.jsp"%>              
    </s:layout-component>
</s:layout-render>