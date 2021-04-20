<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!-- form start -->
<s:form beanclass="cz.muni.fi.pa165.bottler.web.LiquorsActionBean" class="center-block" id="liquorActionBean">

    <h4><f:message key="liquor.edit"/></h4>
    <br />
    <s:hidden name="liquor.id" />
    <%@include file="liquorForm.jsp"%>
    <div class="pull-right">
        <s:link class="btn btn-default" href="/liquors"><f:message key="cancel"/></s:link>
        <s:submit name="update" class="btn btn-primary"><f:message key="liquor.save"/></s:submit>
    </div>

</s:form>