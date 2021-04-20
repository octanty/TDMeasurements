<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ page session="true" %> 
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><spring:message code="index.title"/></title>
        <spring:url value="/resources/css/style.css" var="resourceUrl"/>
        <link rel="stylesheet" type="text/css" href="${resourceUrl}" /> 
                
         <link rel="stylesheet" href="/pa165/resources/css/validationEngine.jquery.css" type="text/css"/>
	 <script src="/pa165/resources/js/jquery-1.8.2.min.js" type="text/javascript">
  	</script>
	<script src="/pa165/resources/js/languages/jquery.validationEngine-<spring:message code="index.language"/>.js" type="text/javascript" charset="utf-8">
	</script>
	<script src="/pa165/resources/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8">
	</script>
        
    </head>
    <body>
        <div id="wrapper">
            <div id="header">  
                <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/picture/logoHeader.png" /></a>
                 <a id="logout" href="<c:url value="/j_spring_security_logout" />">Log out</a>
                <ul>
                    <li><a href="<c:url value="/employee/view/1" />"><div class="img1" ></div><p><spring:message code="navigation.employee"/></p></a></li>
                    <li><a href="<c:url value="/cars/" />"><div class="img3"></div><p><spring:message code="navigation.car"/></p></a></li>
                    <li><a href="<c:url value="/journeys/" />"><div class="img2" ></div><p><spring:message code="navigation.journey"/></p></a></li>
                </ul> 
            </div>
            <div id="error">  
                <c:if test="${not empty error}">
                    <div class="error">
                        <fmt:message key="${error}" />
                    </div>
                    <%
                        synchronized (session) {
                            session.removeAttribute("error");
                        }
                    %>
                </c:if>

                <c:if test="${not empty message}">
                    <div class="success">
                        <fmt:message key="${message}" />
                    </div>
                    <%
                        synchronized (session) {
                            session.removeAttribute("message");
                        }
                    %>
                </c:if>
            </div>
            <div id="center">             
                <sitemesh:write property='body'/>
            </div>  
        </div>
        <div id="footer">
            <div id="footerCenter">
                <img src="${pageContext.request.contextPath}/resources/picture/logoFooter.png" alt="logo">
                <p><spring:message code="footer.copyright"/></p>
            </div>
        </div> 
    </body>
</html>