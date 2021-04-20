<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.TestResultsActionBean" var="testResultsActionBean"/>
<div class="form-group">
    <label for="testresult_bottle_input"><f:message key="testresult.bottle"/></label>

    <s:select name="testResult.bottle" class="form-control" id="testresult_bottle_input">
        <s:options-collection collection="${testResultsActionBean.bottles}"
                              label="liquor.name" value="id"/>
    </s:select>
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

<!-- <button type="submit" class="btn btn-default">Create testresult</button>-->
