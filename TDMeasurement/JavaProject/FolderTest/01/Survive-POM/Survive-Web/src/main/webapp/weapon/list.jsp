<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="weapon.title">
    <s:layout-component name="body">
        <h1><f:message key="weapon.title"/></h1>

        <s:useActionBean beanclass="com.muni.fi.pa165.actions.weapon.WeaponActionBean" var="actionBean"/>
        <s:errors/> 
        <p><f:message key="weapon.list.allweapons"/></p>
        <div class="table-responsive">
            <table class="table">
                <tr>
                   
                    <th>id</th>
                    <th><f:message key="weapon.name"/></th>                
                    <th><f:message key="weapon.weaponType"/></th>                    
                    <th><f:message key="weapon.weaponClass"/></th>
                    <th><f:message key="weapon.range"/></th>
                    <th><f:message key="weapon.caliber"/></th>
                    <th><f:message key="weapon.rounds"/></th>
                    <th><f:message key="weapon.description"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${actionBean.weapons}" var="weapon">
                    <tr>                       
                        <td>${weapon.id}</td>
                        <td><c:out value="${weapon.name}" /></td>
                        <td><f:message key="com.muni.fi.pa165.enums.WeaponType.${weapon.weaponType}" /></td>
                        <td><f:message key="com.muni.fi.pa165.enums.WeaponClass.${weapon.weaponClass}" /></td>
                        <td><c:out value="${weapon.range}" /></td>

                        <td><c:out value="${weapon.caliber}" /></td>
                        <td><c:out value="${weapon.rounds}" /></td>
                        <td><c:out value="${weapon.description}" /></td>
                          <td>  
                                <security:authorize ifAnyGranted="ROLE_ADMIN">
                            <s:form beanclass="com.muni.fi.pa165.actions.weapon.WeaponActionBean" action="edit">
                                <s:hidden name="weapon.id" value="${weapon.id}"/>
                                <s:submit class="btn btn-warning" name="edit"><f:message key="forms.edit"/></s:submit>
                            </s:form>
                                </security:authorize>
                        </td>
                        <td>     
                              <security:authorize ifAnyGranted="ROLE_ADMIN">
                            <s:form beanclass="com.muni.fi.pa165.actions.weapon.WeaponActionBean" action="delete">
                                <s:hidden name="weapon.id" value="${weapon.id}"/>
                                <s:submit class="btn btn-danger" name="delete"><f:message key="forms.delete"/></s:submit>
                            </s:form>
                              </security:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <s:form beanclass="com.muni.fi.pa165.actions.weapon.WeaponActionBean"  action="add" class="form-horizontal">
            <fieldset><legend><f:message key="weapon.list.newweapon"/></legend>
                <%@include file="form.jsp"%>
                <s:submit class="btn btn-info" name="add"><f:message key="forms.save" /></s:submit>
                </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>
