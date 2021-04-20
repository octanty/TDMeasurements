<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!-- form start -->
<s:form beanclass="cz.muni.fi.pa165.bottler.web.TestResultsActionBean" id="testResultCreateForm">
    <s:hidden name="modal_form" value="true" />

    <div class="modal modal_form" id="createTestResultModal" tabindex="-1" role="dialog" aria-labelledby="createTestResultModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"><f:message key="testresult.create"/></h4>
                </div>

                <div class="modal-body">

                    <s:errors/>




                    <s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.BottlesActionBean" var="bottlesActionBean"/>

                    <s:hidden name="testResult.bottle" value="${bottlesActionBean.bottle.id}" />

                    <div class="form-group">
                        <label for="testresult_time_input"><f:message key="testresult.time"/></label>

                        <div class='input-group date' id='datetimepicker1'>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                            </span>

                            <%-- !! HACK !! - <s:text> doesn't accept data-format parameter --%>
                            <input data-format="YYYY-MM-DD HH:mm:ss" type="text" class="hidden"></input>

                            <s:text name="testResult.time" class="form-control" id="testresult_time_input" />

                        </div>
                    </div>

                    <div class="form-group">
                        <label for="testresult_toxic_input"><f:message key="testresult.toxic"/></label>
                        <s:checkbox name="testResult.toxic" class="form-control" id="testresult_toxic_input" />
                    </div>

                    <script type="text/javascript">
                        $(function() {
                            $('#datetimepicker1').datetimepicker({
                            });
                            var dateInput = $('#datetimepicker1 input.form-control');
                            var dateString = dateInput.val();
                            dateString = dateString.replace('T', ' ').substr(0, 19);
                            dateInput.val(dateString);
                        });
                    </script>

                    <!-- <button type="submit" class="btn btn-default">Create testresult</button>-->


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><f:message key="cancel"/></button>

                  
                    <s:submit name="create" class="btn btn-primary"><f:message key="testresult.create"/></s:submit>
                </div>





            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


</s:form>

