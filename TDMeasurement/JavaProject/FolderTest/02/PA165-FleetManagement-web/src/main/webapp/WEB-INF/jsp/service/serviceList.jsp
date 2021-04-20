<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<p class="title"><spring:message code="service.list.title"/></p>

<a href="<c:url value="/service/new" />" class="addButton"><span3><spring:message code="service.newInterval"/></span3></a>

<fmt:message var="datePattern" key="date.pattern.date" />
<table id="employeeAll" border="0">   
    <tr class="faty"> 
        <td><spring:message code="service.service"/></td>
        <td><spring:message code="service.mileage"/></td>
        <td><spring:message code="service.dateControl"/></td>
        <td><spring:message code="service.completionDate"/></td>
        <td><spring:message code="edit"/></td>
        <td><spring:message code="delete"/></td>
    </tr> 
    <tr> 
        <c:forEach items="${serviceIntervals}" var="serviceInterval">
        <tr>            
            <td><c:out value="${serviceInterval.service}"/></td>
            <td><c:out value="${serviceInterval.mileageControl}"/></td>
            <td><fmt:formatDate pattern="${datePattern}" value="${serviceInterval.dateControl}" /></td>
            <td><fmt:formatDate pattern="${datePattern}" value="${serviceInterval.completionDate}" /></td>

            <td>
                <a href="<c:url value="/service/edit/${serviceInterval.id}" />"  id="bEdit"></a>
            </td>

            <td>
                <a href="<c:url value="/service/delete/${serviceInterval.id}" />" onclick="return(confirm('Do you really want to delete ?'));" id="bDelete"></a>
            </td>
        </tr>
    </c:forEach>

</table>  
