<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib  prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
          <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.js"></script> 
          <script type="text/javascript"> 
            $(function() {
                $("#journey-finish").validate({ 
                    rules: { 
                        distance: { required:   true, 
                                    number:     true,
                                    min:        0}
                        }, 
                    messages: { 
                        distance: 	{required:  " <fmt:message key="error.drive.distance" />", 
                                     number:    " <fmt:message key="error.drive.distance.number" />",
                                     min:       " <fmt:message key="error.drive.distance.gtZero" />"}
                        } 
                  }); 
            }); 

    </script>

    <fmt:message var="datePattern" key="date.pattern.joda" /> 
    <div class="form">

        <form:form commandName="journey" method="post" id="journey-finish">
            <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.journey'].allErrors}">
                    <div class="form-errors">
                        <form:errors path="*" cssStyle="color:red" />
                    </div>
            </c:if>
            <table class="detail">
                <tr>
                    <th><form:label path="mileage"><spring:message code="journey.mileage"/>(km):</form:label></th>
                    <td><form:input path="mileage" maxlength="6" /> <form:errors path="mileage" /></td>
                    <td>
                        <input type="submit" value="<fmt:message key="drives.action.finish" />"/>
                    </td>
                </tr>

                <tr>

                </tr>
            </table>
        </form:form>

    </div>
    <br /><br />

    <div id="drive" >
        <table class="detail">
            <colgroup>
                <col style="width: 80px;" />
            </colgroup>
            <tr>
                <th>journey</th>
                <th><spring:message code="journey.pickUpDate"/></th>
                <th><spring:message code="journey.returnDate"/></th>
            </tr>
            <tr>
                <td>${journey.id}</td>
                <td><joda:format pattern="${datePattern}" value="${journey.pickUpDate}"/></td>
                <td><joda:format pattern="${datePattern}" value="${journey.returnDate}"/></td>
            </tr>
        </table>
    </div>
    <br/>	

    <div id="vehicle" >
        <table class="detail">
            <colgroup>
                <col style="width: 80px;" />
            </colgroup>
            <tr>
                <th>car</th>
                <th><spring:message code="car.vin"/></th>
                <th><spring:message code="car.brand"/></th>
                <th><spring:message code="car.model"/></th>
            </tr>
            <tr>
                <td><c:out value="${journey.car.id}"/></td>
                <td><c:out value="${journey.car.licensePlate}"/></td>                
                <td><c:out value="${journey.car.brand}"/></td>
                <td><c:out value="${journey.car.model}"/></td>
           </tr>
        </table>
    </div>
    <br/>