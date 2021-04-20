<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="login">       
    <s:layout-component name="body"> 

        <h1><f:message key="main.title" /></h1>

        <form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
            <c:if test="${param.badpw == '1'}">

            </c:if>
            <div class="post">
                <table>
                    <tr>
                        <td> <s:label for="j_username" name="username.title"/></td>
                        <td><input id="j_username" name="j_username" type="text" value="" autofocus /></td>
                    <tr><td><s:label for="j_password" name="password.title"/></td>
                        <td> <input id="j_password" name="j_password" type="password" /></td></tr>
                </table>
            </div>
            <button name="submit" type="submit" value="1" class="button"><f:message key="login"/></button>
        </form>

    </s:layout-component>
</s:layout-render>