<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="monster.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.muni.fi.pa165.actions.locations.LocationsActionBean" var="actionBean"/>

        <s:form beanclass="com.muni.fi.pa165.actions.locations.LocationsActionBean" class="form-horizontal"  focus="" action="/locations/edit.action">
            <s:hidden name="monsterArea.monster.id"/>
            <s:hidden name="monsterArea.area.id"/>
            <fieldset><legend><f:message key="monsterArea.edit.edit"/></legend>
<s:errors/> 
                <div class="form-group">
                       
                    <div class="form-group">
                    <s:label for="monsterWeapon.monster.id"  class="col-sm-2 control-label" name="monsterArea.monster.id" />
                    <div class="col-sm-10">
                        <s:select class="form-control" disabled="true"  id="monsterArea.monster.id" name="monsterArea.monster.id"><s:options-collection collection="${actionBean.monsters}"  label="name" value="id" /></s:select>
                    </div>
                    </div>


                        <div class="form-group">
                        <s:label for="monsterArea.area.id"  class="col-sm-2 control-label" name="monsterArea.area.id" />
                        <div class="col-sm-10">
                            <s:select class="form-control"   disabled="true" id="monsterArea.area.id" name="monsterArea.area.id"><s:options-collection collection="${actionBean.areas}"  label="name" value="id" /></s:select>
                         </div>
                        </div>
                    <%@include file="form.jsp"%>
                    <s:submit class="btn btn-info" name="save"><f:message key="forms.save"/></s:submit> 
                </div>    
            </fieldset>
        </s:form>
        
            <s:form beanclass="com.muni.fi.pa165.actions.locations.LocationsActionBean" class="form-horizontal"  focus="" action="/locations/cancel.action">           
                <div class="form-group">
                <s:submit class="btn btn-warning" name="cancel" value="cancel" ><f:message key="forms.cancel"/></s:submit> 
                    </div>

                </s:form>
    </s:layout-component>
</s:layout-render>