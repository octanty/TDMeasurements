<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<p class="title"><spring:message code="journey.list.title"/>:</p>

<a href="<c:url value="/journey/new" />" class="addButton"><span2><spring:message code="journey.newJourney"/></span2></a>
        <fmt:message var="datePattern" key="date.pattern.date" />
<table id="employeeAll" border="0">   
    <tr class="faty"> 
        <td><spring:message code="journey.car"/></td>
        <td><spring:message code="journey.employee"/></td>
        <td><spring:message code="journey.mileage"/></td>
        <td><spring:message code="journey.pickUpDate"/></td>
        <td><spring:message code="journey.returnDate"/></td>
        <td><spring:message code="edit"/></td>
        <td><spring:message code="delete"/></td>
    </tr>      

    <c:forEach items="${journeys}" var="journey">
        <tr>            
            <td><c:out value="${journey.car}"/></td>
            <td><c:out value="${journey.employee}"/></td>
            <td><c:out value="${journey.mileage}"/></td>
            <td><fmt:formatDate pattern="${datePattern}" value="${journey.pickUpDate}" /></td>
            <td><fmt:formatDate pattern="${datePattern}" value="${journey.returnDate}" /></td>

            <td>
                <a href="<c:url value="/journey/edit/${journey.id}" />"  id="bEdit"></a>
            </td>

            <td>
                <a href="<c:url value="/journey/delete/${journey.id}" />" onclick="return(confirm('Do you really want to delete ?'));" id="bDelete"></a>
            </td>
        </tr>
    </c:forEach>

</table>  
