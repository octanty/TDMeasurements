<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.TestResultsActionBean" var="actionBean"/>
<script>
    $(document).ready(function() {

        // apply the table sorter
        $("#testResultsList").tablesorter({
            theme: "bootstrap",
            // disable sorting on buttons
            headers: {3: {sorter: false}},
            widthFixed: true,
            headerTemplate: '{content} {icon}', // new in v2.7. Needed to add the bootstrap icon!

            // widget code contained in the jquery.tablesorter.widgets.js file
            // use the zebra stripe widget if you plan on hiding any rows (filter widget)
            widgets: ["uitheme", "filter", "zebra"],
            widgetOptions: {
                // using the default zebra striping class name, so it actually isn't included in the theme variable above
                // this is ONLY needed for bootstrap theming if you are using the filter widget, because rows are hidden
                zebra: ["even", "odd"],
                // reset filters button
                filter_reset: ".reset"
            }
        });


    });
</script>
<table class="table table-hover" id="testResultsList">
    <thead>
        <tr>
            <th><f:message key="testresult.bottle"/></th>
            <th><f:message key="testresult.time"/></th>
            <th><f:message key="testresult.toxic"/></th>
            <th class="filter-false" style="width:160px">&nbsp;</th>

        </tr>
    </thead>
    <tbody>
        <c:forEach items="${actionBean.tests}" var="testResult">
            <tr class="with-button no-link">
                <td>
                    <s:link beanclass="cz.muni.fi.pa165.bottler.web.BottlesActionBean" event="detail">
                        <s:param name="bottle.id" value="${testResult.bottle.id}"/><c:out value="${testResult.bottle.liquor.name}"/>, ID: <c:out value="${testResult.bottle.id}"/>
                    </s:link>
                </td>
                <td>
                    <time class="timeago" datetime="<c:out value="${testResult.time}"/>">
                        <c:out value="${testResult.time}"/>
                    </time>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${testResult.isToxic()}">
                            <f:message key="yes"/>
                        </c:when>
                        <c:otherwise>
                            <f:message key="no"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td style="width:150px;text-align:right" class="no-link">
                  
                    <s:form beanclass="cz.muni.fi.pa165.bottler.web.TestResultsActionBean">


                        <s:link class="btn btn-primary btn-sm" beanclass="cz.muni.fi.pa165.bottler.web.TestResultsActionBean" event="edit">
                            <s:param name="testResult.id" value="${testResult.id}"/><f:message key="edit"/>
                        </s:link>


                        <s:hidden name="testResult.id" value="${testResult.id}"/>

                        <f:message key="areyousuretodelete" var="msg">
                <f:param value="${testResult.id}" />
            </f:message>

            <s:submit class="btn btn-danger btn-sm" onclick="return confirm('${msg}');" name="delete"> <f:message key="delete"/></s:submit>


        </s:form>



    </td>
</tr>

</c:forEach>

</tbody>



</table>
<c:if test="${fn:length(actionBean.tests) == 0}">
    <div class="alert alert-warning"> <f:message key="noitemsfound" /></div>
</c:if>