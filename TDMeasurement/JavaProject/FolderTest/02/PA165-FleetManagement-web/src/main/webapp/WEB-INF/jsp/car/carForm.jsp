<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib  prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#form2").validationEngine('attach', {bindMethod:"live"});
		});
	</script>
<p class="title"><spring:message code="car.edit.title"/>:</p> 
<form:form commandName="carDTO" method="post" id="form2" class="formular">
    <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.carDTO'].allErrors}">
        <div class="error" >                    
            <form:errors path="*" />
        </div>
    </c:if> 

    <table border="0" id="tab1">
        <tr>
            <td><form:label path="id"></form:label></td>
            <td class="value"><form:hidden path="id" />${car.id}</td>
        </tr>


        <tr>
            <td rowspan="3" colspan="2"><div id="img2"></div> 
            <td><label><spring:message code="car.brand"/></label></td>
            <td><form:input path="brand"  maxlength="50" class="validate[required,custom[onlyLetterSp]] text-input" /></td>
        </tr>
        <tr>
            <td><label><spring:message code="car.model"/></label></td>
            <td><form:input path="model"  maxlength="50" class="validate[required] text-input" /></td>
        </tr>
        <tr>
            <td><label><spring:message code="car.engine"/></label></td>
            <td><form:input path="engine"  maxlength="50"   class="validate[required,custom[onlyNumberSp]] text-input" /></td>
        </tr>  
        <tr><td><label><spring:message code="car.spz"/></label></td>
            <td><form:input path="licensePlate"  maxlength="50" class="validate[required] text-input" /></td>  
            <td><label><spring:message code="car.mileage"/></label></td>
            <td><form:input path="mileage"  maxlength="50"  class="validate[required,custom[onlyNumberSp]] text-input" /></td> 
        </tr> 
        <tr> 
            <td><label><spring:message code="car.userClass"/></label></td>
            <td><form:select path="userClass">
                    <c:forEach var="item" items="${userClass}">
                        <form:option value="${item}">
                            ${item}
                        </form:option>
                    </c:forEach>
                </form:select>
            </td> 
            <td><label><spring:message code="car.color"/></label></td>
            <td><form:input path="color"  maxlength="50" class="validate[required,custom[onlyLetterSp]] text-input"/></td>  
        </tr>         
        <tr>
            <td><a href="<c:url value="/car/list" />"><div  id="buttonBack"></div></a></td>
            <td></td>
            <td></td>

            <td>                
                <button class="submit" name="send" value="edit" type="submit" id="buttonSave"></button>
            </td>
        </tr>
    </table>  
</form:form>

