<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib  prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#journey-create-cancel").on("click",function(){
                if(confirm("<fmt:message key="employee.journey.confirmLeave" />")) {
                    window.location = "<c:url value="/cars" />";
                }
            });
        });
    </script>
    <style type="text/css" media="all">
        .detail {
            width: 500px;
            text-align: center;
            margin: 0 auto;
        }
        input[type=text],  select, textarea  {
            width: 280px;
        }
    </style>

    
    <div class="form">
        <form:form commandName="journeyDTO" method="post" modelAttribute="journeyDTO">
             <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.journeyDTO'].allErrors}">
                    <div class="form-errors">
                        <form:errors path="*" cssStyle="color:red" />
                    </div>
            </c:if>
            <table class="detail">
                <colgroup>
                    <col style="width: 200px" />
                    <col style="width: 300px" />
                </colgroup>
                <tr>
                    <td><form:label path="mileage"><spring:message code="journey.mileage"/>:</form:label></td>
                    <td>
                        <form:input readonly="true" path="mileage" />
                        <form:errors path="mileage" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <td><form:label path="employee"><spring:message code="journey.employee"/>:</form:label></td>
                    <td>
                        <form:input readonly="true" path="employee.firstName" />
                        <form:errors path="user" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <td><form:label path="car"><spring:message code="journey.car"/>:</form:label></td>
                    <td>
                        <form:input readonly="true" path="car.brand"  />
                        <form:errors path="car" cssClass="error" />
                    </td>
                </tr>

                <tr>
                    <td><form:label path="pickUpDate"><spring:message code="journey.pickUpDate"/>:</form:label></td>
                    <td>
                        <form:input readonly="true" path="pickUpDate" />
                        <form:errors path="pickUpDate" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <td><form:label path="returnDate"><spring:message code="journey.returnDate"/>:</form:label></td>
                    <td>
                        <form:input readonly="true" path="returnDate" />
                        <form:errors path="returnDate" cssClass="error" />
                    </td>
                </tr>
                <!--<tr>
                    <td><form:label path="state"><fmt:message key="drive.state" />:</form:label></td>
                    <td>
                        <form:input readonly="true" path="state" maxlength="17" />
                        <fmt:message key="${item.code}" />
                        <form:errors path="state" cssClass="error" />
                    </td>
                </tr>-->
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="submit" value="<fmt:message key="actions.save" />"/>
                        <input type="button" onclick="cancelReservation" id="journey-create-cancel" value="<fmt:message key="actions.cancel" />" />
                    </td>
                </tr>
            </table>
        </form:form>

    </div>