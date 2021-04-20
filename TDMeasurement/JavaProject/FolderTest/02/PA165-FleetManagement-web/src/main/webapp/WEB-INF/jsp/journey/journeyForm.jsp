<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib  prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>

<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
    $(function() {
        // DATE FROM
        // insert alt field
        $('<input type="text" id="pickUpDate">').insertBefore("#from");
        // get date
        var from = $('#from').val();
        // convert to localized date format
        var pickUpDate = $.datepicker.parseDate('yy-mm-dd', from);
        // put localized date to alt field
        $('#pickUpDate').val($.datepicker.formatDate('<fmt:message key="date.pattern.jquery.ui.datepicker"/>', pickUpDate));
        // hide original field
        $("#from").hide();
        // set datepicker
        $('#pickUpDate').datepicker({
            dateFormat: '<fmt:message key="date.pattern.jquery.ui.datepicker"/>',
            altFormat: 'yy-mm-dd',
            altField: '#from'
        });

        // DATE TO
        // insert alt field
        $('<input type="text" id="returnDate">').insertBefore("#to");
        // get date
        var to = $('#to').val();
        // convert to localized date format
        var returnDate = $.datepicker.parseDate('yy-mm-dd', to);
        // put localized date to alt field
        $('#returnDate').val($.datepicker.formatDate('<fmt:message key="date.pattern.jquery.ui.datepicker"/>', returnDate));
        // hide original field
        $("#to").hide();
        // set datepicker
        $('#returnDate').datepicker({
            dateFormat: '<fmt:message key="date.pattern.jquery.ui.datepicker"/>',
            altFormat: 'yy-mm-dd',
            altField: '#to'
        });


    });

</script>

<p class="title"><spring:message code="journey.edit.title"/>:</p> 
<form:form commandName="journeyDTO" method="post" id="form2" class="formular">
    <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.journeyDTO'].allErrors}">
        <div class="error" >                    
            <form:errors path="*" />
        </div>
    </c:if> 

    <table border="0" id="tab1"> 
         <tr>
            <td><form:label path="id"></form:label></td>
            <td class="value"><form:hidden path="id" />${journey.id}</td>
        </tr>
        <tr>
            <td><label><spring:message code="journey.car"/></label></td> 
            <td>
                <form:select path="car">
                    <c:forEach var="item1" items="${cars}">
                        <form:option value="${item1.id}">
                            #<c:out value="${item1.id}"/>: [<c:out value="${item1.licensePlate}"/>] <c:out value="${item1.brand}"/> <c:out value="${item1.model}"/>  
                        </form:option>
                    </c:forEach>
                </form:select>          
            </td> 	
            <td><label><spring:message code="journey.employee"/></label></td>
            <td>
                <form:select path="employee">
                    <c:forEach var="item" items="${employees}">
                        <form:option value="${item.id}">
                            #<c:out value="${item.id}" />: <c:out value="${item.firstName}" /> <c:out value="${item.lastName}" /> 
                        </form:option>
                    </c:forEach>
                </form:select>
            </td>   
        </tr>
        <tr>
            <td><label><spring:message code="journey.pickUpDate"/></label></td>
            <td><form:input path="pickUpDate"  type="date" id="from" cssClass="date" class="validate[required] text-input" /></td>
        </tr>   
        <tr>
            <td><label><spring:message code="journey.returnDate"/></label></td>
            <td><form:input path="returnDate" type="date" id="to" cssClass="date" class="validate[required] text-input"/></td> 
            <td><label><spring:message code="journey.mileage"/></label></td>
            <td><form:input path="mileage"  maxlength="50" class="validate[required,custom[onlyNumberSp]] text-input" /></td>
        </tr>  
        <td><a href="<c:url value="/journey/list" />"><div  id="buttonBack"></div></a></td>
        <td></td>
        <td></td>
        <td>
            <button class="submit" name="send" value="edit" type="submit" id="buttonSave"></button> 
        </td>
    </tr>
</table> 
</form:form>
<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#form2").validationEngine('attach', {bindMethod:"live"});
		});
	</script>