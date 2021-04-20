<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="weapon.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.muni.fi.pa165.actions.weapon.WeaponActionBean" var="actionBean"/>

        <s:form beanclass="com.muni.fi.pa165.actions.weapon.WeaponActionBean" class="form-horizontal"  focus="" action="/weapon/edit.action">
            <s:hidden name="weapon.id"/>
            <fieldset><legend><f:message key="weapon.edit.edit"/></legend>
                <%@include file="form.jsp"%>
                <s:submit class="btn btn-info" name="save"><f:message key="forms.save"/></s:submit>
                </fieldset>
        </s:form>

        &nbsp;
         <s:form beanclass="com.muni.fi.pa165.actions.weapon.WeaponActionBean" class="form-horizontal"  focus="" action="/weapon/edit.action">
                    <s:submit class="btn btn-warning" name="cancel" value="cancel" ><f:message key="forms.cancel"/></s:submit>             
        </s:form>

    </s:layout-component>
</s:layout-render>