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
        $('<input type="text" id="dateFrom">').insertBefore("#from");
        // get date
        var from = $('#from').val();
        // convert to localized date format
        var dateFrom = $.datepicker.parseDate('yy-mm-dd', from);
        // put localized date to alt field
        $('#dateFrom').val($.datepicker.formatDate('<fmt:message key="date.pattern.jquery.ui.datepicker"/>', dateFrom));
        // hide original field
        $("#from").hide();
        // set datepicker
        $('#dateFrom').datepicker({
            dateFormat: '<fmt:message key="date.pattern.jquery.ui.datepicker"/>',
            altFormat: 'yy-mm-dd',
            altField: '#from'
        });

        // DATE TO
        // insert alt field
        $('<input type="text" id="dateTo">').insertBefore("#to");
        // get date
        var to = $('#to').val();
        // convert to localized date format
        var dateTo = $.datepicker.parseDate('yy-mm-dd', to);
        // put localized date to alt field
        $('#dateTo').val($.datepicker.formatDate('<fmt:message key="date.pattern.jquery.ui.datepicker"/>', dateTo));
        // hide original field
        $("#to").hide();
        // set datepicker
        $('#dateTo').datepicker({
            dateFormat: '<fmt:message key="date.pattern.jquery.ui.datepicker"/>',
            altFormat: 'yy-mm-dd',
            altField: '#to'
        });


    });

</script>

<style type="text/css">
    #choose-dates  {
        border:  0px none;
    }
    #choose-dates td {
        vertical-align: middle;
    }
    form {
        margin: 0px;
    }
    .detail {
        padding: 5px;
        margin: 5px 0;
    }
</style>


<div>

    <div class="detail">
        <form method="get" action="cars" >
            <table id="choose-dates">
                <tr>
                    <td><label for="from" ><spring:message code="journey.pickUpDate"/></label></td>
                    <td><input type="text" name="from" id="from" value="<c:out value="${dateFrom}"/>" /></td>
                    <td><label for="to" ><spring:message code="journey.returnDate"/></label></td>
                    <td><input type="text" name="to" id="to" value="<c:out value="${dateTo}"/>" /></td>
                    <td><input type="submit" /></td>
                </tr>
            </table>
        </form>
    </div>

    <table class="grid">
        <colgroup>

            <col style="width: 80px;"/>
            <col style="width: 120px;"/>
            <col span="2" />
            <col style="width: 230px;"/>
        </colgroup>
        <tr>
            <th><spring:message code="car.vin"/></th>
            <th><spring:message code="car.brand"/></th>
            <th><spring:message code="car.model"/></th>
            <th></th>
        </tr>
        <c:forEach  items="${cars}" var="vehicle">
            <tr>
                <td><c:out value="${car.licensePlate}"/></td>
                <td><c:out value="${car.brand}"/></td>
                <td><c:out value="${car.model}"/></td>
                <td>
                    <a href="<c:url value="/reserveForm?id=${car.id}&from=${dateFrom}&to=${dateTo}" />">reserve car</a>
                </td>
            </tr>
        </c:forEach>

    </table><br>


</div>