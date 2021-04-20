<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="monster.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean" var="actionBean"/>

        <s:form beanclass="com.muni.fi.pa165.actions.area.AreaActionBean" class="form-horizontal"  focus="" action="/area/edit.action">
            <s:hidden name="area.id"/>
            <fieldset><legend><f:message key="area.edit.edit"/></legend>
                <%@include file="form.jsp"%>
                <s:submit class="btn btn-info" name="save"><f:message key="forms.save"/></s:submit> 
                </fieldset>
        </s:form>
        &nbsp;
        <div>
            <s:form beanclass="com.muni.fi.pa165.actions.area.AreaActionBean" class="form-horizontal"  focus="" action="/area/cancel.action">           
                <s:submit class="btn btn-warning" name="cancel" value="cancel" ><f:message key="forms.cancel"/></s:submit> 
            </s:form>
        </div>
    </s:layout-component>
</s:layout-render>