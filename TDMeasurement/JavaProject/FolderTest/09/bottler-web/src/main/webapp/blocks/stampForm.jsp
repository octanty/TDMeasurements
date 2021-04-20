<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="form-group">
    <label for="stamp_bottle_input"><f:message key="stamp.number_of_stamp"/></label>

    <s:text name="stamp.numberOfStamp" class="form-control" id="stamp_bottle_input" />

</div>

<div class="form-group">
    <label for="stamp_time_input"><f:message key="stamp.issued_date"/></label>

    <div class='input-group date' id='datetimepicker1'>
        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
        </span>

        <%-- !! HACK !! - <s:text> doesn't accept data-format parameter --%>
        <input data-format="YYYY-MM-DD HH:mm:ss" type="text" class="hidden"></input>

        <s:text name="stamp.issuedDate" class="form-control"
                id="stamp_time_input"/>
    </div>

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

<!-- <button type="submit" class="btn btn-default">Create stamp</button>-->
