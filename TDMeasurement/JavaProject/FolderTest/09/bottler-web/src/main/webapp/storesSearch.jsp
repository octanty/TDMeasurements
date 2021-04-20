<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="stores" page="stores">
    <s:layout-component name="header">
        <!-- adding to header -->
    </s:layout-component>
    <s:layout-component name="body">

          <h2><f:message key="store.search"/></h2> 
          
        <%@include file="blocks/storeSearchForm.jsp"%>

        <h3><f:message key="search.results"/></h3> 
        
        
        
        <%@include file="blocks/storesList.jsp"%>

    </s:layout-component>
</s:layout-render>