<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>

<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
    $(function() {
        // DATE FROM
        // insert alt field
        $('<input type="text" id="dateControl">').insertBefore("#from");
        // get date
        var from = $('#from').val();
        // convert to localized date format
        var dateControl = $.datepicker.parseDate('yy-mm-dd', from);
        // put localized date to alt field
        $('#dateControl').val($.datepicker.formatDate('<fmt:message key="date.pattern.jquery.ui.datepicker"/>', dateControl));
        // hide original field
        $("#from").hide();
        // set datepicker
        $('#dateControl').datepicker({
            dateFormat: '<fmt:message key="date.pattern.jquery.ui.datepicker"/>',
            altFormat: 'yy-mm-dd',
            altField: '#from'
        });

        // DATE TO
        // insert alt field
        $('<input type="text" id="completionDate">').insertBefore("#to");
        // get date
        var to = $('#to').val();
        // convert to localized date format
        var completionDate = $.datepicker.parseDate('yy-mm-dd', to);
        // put localized date to alt field
        $('#completionDate').val($.datepicker.formatDate('<fmt:message key="date.pattern.jquery.ui.datepicker"/>', completionDate));
        // hide original field
        $("#to").hide();
        // set datepicker
        $('#completionDate').datepicker({
            dateFormat: '<fmt:message key="date.pattern.jquery.ui.datepicker"/>',
            altFormat: 'yy-mm-dd',
            altField: '#to'
        });


    });

</script>

<p class="title"><spring:message code="service.list.title"/></p>
<form:form commandName="serviceIntervalDTO" method="post" id="form2" class="formular">
    <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.serviceIntervalDTO'].allErrors}">
        <div class="error" >                    
            <form:errors path="*" />
        </div>
    </c:if> 

    <table border="0" id="tab1">
        <tr>
            <td><form:label path="id"></form:label></td>
            <td class="value"><form:hidden path="id" />${serviceInterval.id}</td>
        </tr>

        <tr>
            <td><label><spring:message code="service.service"/></label></td>
            <td><form:input path="service"  maxlength="50" class="validate[required] text-input" /></td> 

            <td><label><spring:message code="service.mileage"/></label></td>
            <td><form:input path="mileageControl"  maxlength="50" class="validate[required,custom[onlyNumberSp]] text-input" /></td>
        </tr>
        <tr>
            <td><label><spring:message code="service.dateControl"/></label></td>
            <td><form:input path="dateControl"  type="date" id="from" cssClass="date"/></td> 
            <td><label><spring:message code="service.completionDate"/></label></td>
            <td><form:input path="completionDate" type="date" id="to" cssClass="date"/></td> 
        </tr>  
        <td><a href="<c:url value="/service/list" />"><div  id="buttonBack"></div></a></td>
        <td></td>
        <td></td>
        <td><button name="send" value="edit" type="submit" id="buttonSave"></button> </td>
    </tr>
</table> 
</form:form>
<script>
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#form2").validationEngine('attach', {bindMethod:"live"});
		});
	</script>