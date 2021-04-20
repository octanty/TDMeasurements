<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.TestResultsActionBean" var="testResultsActionBean"/>

<!-- form start -->
<s:form beanclass="cz.muni.fi.pa165.bottler.web.TestResultsActionBean" class="center-block" id="testResultEditForm">

    <h4><f:message key="testresult.edit"/></h4>

    <br />
    <s:hidden name="testResult.id" />

    <div class="form-group">
        <s:hidden name="testResult.bottle" value="${testResultsActionBean.testResult.bottle.id}"/>
        <strong><f:message key="testresult.edit_bottle"></f:message>:</strong><br>
        ID: <c:out value="${testResultsActionBean.testResult.bottle.id}"></c:out><br>
        <f:message key="bottle.lotCode"></f:message>: <c:out value="${testResultsActionBean.testResult.bottle.lotCode}"></c:out><br>
        <f:message key="bottle.stamp"></f:message>: <c:out value="${testResultsActionBean.testResult.bottle.stamp.numberOfStamp}"></c:out><br>
        <f:message key="bottle.liquor"></f:message>: <c:out value="${testResultsActionBean.testResult.bottle.liquor.name}"></c:out><br>
        <f:message key="liquor.producer"></f:message>: <c:out value="${testResultsActionBean.testResult.bottle.liquor.producer.name}"></c:out>
    </div>

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

    <div class="pull-right">

        <s:link class="btn btn-default" href="/testResults"><f:message key="cancel"/></s:link>

        <s:submit name="update" class="btn btn-primary"><f:message key="testresult.save"/></s:submit>

        </div>

</s:form>