<%-- 
    Document   : list
    Created on : Nov 21, 2013, 4:41:59 PM
    Author     : Matej
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="j" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="post">
    <table>
        <tr>
            <td valign="top">
                <table border="1" style="border-collapse: collapse;">
                    <tr>
                        <th><f:message key="customer.name"/></th>
                        <th><f:message key="customer.address"/></th>                                 
                    </tr>
                    <c:forEach items="${actionBean.allCustomers}" var="customer">
                        <tr>
                            <td><s:link beanclass="cz.muni.fi.pa165.web.LoanEditBean" event="findByCustomer">
                                    <s:param name="customer.id" value="${customer.id}"/><c:out value="${customer.name}"/></s:link></td>                   
                            <td><c:out value="${customer.address}"/></td>                           
                            <td><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean" event="edit">
                                    <s:param name="customer.id" value="${customer.id}"/><f:message key="edit"/>
                                </s:link>
                            </td>
                            <td><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean" event="delete">
                                    <s:param name="customer.id" value="${customer.id}"/><f:message key="delete"/>
                                </s:link>
                            </td>
                            <td><s:link beanclass="cz.muni.fi.pa165.web.LoanEditBean">
                                    <s:param name="customer.id" value="${customer.id}"/><f:message key="createLoan"/>
                                </s:link>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>        
        </tr>
    </table>
</div>
