<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="artists.title">
    <s:layout-component name="body">
        <h3><f:message key="menu.explore"/></h3>
        <%@include file="menuUp.jsp"%>

        <table class="basic">
            <tr>
                <th><f:message key="artist.name"/></th>
            </tr>
            <c:forEach items="${actionBean.artists}" var="artist">
                <tr>
                    <td>
                        <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showArtist">
                            <s:param name="mainId" value="${artist.id}"/>
                            <c:out value="${artist.name}"/>
                        </s:link>
                    </td>
                </tr>
            </c:forEach>
        </table>


    </s:layout-component>
</s:layout-render>
