<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#form2").validationEngine('attach', {bindMethod:"live"});
		});
	</script>
<p class="title"><spring:message code="employee.edit.title"/>:</p> 

<form:form commandName="employeeDTO" method="post" id="form2" class="formular" >
    <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.employeeDTO'].allErrors}">
        <div class="error" >                    
            <form:errors path="*" />
        </div>
    </c:if>

    <table border="0" id="tab1">
        <tr>
            <td><form:label path="id" ></form:label></td>
            <td class="value"><form:hidden path="id" />${employee.id}</td>
        </tr>

        <tr>
            <td rowspan="3" colspan="2"><div id="img1"></div></td>
            <td><label><spring:message code="employee.firstName"/></label></td>
            <td><form:input path="firstName"  maxlength="50" class="validate[required,custom[onlyLetterSp]] text-input"/></td>
 
        </tr>
        <tr>
            <td><label><spring:message code="employee.lastName"/></label></td>
            <td><form:input path="lastName"  maxlength="50" class="validate[required,custom[onlyLetterSp]] text-input"/></td>
        </tr>
        <tr>
            <td><label><spring:message code="employee.email"/></label></td>
            <td><form:input path="email"  maxlength="50" class="validate[required,custom[email]] text-input" /></td> 
        </tr>    
        <tr>
            <td><label><spring:message code="employee.userClass"/></label></td>
            <td><form:select path="userClass">
                    <c:forEach var="item" items="${userClass}">
                        <form:option value="${item}">
                            ${item} 
                        </form:option>
                    </c:forEach>
                </form:select>
            </td> 
            <td><label><spring:message code="employee.gender"/></label></td>
            <td>
                <form:select path="gender">
                    <c:forEach var="item" items="${gender}">
                        <form:option value="${item}">
                            ${item}
                        </form:option>
                    </c:forEach>
                </form:select>            
            </td>
        </tr>
        <tr>
            <td><label><spring:message code="employee.password"/></label></td>
            <td><form:input path="password"  maxlength="50" type="password" class="validate[required] text-input" /></td>
            <td><label><spring:message code="employee.phoneNumber"/></label></td>
            <td><form:input path="phoneNumber"  maxlength="50" class="validate[required,custom[phone]] text-input"/></td>
        </tr>        
        <tr>
            <td><a href="<c:url value="/employee/list" />"><div  id="buttonBack"></div></a></td>
            <td></td>
            <td></td>
            <td>
                 <button class="submit" name="send" value="edit" type="submit" id="buttonSave"></button> 
            </td>
        </tr>
    </table>  

</form:form>
