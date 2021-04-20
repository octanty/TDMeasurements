<%-- 
    Document   : impression_edit
    Created on : 10.11.2013, 20:00:38
    Author     : Mjartan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="j" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="post">
    <table>
        <tr>
            <th scope="col"><f:message key="impression.isbn"/></th>
            <th scope="col"><f:message key="impression.author"/></th>
            <th scope="col"><f:message key="impression.name"/></th>
            <th scope="col"><f:message key="impression.releaseDate"/></th>
            <th scope="col"><f:message key="impression.department"/></th>            
        </tr>
        <c:forEach items="${actionBean.allImpressions}" var="impression">
            <tr>
                <td><c:out value="${impression.isbn}"/></td>                    
                <td><c:out value="${impression.author}"/></td>
                <td><s:link beanclass="cz.muni.fi.pa165.web.BookEditBean" event="listBooks">
                        <s:param name="impression.id" value="${impression.id}"/><c:out value="${impression.name}"/></s:link></td>
                <td><j:format value="${impression.releaseDate}" pattern="dd.MM.yyyy"/></td>
                <td><f:message key="Department.${impression.department}"/></td>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean" event="edit">
                            <s:param name="impression.id" value="${impression.id}"/><f:message key="edit"/></s:link></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean" event="delete">
                            <s:param name="impression.id" value="${impression.id}"/><f:message key="delete"/></s:link></td>     
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

