<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
       <div class="row">
        <div class='col-md-6'>           
            <h2 class='text-danger'>403 - You are not authorized to view this content</h2>
           <p>Please <a href="${pageContext.request.contextPath}/login.jsp" ><span class="glyphicon glyphicon-off"></span>&nbsp;<f:message key="navigation.login"/></a> with a higher authorization account first and contribute to the survival of human kind. <strong>Just do it before your face gets eaten by a mutated zombie</strong>
        </div>
           <div class="col-md-6">
             <img class="img-rounded" src="${pageContext.request.contextPath}/docs-assets/images/403c.jpg" alt="403 Not authorized" height="350" width="400"/>
        </div>
       </div>
    </s:layout-component>
</s:layout-render>

