<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<p class="title"><spring:message code="employee.view.title"/>:</p>
<img src="${pageContext.request.contextPath}/resources/picture/avatar.png" style="float:left" />        
<div id="right">  
    <table border="0" class="form">  
        <tr>
            <th colspan="2"><h1>${employee.firstName} ${employee.lastName}</h1></th>
        </tr>
        <tr>
            <td><spring:message code="employee.gender"/></td><td class="col2">${employee.gender}</td>
        </tr> 
        <tr>
            <td><spring:message code="employee.userClass"/></td><td class="col2">${employee.userClass}</td>
        </tr><td> 
        <tr>
            <td><spring:message code="employee.email"/></td><td class="col2">${employee.email}</td>
        </tr>
        <tr>
            <td><spring:message code="employee.phoneNumber"/></td><td class="col2">${employee.phoneNumber}</td>
        </tr>    
    </table>   
</div> 
