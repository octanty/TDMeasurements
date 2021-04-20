<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib  prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("a.link-journey-cancel").on("click",function(){
                event.stopPropagation();
                if(!confirm("<fmt:message key="journeys.confirm.cancel" />")) {
                    event.preventDefault();
                }       
             });
             $("a.link-journey-start").on("click",function(){
                event.stopPropagation();
                if(!confirm("<fmt:message key="journeys.confirm.start" />")) {
                    event.preventDefault();
                }       
             });
         });
    </script>

    <fmt:message var="datePattern" key="date.pattern.joda" /> 
    <div>
        <a href="<c:url value="/cars" />">reserve</a>
         | 
        <c:if test="${journeys.size() > 0}">
            <fmt:message key="journeys.index.found" /> ${journeys.size()}
        </c:if>
        <c:if test="${journeys.size() < 1}">
            <fmt:message key="journeys.index.nofound" />
        </c:if>
    </div>
    <table class="grid">
        <colgroup>
            <col style="width: 100px;" />
            <col style="width: 100px;" />
            <col style="width: 100px;" />
            <col style="width: 250px;" />
            <col style="min-width: 100px;" />
        </colgroup>
        <tr>
            <th><spring:message code="journey.pickUpDate"/></th>
            <th><spring:message code="journey.returnDate"/></th>
            <th>state</th>
            <th>car</th>
            
            <th>actions</th>
        </tr>
        
        <c:forEach var="item" items="${journeys}">
            <tr>
                <!-- < fmt : formatDate value="" / > --->
                <td><joda:format pattern="${datePattern}" value="${item.dateFrom}"/></td>
                <td><joda:format pattern="${datePattern}" value="${item.dateTo}"/></td>
                <td><fmt:message key="${item.state.code}" /></td>
                <td> #<c:out value="${item.car.id}"/>: <span class="registrationPlate">[<c:out value="${item.vehicle.registrationPlate}"/>]</span> <c:out value="${item.vehicle.brand}"/> <c:out value="${item.vehicle.type}"/></td>
                <c:choose>
                    <c:when test="${item.state.name == 'reserved'}">
                        <td>
                           <a href="<c:url value="journeys/start?id=${item.id}" />" class="link-journey-start"><fmt:message key="journeys.changeState.start" /></a> | 
                            
                            <a href="<c:url value="journeys/cancel?id=${item.id}" />" class="link-journey-cancel"><fmt:message key="journeys.changeState.cancel" /></a>
                        </td>
                    </c:when>
                    <c:when test="${item.state.name == 'ongoing'}">
                        <td>
                            <a href="<c:url value="journeys/finish?id=${item.id}" />" class="link-journey-finish"><fmt:message key="journeys.changeState.finish" /></a>
                        </td>
                    </c:when>
                    <c:otherwise>
                        
                        <td>
                            &nbsp;
                        </td>
                    </c:otherwise>
                </c:choose>
                
            </tr>
        </c:forEach>
        
    </table>
