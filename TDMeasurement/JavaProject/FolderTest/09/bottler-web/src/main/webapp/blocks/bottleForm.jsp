<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.BottlesActionBean" var="bottlesActionBean"/>
<div class="form-group">
    <label for="bottle-liquor-input"><f:message key="bottle.liquor"/></label>
    <s:select name="bottle.liquor" class="form-control" id="bottle-liquor-input">
        <s:options-collection collection="${bottlesActionBean.liquors}" value="id" label="name"/>
    </s:select>
</div>

<div class="form-group">
    <label for="bottle-stamp-input"><f:message key="bottle.stamp"/></label>
    <s:select name="bottle.stamp" class="form-control" id="bottle-stamp-input">
        <s:options-collection collection="${bottlesActionBean.stamps}" value="id" label="numberOfStamp"/>
    </s:select>
</div>

<div class="form-group">
    <label for="bottle-producedDate-input"><f:message key="bottle.producedDate"/></label>
    <div class="form-group">
        <div class='input-group date' id='datetimepicker'>

            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
            </span>
            <%-- !! HACK !! - <s:text> doesn't accept data-format parameter --%>
            <joda:format value="${bottlesActionBean.bottle.producedDate}" var="bottleProdDate" pattern="yyyy-MM-dd HH:mm:ss" />
            <input data-format="YYYY-MM-DD HH:mm:ss" type="text" value="${bottleProdDate}" class="hidden"></input>
            <script>
                $(document).ready(function()
                {
                    $("#bottle-producedDate-input").val("${bottleProdDate}");
                });
            </script>
            <s:text name="bottle.producedDate" class="form-control" id="bottle-producedDate-input" />
            <%-- !! HACK  END !! --%>
        </div>
    </div>
</div>


<div class="form-group">
    <label for="bottle-lotCode-input"><f:message key="bottle.lotCode"/></label>
    <s:text name="bottle.lotCode" class="form-control" id="bottle-lotCode-input" />
</div>

<div class="form-group">
    <label for="bottle-store-input"><f:message key="bottle.store"/></label>
    <s:select name="bottle.store" class="form-control" id="bottle-store-input">
        <s:options-collection collection="${bottlesActionBean.stores}" value="id" label="name"/>
    </s:select>
</div>

<div class="form-group">
    <label for="bottle-sold"><f:message key="bottle.sold"/></label>
    <s:checkbox name="bottle.sold" class="form-control" id="bottle-sold" />
</div>

<script type="text/javascript">
    $(function() {
        $('#datetimepicker').datetimepicker({
        });
    });
</script>