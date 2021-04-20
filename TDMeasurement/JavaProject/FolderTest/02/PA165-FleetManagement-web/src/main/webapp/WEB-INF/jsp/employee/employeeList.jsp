<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<p class="title"><spring:message code="employee.list.title"/>:</p>

<a href="<c:url value="/employee/new" />" class="addButton"><span><spring:message code="employee.newEmployee"/></span></a>


<table id="employeeAll" border="0">   
    <tr class="faty"> 
        <td><spring:message code="employee.firstName"/></td>
        <td><spring:message code="employee.lastName"/></td>
        <td><spring:message code="employee.gender"/></td>
        <td><spring:message code="employee.userClass"/></td>
        <td><spring:message code="employee.email"/></td>
        <td><spring:message code="employee.phoneNumber"/></td>
        <td><spring:message code="edit"/></td>
        <td><spring:message code="delete"/></td>
    </tr> 

    <c:forEach items="${employees}" var="employee">
        <tr>
            <td><c:out value="${employee.firstName}"/></td>
            <td><c:out value="${employee.lastName}"/></td>
            <td><c:out value="${employee.gender}"/></td>
            <td><c:out value="${employee.userClass}"/></td>
            <td><c:out value="${employee.email}"/></td>
            <td><c:out value="${employee.phoneNumber}"/></td>
            <td> 
                <a href="<c:url value="/employee/edit/${employee.id}" />"  id="bEdit"></a>
            </td>
            <td>
                <a href="<c:url value="/employee/delete/${employee.id}" />" onclick="return(confirm('Do you really want to delete ?'));" id="bDelete"></a>
            </td>
        </tr>
    </c:forEach>
</table>  

