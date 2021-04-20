<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="user.title">
    <s:layout-component name="body">
        <h1><f:message key="user.title"/></h1>

        <s:useActionBean beanclass="com.muni.fi.pa165.actions.user.UserActionBean" var="actionBean"/>
        <s:errors/> 
        <p><f:message key="user.list.allusers"/></p>
        <div class="table-responsive">
            <table class="table">
                <tr>
                    <th><f:message key="user.id"/></th>  
                    <th><f:message key="user.username"/></th>                
                    <th><f:message key="user.password"/></th>                    
                    <th><f:message key="user.authority"/></th>      
                    <th><f:message key="user.enabled"/></th>         
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${actionBean.users}" var="user">
                    <tr>                       
                        <td><c:out value="${user.id}" /></td>
                        <td><c:out value="${user.username}" /></td>
                        <td><c:out value="${user.password}" /></td>
                        <td><c:out value="${user.authority}" /></td>   
                        <td><c:out value="${user.enabled}" /></td>  
                        <td>  
                            <security:authorize ifAnyGranted="ROLE_ADMIN">
                                <s:form beanclass="com.muni.fi.pa165.actions.user.UserActionBean" action="edit">
                                    <s:hidden name="user.id" value="${user.id}"/>
                                    <s:submit class="btn btn-warning" name="edit"><f:message key="forms.edit"/></s:submit>
                                </s:form>
                            </security:authorize>
                        </td>
                        <td>     
                            <security:authorize ifAnyGranted="ROLE_ADMIN">
                                <s:form beanclass="com.muni.fi.pa165.actions.user.UserActionBean" action="delete">
                                    <s:hidden name="user.id" value="${user.id}"/>
                                    <s:submit class="btn btn-danger" name="delete"><f:message key="forms.delete"/></s:submit>
                                </s:form>
                            </security:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <s:form beanclass="com.muni.fi.pa165.actions.user.UserActionBean"  action="add" class="form-horizontal">
            <fieldset><legend><f:message key="user.list.newuser"/></legend>
                <%@include file="form.jsp"%>
                <s:submit class="btn btn-info" name="add"><f:message key="forms.save" /></s:submit>
                </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>
