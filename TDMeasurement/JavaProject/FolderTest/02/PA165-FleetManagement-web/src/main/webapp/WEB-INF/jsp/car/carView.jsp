<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<p class="title"></p> 
<div id="img22"><img src="${pageContext.request.contextPath}/resources/picture/car1.png" alt="image" id="imgInide"/></div>   
<div id="right">  
    <table border="0" class="form">  
        <tr>
            <th colspan="2"><h1>${car.brand} ${car.model}</h1></th>
        </tr>
        <tr>
            <td><spring:message code="car.spz"/></td><td class="col2">${car.licensePlate}</td>
        </tr> 
        <tr>
            <td><spring:message code="car.engine"/></td><td class="col2">${car.engine}</td>
        </tr><td>
        <tr>
            <td><spring:message code="car.color"/></td><td class="col2">${car.color}</td>
        </tr> 
        <tr>
            <td><spring:message code="car.mileage"/></td><td class="col2">${car.mileage}</td>
        </tr>
        <tr>
            <td><spring:message code="car.userClass"/></td><td class="col2">${car.userClass}</td>
        </tr>    
    </table>   
</div> 
<button name="send" value="back" type="submit"  id="buttonBack2"></button> 
