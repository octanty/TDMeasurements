<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<s:layout-render name="/layout.jsp" titlekey="bottles" page="bottles">
    <s:layout-component name="header">
    </s:layout-component>
    <s:layout-component name="body">

        <s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.BottlesActionBean" var="bottleActionBean"/>

        <h2>${bottleActionBean.bottle.liquor.name},<br />${bottleActionBean.bottle.liquor.producer.name} (ID: ${bottleActionBean.bottle.id})</h2>

        <p><strong><f:message key="bottle.liquor" />:</strong> ${bottleActionBean.bottle.liquor.name}</p>
        <p><strong><f:message key="bottle.stamp" />:</strong> ${bottleActionBean.bottle.stamp.numberOfStamp}</p>
        <p><strong><f:message key="bottle.producedDate" />:</strong>  <joda:format value="${bottleActionBean.bottle.producedDate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
        <p><strong><f:message key="bottle.lotCode" />:</strong> ${bottleActionBean.bottle.lotCode}</p>
        <p><strong><f:message key="bottle.store" />:</strong> ${bottleActionBean.bottle.store.name}</p>
         <s:form  beanclass="cz.muni.fi.pa165.bottler.web.BottlesActionBean">

        <p><strong><f:message key="bottle.sold" />:</strong>
            <c:choose>
                <c:when test="${bottleActionBean.bottle.sold}">
                    <f:message key="yes"/>
                </c:when>
                <c:otherwise>
                    <f:message key="no"/>
                    
                    
                    
               
                   
                    <c:if test="${bottleActionBean.userIsPermittedToEdit}">
                        <s:hidden name="bottle.id" value="${bottle.id}"/>
                        <s:submit class="btn btn-xs btn-primary" name="sell"> <f:message key="sell"/></s:submit>
                    </c:if>
              
                    
                </c:otherwise>
            </c:choose>
        </p>
  </s:form>
        <h3><f:message key="testresults" /></h3>

        <c:choose>
            <c:when test="${fn:length(bottleActionBean.testResults) != 0}">
                <table class="table table-hover" id="testResultsList">
                    <thead>
                        <tr>
                            <th><f:message key="testresult.time"/></th>
                            <th><f:message key="testresult.toxic"/></th>
                           
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${actionBean.testResults}" var="testResult">
                            <tr class="with-button no-link">
                                <td>
                                    <time class="timeago" datetime="<c:out value="${testResult.time}"/>">
                                        <c:out value="${testResult.time}"/>
                                    </time>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${testResult.isToxic()}">
                                            <f:message key="yes"/>
                                        </c:when>
                                        <c:otherwise>
                                            <f:message key="no"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                           
                            </tr>

                        </c:forEach>

                    </tbody>



                </table>
            </c:when>
            <c:otherwise>
                <%@include file="blocks/bottleTestResultCreateForm.jsp"%>
                <div class="row">

                    <div class="col-md-12">

                        <div class="btn-group pull-left">
                            <button class="btn btn-default" data-toggle="modal" data-target="#createTestResultModal">
                                <span class="glyphicon glyphicon-plus-sign"></span>
                                <f:message key="testresult.create"/>
                            </button>
                        </div>
                    </div>

                </div>

            </c:otherwise>
        </c:choose>
    </s:layout-component>
</s:layout-render>