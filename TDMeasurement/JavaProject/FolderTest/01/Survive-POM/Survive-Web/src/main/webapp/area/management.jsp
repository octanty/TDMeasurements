<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <h1><f:message key="area.title"/></h1>
    
        <s:useActionBean beanclass="com.muni.fi.pa165.area.AreaActionBean" var="actionBean"/>

        <p><f:message key="area.list.allareas"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="area.id"/></th>
                <th><f:message key="area.name"/></th>
                <th><f:message key="area.terrain"/></th>
                <th><f:message key="area.description"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.areas}" var="area">
                <tr>
                    <td>${area.id}</td>
                    <td><c:out value="${area.id}"/></td>
                    <td><c:out value="${area.name}"/></td>
                    <td><c:out value="${area.terrain}"/></td>
                    <td><c:out value="${area.description}"/></td>
                    <td>
                     <s:link beanclass="com.muni.fi.pa165.area.AreaActionBean" event="edit"><s:param name="area.id" value="${area.id}"/>edit</s:link>
                    </td>
                    <td>
                        <s:form beanclass="com.muni.fi.pa165.area.AreaActionBean">
                            <s:hidden name="area.id" value="${area.id}"/>
                            <s:submit name="delete"><f:message key="area.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="com.muni.fi.pa165.area.AreaActionBean">
            <fieldset><legend><f:message key="area.list.newarea"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">Save new area</s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>
        