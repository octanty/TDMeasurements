<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:form beanclass="cz.muni.fi.pa165.bottler.web.BottlesActionBean" class="center-block" id="bottlesActionBean">

    <h4><f:message key="bottle.edit"/></h4>
    <br />
    <s:hidden name="bottle.id" />
    <%@include file="bottleForm.jsp"%>
    <div class="pull-right">
        <s:link class="btn btn-default" href="/bottlers"><f:message key="cancel"/></s:link>
        <s:submit name="update" class="btn btn-primary"><f:message key="bottle.save"/></s:submit>
    </div>

</s:form>