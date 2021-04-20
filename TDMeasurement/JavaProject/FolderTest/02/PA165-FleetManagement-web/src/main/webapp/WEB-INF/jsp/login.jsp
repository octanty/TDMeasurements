<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="login" ><h2><spring:message code="login.header"/></h2><form name='f' action="<c:url value='j_spring_security_check' />" method="post"  >
        <table border="0" id="form3">
            <tr>
                <td><label><spring:message code="login.email"/></label></td>
                <td><input name="j_username" value="" type="text"></td>
            </tr>
            <tr>
                <td><label><spring:message code="login.password"/></label></td>
                <td><input name="j_password" value="" type="password" ></td>
            </tr> 
            <td></td>
            <td><button name="submit" value="delete" type="submit" ><spring:message code="login.login"/></button> </td>
        </table>
</div> 