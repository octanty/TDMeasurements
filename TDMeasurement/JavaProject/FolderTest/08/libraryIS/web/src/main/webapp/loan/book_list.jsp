<%-- 
    Document   : impression_list
    Created on : Nov 22, 2013, 5:58:54 PM
    Author     : Mi?koHu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="j" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="post">
    <table>
        <tr>
            <th scope="col">Condition</th>
            <th scope="col"><f:message key="impression.author"/></th>
            <th scope="col"><f:message key="impression.name"/></th>
            <th scope="col"><f:message key="impression.releaseDate"/></th>
            <th scope="col"><f:message key="impression.department"/></th>            
        </tr>
        <c:forEach items="${actionBean.books}" var="book">
            <tr>
                <td><c:out value="${book.condition}"/></td>                    
                <td><c:out value="${book.impression.author}"/></td>
                <td><c:out value="${book.impression.name}"/></td>
                <td><j:format value="${book.impression.releaseDate}" pattern="dd.MM.yyyy"/></td>
                <td><f:message key="Department.${book.impression.department}"/></td>
                <td><s:link beanclass="cz.muni.fi.pa165.web.LoanEditBean" event="create">
                        <s:param name="book.id" value="${book.id}"/>
                        <s:param name="customer.id" value="${actionBean.customer.id}"/>lend</s:link></td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
</div>