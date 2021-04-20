<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
       <div class="row">
        <div class='col-md-6'>           
            <h2 class='text-danger'>404 - I'm afraid the resource you requested, could not be found</h2>
           <p>Please ensure that you have entered the correct link to the resource. If the problem persist, it does not help complaining. <strong>Everyone who cared is probably dead.</strong>
        </div>
           <div class="col-md-6">
             <img class="img-rounded" src="${pageContext.request.contextPath}/docs-assets/images/403b.jpg" alt="404 Not found" height="250" width="400"/>
        </div>
       </div>
    </s:layout-component>
</s:layout-render>

