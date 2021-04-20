<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.StoresActionBean" var="actionBean"/>
<s:layout-render name="/layout.jsp" titlekey="stores" page="stores">
    <s:layout-component name="header">
        <!-- adding to header -->
    </s:layout-component>
    <s:layout-component name="body">

        <%@include file="blocks/storeCreateForm.jsp"%>

        <div class="row">
            <div class="col-md-6"></div>
            <div class="col-md-6">

                <div class="btn-group pull-right">

                    <s:link class="btn btn-default" href="/stores/search">
                         <span class="glyphicon glyphicon-search"></span>
                        <f:message key="store.search"/>
                    
                    </s:link>

                    <c:if test="${actionBean.userIsPermittedToCreate}">
                        <button class="btn btn-default" data-toggle="modal" data-target="#createStoreModal">
                            <span class="glyphicon glyphicon-plus-sign"></span>
                            <f:message key="store.create" />
                        </button>
                    </c:if>
                </div>
            </div>

        </div>

        <%@include file="blocks/storesList.jsp"%>

    </s:layout-component>
</s:layout-render>