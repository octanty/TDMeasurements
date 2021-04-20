<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="monsterArea.title">
    <s:layout-component name="body">
        <h1><f:message key="monster.title"/></h1>

        <s:useActionBean beanclass="com.muni.fi.pa165.actions.locations.LocationsActionBean" var="actionBean"/>
        <s:errors/>
        <p><f:message key="monsterArea.list.allmonsterAreas"/></p>
        <p> <s:link beanclass="com.muni.fi.pa165.actions.locations.LocationsActionBean" event="clearFilter">
                <span class="glyphicon glyphicon-filter"><f:message key="clear.filters"/></span>
            </s:link>
        <p>

        <div class="table-responsive">
            <table class="table">
                <tr>                    
                    <th><f:message key="monsterArea.monsterID"/></th> 
                    <th></th>
                    <th><f:message key="monsterArea.monster.name"/></th>
                    <th><f:message key="monsterArea.areaID"/></th>   
                    <th></th>
                    <th><f:message key="monsterArea.area.name"/></th>                   
                    <th><f:message key="monsterArea.monsterQuantity"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${actionBean.monsterAreas}" var="monsterArea">
                    <tr>

                        <td><s:link beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean" event="edit">
                                <s:param name="monster.id" value="${monsterArea.monster.id}" />
                                <c:out value="${monsterArea.monster.id}" /></s:link>
                            </td>
                            <td><s:link beanclass="com.muni.fi.pa165.actions.locations.LocationsActionBean" event="findByMonster">
                                <s:param name="filter.monster.id" value="${monsterArea.monster.id}" />
                                <span class="glyphicon glyphicon-filter"></span>
                            </s:link>
                        </td>                        
                        <td><c:out value="${monsterArea.monster.name}" /></td>


                        <td><s:link beanclass="com.muni.fi.pa165.actions.area.AreaActionBean" event="edit">
                                <s:param name="area.id" value="${monsterArea.area.id}"/>
                                <c:out value="${monsterArea.area.id}" /></s:link>
                            </td>

                            <td><s:link beanclass="com.muni.fi.pa165.actions.locations.LocationsActionBean" event="findByArea">
                                <s:param name="filter.area.id" value="${monsterArea.area.id}" />
                                <span class="glyphicon glyphicon-filter"></span>
                            </s:link>
                        </td>
                        
                        <td><c:out value="${monsterArea.area.name}" /></td>

                        <td><c:out value="${monsterArea.monsterQuantity}" /></td>

                        <td>    
                             
                            <s:form beanclass="com.muni.fi.pa165.actions.locations.LocationsActionBean" action="edit">
                                <s:hidden name="monsterArea.monster.id" value="${monsterArea.monster.id}"/>
                                <s:hidden name="monsterArea.area.id" value="${monsterArea.area.id}"/>
                                <s:submit class="btn btn-warning" name="edit"><f:message key="forms.edit"/></s:submit>
                            </s:form>
                             
                        </td>
                        <td>     
                             
                            <s:form beanclass="com.muni.fi.pa165.actions.locations.LocationsActionBean" action="delete">
                                <s:hidden name="monsterArea.monster.id" value="${monsterArea.monster.id}"/>
                                <s:hidden name="monsterArea.area.id" value="${monsterArea.area.id}"/>
                                <s:submit class="btn btn-danger" name="delete"><f:message key="forms.delete"/></s:submit>
                            </s:form>
                              
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <s:form beanclass="com.muni.fi.pa165.actions.locations.LocationsActionBean"  action="add" class="form-horizontal">
            <fieldset><legend><f:message key="monsterArea.list.newmonsterArea"/></legend>
                <div class="form-group">
                    <div class="form-group">
                        
                        <s:label for="monsterArea.monster.id"  class="col-sm-2 control-label" name="monsterArea.monster.id" />
                        <div class="col-sm-10">
                            <s:select class="form-control"   id="monsterArea.monster.id" name="monsterArea.monster.id"><s:options-collection collection="${actionBean.monsters}"  label="name" value="id" /></s:select>
                        </div>
                        </div>

                        <div class="form-group">
                        <s:label for="monsterArea.area.id"  class="col-sm-2 control-label" name="monsterArea.area.id" />
                        <div class="col-sm-10">
                            <s:select class="form-control"   id="monsterArea.area.id" name="monsterArea.area.id"><s:options-collection collection="${actionBean.areas}"  label="name" value="id" /></s:select>
                            </div>
                        </div>
                    <%@include file="form.jsp"%>
                    <s:submit class="btn btn-info" name="add"><f:message key="forms.save" /></s:submit>
                </div>    
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>
