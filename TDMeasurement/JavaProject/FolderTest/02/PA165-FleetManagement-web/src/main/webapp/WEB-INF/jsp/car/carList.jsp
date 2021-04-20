<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<p class="title"><spring:message code="car.list.title"/>:</p>

<a href="<c:url value="/car/new" />" class="addButton"><span1><spring:message code="car.newCar"/></span1></a>

<table id="employeeAll" border="0">   
    <tr class="faty"> 
        <td><spring:message code="car.brand"/></td>
        <td><spring:message code="car.model"/></td>
        <td><spring:message code="car.color"/></td>
        <td><spring:message code="car.vin"/></td>
        <td><spring:message code="car.engine"/></td>
        <td><spring:message code="car.mileage"/></td>
        <td><spring:message code="car.serviceInterval"/></td>
        <td><spring:message code="car.userClass"/></td>
        <td><spring:message code="service"/></td>
        <td><spring:message code="edit"/></td>
        <td><spring:message code="delete"/></td>       
    </tr> 

    <c:forEach items="${cars}" var="car">
        <tr>            
            <td><c:out value="${car.brand}"/></td>
            <td><c:out value="${car.model}"/></td>
            <td><c:out value="${car.color}"/></td>
            <td><c:out value="${car.licensePlate}"/></td>
            <td><c:out value="${car.engine}"/></td>
            <td><c:out value="${car.mileage}"/></td>
            <td>1</td><!-- premenna sa vola: car.serviceInterval -->
            <td><c:out value="${car.userClass}"/></td>

            <td>
                <a href="<c:url value="/service/edit/1" />"  id="bService"></a>
            </td>

            <td>
                <a href="<c:url value="/car/edit/${car.id}" />"  id="bEdit"></a>
            </td>

            <td>
                <a href="<c:url value="/car/delete/${car.id}" />" onclick="return(confirm('Do you really want to delete ?'));" id="bDelete"></a>
            </td>
        </tr>
    </c:forEach>
</table>  
