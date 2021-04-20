<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>


<!-- form start -->
<s:form beanclass="cz.muni.fi.pa165.bottler.web.ProducersActionBean" class="center-block" id="producerEditForm">


    <h4><f:message key="producer.edit"/></h4> 

<br />
    <s:hidden name="producer.id" />

    <%@include file="producerForm.jsp"%>

    <div class="pull-right">

    <s:link class="btn btn-default" href="/producers"><f:message key="cancel"/></s:link>

    <s:submit name="update" class="btn btn-primary"><f:message key="producer.save"/></s:submit>

</div>

</s:form>