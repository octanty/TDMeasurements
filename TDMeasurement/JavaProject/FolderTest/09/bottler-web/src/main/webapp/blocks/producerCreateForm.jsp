<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>


<!-- form start -->
<s:form beanclass="cz.muni.fi.pa165.bottler.web.ProducersActionBean" id="producerCreateForm">
    
    <s:hidden name="modal_form" value="true" />
    <div class="modal modal_form" id="createProducerModal" tabindex="-1" role="dialog" aria-labelledby="createProducerModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"><f:message key="producer.create"/></h4>
                </div>

                <div class="modal-body">

                    <s:errors/>

                    <%@include file="producerForm.jsp"%>

                </div>
                <div class="modal-footer">

                    <button type="button" class="btn btn-default" data-dismiss="modal"><f:message key="cancel"/></button>

                    <s:submit name="create" class="btn btn-primary"><f:message key="producer.create"/></s:submit>
                    </div>




                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->


</s:form>

