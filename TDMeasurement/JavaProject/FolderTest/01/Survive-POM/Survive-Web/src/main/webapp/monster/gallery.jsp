<%@page import="net.sourceforge.stripes.action.ActionBean"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">

        <s:useActionBean beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean" var="actionBean"/>

        <h1><f:message key="gallery.title"/></h1>
        <!-- The Bootstrap Image Gallery lightbox, should be a child element of the document body -->
        <div id="blueimp-gallery" class="blueimp-gallery">
            <!-- The container for the modal slides -->
            <div class="slides"></div>
            <!-- Controls for the borderless lightbox -->
            <h3 class="title"></h3>
            <a class="prev">‹</a>
            <a class="next">›</a>
            <a class="close">×</a>
            <a class="play-pause"></a>
            <ol class="indicator"></ol>
            <!-- The modal dialog, which will be used to wrap the lightbox content -->
            <div class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" aria-hidden="true">&times;</button>
                            <h4 class="modal-title"></h4>
                        </div>
                        <div class="modal-body next"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left prev">
                                <i class="glyphicon glyphicon-chevron-left"></i>
                                Previous
                            </button>
                            <button type="button" class="btn btn-primary next">
                                Next
                                <i class="glyphicon glyphicon-chevron-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="links">
            <c:forEach items="${actionBean.monsters}" var="monster">

                <div class="media">
                    <a href="${monster.imagePath}" title="${monster.name}" class="pull-left" data-gallery>
                        <img class="img-rounded" style="width: 200px; height: 200px" src="${monster.imagePath}" alt="${monster.name}">
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading">${monster.name}</h4>

                        <div class="progress progress-striped ">
                            <div class="progress-bar progress-bar-success" style="width: ${monster.agility}%">
                                <span class="sr-only"> Agility: ${monster.agility} </span>
                            </div>
                        </div>
                        <div class="progress progress-striped">

                            <div class="progress-bar progress-bar-warning" style="width: ${monster.strength}%">
                                <span class="sr-only"> Strength: ${monster.strength}% </span>
                            </div>

                        </div>
                        <div class="progress progress-striped">
                            <div class="progress-bar progress-bar-danger" style="width: ${monster.dangerLevel}%">
                                 <span class="sr-only"> Danger level: ${monster.dangerLevel} </span>
                            </div>
                        </div>
                    </div>
                    &nbsp;
                    Description:
                    ${monster.description}
                    <br/>
                    &nbsp;
                    <s:form class="form-horizontal" beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean" action="edit">
                        <s:hidden name="monster.id" value="${monster.id}"/>
                        <s:submit class="btn btn-warning" name="edit"><f:message key="forms.edit"/></s:submit>
                    </s:form>
                </div>
            </div>





        </c:forEach>
    </div>


</s:layout-component>
</s:layout-render>