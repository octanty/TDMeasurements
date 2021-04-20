<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="monster.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.muni.fi.pa165.actions.efficiencies.EfficienciesActionBean" var="actionBean"/>

        <s:form beanclass="com.muni.fi.pa165.actions.efficiencies.EfficienciesActionBean" class="form-horizontal"  focus="" action="/efficiencies/edit.action">
               <fieldset><legend><f:message key="monsterWeapon.edit.edit"/></legend></fieldset>
                   <s:hidden name="monsterWeapon.monster.id"/>
                   <s:hidden name="monsterWeapon.weapon.id"/>
                      <div class="form-group">
        <div class="form-group">
            <s:label for="monsterWeapon.monster.id"  class="col-sm-2 control-label" name="monsterWeapon.monster.id" />
            <div class="col-sm-10">
                <s:select class="form-control" disabled="true"  id="monsterWeapon.monster.id" name="monsterWeapon.monster.id"><s:options-collection collection="${actionBean.monsters}"  label="name" value="id" /></s:select>
           </div>
        </div>
           
            <div class="form-group">
            <s:label for="monsterWeapon.weapon.id"  class="col-sm-2 control-label" name="monsterWeapon.weapon.id" />
            <div class="col-sm-10">
                <s:select class="form-control"   disabled="true" id="monsterWeapon.weapon.id" name="monsterWeapon.weapon.id"><s:options-collection collection="${actionBean.weapons}"  label="name" value="id" /></s:select>
           </div>
        </div>
                <%@include file="form.jsp"%>
               <div><s:submit class="btn btn-info" name="save"><f:message key="forms.save"/></s:submit> 
                   </div>               
            
        </s:form>
                          &nbsp;
        <div class="form-group">
        <s:form beanclass="com.muni.fi.pa165.actions.efficiencies.EfficienciesActionBean" class="form-horizontal"  focus="" action="/efficiencies/cancel.action">
             <div class="col-sm-10">   
            <s:submit class="btn btn-warning" name="cancel" value="cancel" ><f:message key="forms.cancel"/></s:submit> 
             </div>
        </s:form>
        </div>
        
     


    </s:layout-component>
</s:layout-render>