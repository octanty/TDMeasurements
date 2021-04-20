<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>


<!-- form start -->
<s:form beanclass="cz.muni.fi.pa165.bottler.web.StoresActionBean" class="center-block" id="storeEditForm">


    <h4><f:message key="store.edit"/></h4> 

<br />
    <s:hidden name="store.id" />

    <%@include file="storeForm.jsp"%>

    <div class="pull-right">

    <s:link class="btn btn-default" href="/stores"><f:message key="cancel"/></s:link>

    <s:submit name="update" class="btn btn-primary"><f:message key="store.save"/></s:submit>

</div>

</s:form>