<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="monsterWeapon.title">
    <s:layout-component name="body">
        <h1><f:message key="monster.title"/></h1>

        <s:useActionBean beanclass="com.muni.fi.pa165.actions.efficiencies.EfficienciesActionBean" var="actionBean"/>
        <s:errors/> 
        <p><f:message key="monsterWeapon.list.allmonsterWeapons"/></p>
        <p> <s:link beanclass="com.muni.fi.pa165.actions.efficiencies.EfficienciesActionBean" event="clearFilter">
                                 <span class="glyphicon glyphicon-filter"><f:message key="clear.filters"/></span>
                            </s:link>
        <p>
                        
        <div class="table-responsive">
            <table class="table">
                <tr>                    
                    <th><f:message key="monsterWeapon.monsterID"/></th> 
                    <th></th>
                    <th><f:message key="monsterWeapon.monster.name"/></th>

                    <th><f:message key="monsterWeapon.weaponID"/></th>   
                    <th></th>
                    <th><f:message key="monsterWeapon.weapon.name"/></th>                   

                    <th><f:message key="monsterWeapon.hitRate"/></th>
                    <th><f:message key="monsterWeapon.damage"/></th>
                    <th><f:message key="monsterWeapon.efficiency"/></th>                   
                    <th><f:message key="monsterWeapon.description"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${actionBean.monsterWeapons}" var="monsterWeapon">
                    <tr>

                        <td><s:link beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean" event="edit">
                                <s:param name="monster.id" value="${monsterWeapon.monster.id}" />
                                <c:out value="${monsterWeapon.monster.id}" /></s:link>
                        </td>
                        <td><s:link beanclass="com.muni.fi.pa165.actions.efficiencies.EfficienciesActionBean" event="findByMonster">
                                <s:param name="filter.monster.id" value="${monsterWeapon.monster.id}" />
                                <span class="glyphicon glyphicon-filter"></span>
                            </s:link>
                        </td>                        
                        <td><c:out value="${monsterWeapon.monster.name}" /></td>


                        <td><s:link beanclass="com.muni.fi.pa165.actions.weapon.WeaponActionBean" event="edit">
                                <s:param name="weapon.id" value="${monsterWeapon.weapon.id}"/>
                                <c:out value="${monsterWeapon.weapon.id}" /></s:link>
                        </td>
                        
                        <td><s:link beanclass="com.muni.fi.pa165.actions.efficiencies.EfficienciesActionBean" event="findByWeapon">
                                <s:param name="filter.weapon.id" value="${monsterWeapon.weapon.id}" />
                                <span class="glyphicon glyphicon-filter"></span>
                            </s:link>
                        </td>
                        <td><c:out value="${monsterWeapon.weapon.name}" /></td>

                        <td><c:out value="${monsterWeapon.hitRate}" /></td>
                        <td><c:out value="${monsterWeapon.damage}" /></td>
                        <td><c:out value="${monsterWeapon.efficiency}" /></td>
                        <td><c:out value="${monsterWeapon.description}" /></td>   
                        <td>         
                            <s:form beanclass="com.muni.fi.pa165.actions.efficiencies.EfficienciesActionBean" action="edit">
                                <s:hidden name="monsterWeapon.monster.id" value="${monsterWeapon.monster.id}"/>
                                <s:hidden name="monsterWeapon.weapon.id" value="${monsterWeapon.weapon.id}"/>
                                <s:submit class="btn btn-warning" name="edit"><f:message key="forms.edit"/></s:submit>
                            </s:form>
                        </td>
                        <td>                            
                            <s:form beanclass="com.muni.fi.pa165.actions.efficiencies.EfficienciesActionBean" action="delete">
                                <s:hidden name="monsterWeapon.monster.id" value="${monsterWeapon.monster.id}"/>
                                <s:hidden name="monsterWeapon.weapon.id" value="${monsterWeapon.weapon.id}"/>
                                <s:submit class="btn btn-danger" name="delete"><f:message key="forms.delete"/></s:submit>
                            </s:form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <s:form beanclass="com.muni.fi.pa165.actions.efficiencies.EfficienciesActionBean"  action="add" class="form-horizontal">
            <fieldset><legend><f:message key="monsterWeapon.list.newmonsterWeapon"/></legend>
                   <div class="form-group">
        <div class="form-group">
            <s:label for="monsterWeapon.monster.id"  class="col-sm-2 control-label" name="monsterWeapon.monster.id" />
            <div class="col-sm-10">
                <s:select class="form-control"   id="monsterWeapon.monster.id" name="monsterWeapon.monster.id"><s:options-collection collection="${actionBean.monsters}"  label="name" value="id" /></s:select>
           </div>
        </div>
           
            <div class="form-group">
            <s:label for="monsterWeapon.weapon.id"  class="col-sm-2 control-label" name="monsterWeapon.weapon.id" />
            <div class="col-sm-10">
                <s:select class="form-control"   id="monsterWeapon.weapon.id" name="monsterWeapon.weapon.id"><s:options-collection collection="${actionBean.weapons}"  label="name" value="id" /></s:select>
           </div>
        </div>
 <%@include file="form.jsp"%>
                <s:submit class="btn btn-info" name="add"><f:message key="forms.save" /></s:submit>
                </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>
