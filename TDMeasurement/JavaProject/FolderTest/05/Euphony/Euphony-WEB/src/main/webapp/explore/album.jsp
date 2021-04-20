<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="album.detail.title">
    <s:layout-component name="body">
        <div class="back"><a href="./../albums"><f:message key="action.back.to.albums"/></a></div>
        <table class="showAlbumDetail">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${not empty actionBean.album.cover}">
                            <img src="${pageContext.request.contextPath}/upload/${actionBean.album.cover}"/>
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/upload/nocover.png"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <h3><c:out value="${actionBean.album.title}"/></h3>
                    <f:message key="album.releasedate"/>: <c:out value="${actionBean.album.releaseDate.dayOfMonth}.${actionBean.album.releaseDate.monthOfYear}.${actionBean.album.releaseDate.year}"/><br><br>
                    <i><c:out value="${actionBean.album.comment}"/></i>
                </td>
            </tr>
        </table>

        <c:set var="numberOfSongs" scope="session" value="${actionBean.songsInAlbum}"/>
        <c:choose>
            <c:when test="${empty numberOfSongs}">
                <br><i><f:message key="playlist.songs.none"/></i>
            </c:when>
            <c:otherwise>
                <h4><f:message key="playlist.listofsongs"/></h4>

                <table class="basic">
                    <tr>
                        <th><f:message key="song.title"/></th>
                        <th><f:message key="song.artist"/></th>
                        <th><f:message key="song.album"/></th>
                    </tr>
                    <c:forEach items="${actionBean.songsInAlbum}" var="song">
                        <tr>
                            <td class="blackTd">
                                <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showSong">
                                    <s:param name="mainId" value="${song.id}"/>
                                    <c:out value="${song.title}"/>
                                </s:link>
                            </td>
                            <td class="silverTd">
                                <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showArtist">
                                    <s:param name="mainId" value="${song.artist.id}"/>
                                    <c:out value="${song.artist.name}"/>
                                </s:link>
                            </td>
                            <td class="silverTd">
                                <s:link beanclass="com.musiclibrary.euphonyweb.ExploreActionBean" event="showAlbum">
                                    <s:param name="mainId" value="${song.album.id}"/>
                                    <c:out value="${song.album.title}"/>
                                </s:link>
                            </td>
                        </tr>   
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>


    </s:layout-component>
</s:layout-render>
