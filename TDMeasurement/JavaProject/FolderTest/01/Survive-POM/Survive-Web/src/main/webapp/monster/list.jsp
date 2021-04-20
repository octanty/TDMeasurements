<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<s:layout-render name="/layout.jsp" titlekey="monster.title">
    <s:layout-component name="body">
        <h1><f:message key="monster.title"/></h1>

        <s:useActionBean beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean" var="actionBean"/>
      <s:errors/>
        <p><f:message key="monster.list.allmonsters"/></p>
        <div class="table-responsive">
            <table class="table">
                <tr>
                    <th><f:message key="monster.image"/></th>
                    <th>id</th>
                    <th><f:message key="monster.name"/></th>                
                    <th><f:message key="monster.stamina"/></th>                    
                    <th><f:message key="monster.height"/></th>
                    <th><f:message key="monster.strength"/></th>
                    <th><f:message key="monster.agility"/></th>
                    <th><f:message key="monster.dangerLevel"/></th>
                    <th><f:message key="monster.weight"/></th>
                    <th><f:message key="monster.class"/></th>
                    <th><f:message key="monster.description"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${actionBean.monsters}" var="monster">
                    <tr>
                        <td>
                            
                            <s:form beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean" action="edit">          
                                <s:hidden name="monster.id" value="${monster.id}"/>
                                <s:image src="${monster.imagePath}" class="img img-circle" name="${monster.id}.image" style="width: 60px; height: 60px" />
                            </s:form>
                           
                        </td> 
                        <td>${monster.id}</td>
                        <td><c:out value="${monster.name}" /></td>
                        <td><c:out value="${monster.stamina.intValue()}" /></td>

                        <td><c:out value="${monster.height}" /></td>
                        <td><c:out value="${monster.strength.intValue()}" /></td>
                        <td><c:out value="${monster.agility.intValue()}" /></td>
                        <td><c:out value="${monster.dangerLevel.intValue()}" /></td>
                        <td><c:out value="${monster.weight}" /></td>
                        <td><f:message key="com.muni.fi.pa165.enums.MonsterClass.${monster.monsterClass}" /></td>
                        <td><c:out value="${monster.description}" /></td>   
                        <td>         
                             <security:authorize ifAnyGranted="ROLE_ADMIN">
                            <s:form beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean" action="edit">
                                <s:hidden name="monster.id" value="${monster.id}"/>
                                <s:submit class="btn btn-warning" name="edit"><f:message key="forms.edit"/></s:submit>
                            </s:form>
                                  </security:authorize>
                        </td>
                        <td>  
                              <security:authorize ifAnyGranted="ROLE_ADMIN">
                            <s:form beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean" action="delete">
                                <s:hidden name="monster.id" value="${monster.id}"/>
                                <s:submit class="btn btn-danger" name="delete"><f:message key="forms.delete"/></s:submit>
                            </s:form>
                              </security:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <s:form beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean"  action="add" class="form-horizontal">
            <fieldset><legend><f:message key="monster.list.newmonster"/></legend>
                <%@include file="form.jsp"%>
                <s:submit class="btn btn-info" name="add"><f:message key="forms.save" /></s:submit>
                </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>
