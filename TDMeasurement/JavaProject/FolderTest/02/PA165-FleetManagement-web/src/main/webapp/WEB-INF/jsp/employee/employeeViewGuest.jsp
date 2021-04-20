<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
 <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<p class="title"><spring:message code="employee.view.title.guest"/>:</p>
<img src="${pageContext.request.contextPath}/resources/picture/avatar3.png" id="avatar" />         
<div id="right">  
          
    <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
  
    <security:authorize ifAnyGranted="ROLE_ADMIN">
 	         You are an Administrator.<br/>
 	        </security:authorize>
    <security:authorize ifAnyGranted="ROLE_USER">
 	         You are an USER.<br/>
 	        </security:authorize>
    
    <table border="0" class="form">  
        <tr>
            <th colspan="2"><h1>Bc. Ján Švec</h1></th>
        </tr>
        <tr>
            <td><spring:message code="employee.userClass"/></td><td class="col2">Student</td>
        </tr> 
        <tr>
            <td><spring:message code="employee.email"/></td><td class="col2">418110@mail.muni.cz</td>
        </tr>   
        <tr>
            <td><spring:message code="employee.guest.uco"/></td><td class="col2">420072</td>
        </tr>  
    </table>   
</div> 

        <img src="${pageContext.request.contextPath}/resources/picture/avatar1.png" id="avatar" />         
<div id="right">  
    <table border="0" class="form">  
        <tr>
            <th colspan="2"><h1>Bc. Ľubomír Lacika</h1></th>
        </tr>
        <tr>
            <td><spring:message code="employee.userClass"/></td><td class="col2">Student</td>
        </tr> 
        <tr>
            <td><spring:message code="employee.email"/></td><td class="col2">418110@mail.muni.cz</td>
        </tr>   
        <tr>
            <td><spring:message code="employee.guest.uco"/></td><td class="col2">418110</td>
        </tr>  
    </table>   
</div> 

            <img src="${pageContext.request.contextPath}/resources/picture/avatar2.png" id="avatar" />         
<div id="right">  
    <table border="0" class="form">  
        <tr>
            <th colspan="2"><h1>Bc. Eduard Dobai</h1></th>
        </tr>
        <tr>
            <td><spring:message code="employee.userClass"/></td><td class="col2">Student</td>
        </tr> 
        <tr>
            <td><spring:message code="employee.email"/></td><td class="col2">420065@mail.muni.cz</td>
        </tr>   
        <tr>
            <td><spring:message code="employee.guest.uco"/></td><td class="col2">420065</td>
        </tr>  
    </table>   
</div>  

            <img src="${pageContext.request.contextPath}/resources/picture/avatar4.png" id="avatar" />         
<div id="right">  
    <table border="0" class="form">  
        <tr>
            <th colspan="2"><h1>Bc. Peter Pavlík</h1></th>
        </tr>
        <tr>
            <td><spring:message code="employee.userClass"/></td><td class="col2">Student</td>
        </tr> 
        <tr>
            <td><spring:message code="employee.email"/></td><td class="col2">00000@mail.muni.cz</td>
        </tr>   
        <tr>
            <td><spring:message code="employee.guest.uco"/></td><td class="col2">000000</td>
        </tr>  
    </table>   
</div>  
         
 