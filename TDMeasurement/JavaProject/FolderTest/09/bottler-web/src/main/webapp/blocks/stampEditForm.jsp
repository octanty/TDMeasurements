<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>


<!-- form start -->
<s:form beanclass="cz.muni.fi.pa165.bottler.web.StampsActionBean" class="center-block" id="stampEditForm">


    <h4><f:message key="stamp.edit"/></h4>

    <br />
    <s:hidden name="stamp.id" />

    <%@include file="stampForm.jsp"%>

    <div class="pull-right">

        <s:link class="btn btn-default" href="/stamps"><f:message key="cancel"/></s:link>

        <s:submit name="update" class="btn btn-primary"><f:message key="stamp.save"/></s:submit>

        </div>

</s:form>