<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <h1><f:message key="area.title"/></h1>

        <s:useActionBean beanclass="com.muni.fi.pa165.actions.area.AreaActionBean" var="actionBean"/>
        <s:errors/>
        <p><f:message key="area.list.allareas"/></p>
        <div class="table-responsive">
            <table class="table">
                <tr>
                    <th><f:message key="area.res.id"/></th>
                    <th><f:message key="area.res.name"/></th>                
                    <th><f:message key="area.res.terrain"/></th>
                    <th><f:message key="area.res.description"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${actionBean.areas}" var="area">
                    <tr>
                        <td><c:out value="${area.id}" /></td>
                        <td><c:out value="${area.name}" /></td>
                        <td><c:out value="${area.terrain}" /></td>
                        <td><c:out value="${area.description}" /></td>                 

                        <td>     
                              <security:authorize ifAnyGranted="ROLE_ADMIN">
                             <s:form beanclass="com.muni.fi.pa165.actions.area.AreaActionBean" action="edit">
                                <s:hidden name="area.id" value="${area.id}"/>
                                <s:submit class="btn btn-warning" name="edit"><f:message key="area.list.edit"/></s:submit>
                            </s:form>
                              </security:authorize>
                        </td>
                        <td>        
                              <security:authorize ifAnyGranted="ROLE_ADMIN">
                            <s:form beanclass="com.muni.fi.pa165.actions.area.AreaActionBean" action="delete">
                                <s:hidden name="area.id" value="${area.id}"/>
                                <s:submit class="btn btn-danger" name="delete"><f:message key="area.list.delete"/></s:submit>
                            </s:form>
                              </security:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
            <s:form beanclass="com.muni.fi.pa165.actions.area.AreaActionBean" class="form-horizontal" action="add">
            <fieldset><legend><f:message key="area.list.newarea"/></legend>
                <s:hidden  name="area.id" value="${area.id}" />
                <%@include file="form.jsp"%>
                <s:submit class="btn btn-info" name="add">Save new area</s:submit>
                
            </fieldset>
    </s:form>
</s:layout-component>
</s:layout-render>
