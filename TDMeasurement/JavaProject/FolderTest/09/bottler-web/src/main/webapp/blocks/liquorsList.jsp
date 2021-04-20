<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.LiquorsActionBean" var="liquorsActionBean"/>
<script>
    $(document).ready(function() {
// apply the table sorter
        $("#liquorsList").tablesorter({
            theme: "bootstrap",
            // disable sorting on buttons
            headers: {5: {sorter: false}},
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

<table class="table table-hover" id="liquorsList">
    <thead>
        <tr>
            <th><f:message key="liquor.name"/></th>
            <th><f:message key="liquor.ean"/></th>
            <th><f:message key="liquor.alcoholPercentage"/></th>
            <th><f:message key="liquor.volume"/></th>
            <th><f:message key="liquor.producer"/></th>
            <c:if test="${liquorsActionBean.userIsPermittedToEdit || liquorsActionBean.userIsPermittedToDelete}">
                <th class="filter-false" style="width:150px">&nbsp;</th>
            </c:if>
        </tr>
    </thead>

    <tbody>
        <c:forEach items="${liquorsActionBean.liquors}" var="liquor">
            <tr class="with-button row-link">
                <td>
                    <s:link beanclass="cz.muni.fi.pa165.bottler.web.LiquorsActionBean" event="detail">
                        <s:param name="liquor.id" value="${liquor.id}"/><c:out value="${liquor.name}"/>
                    </s:link>
                </td>
                <td><c:out value="${liquor.ean}"/></td>
                <td><c:out value="${liquor.alcoholPercentage}"/></td>
                <td><c:out value="${liquor.volume}"/></td>
                <td><c:out value="${liquor.producer.name}"/></td>


                <c:if test="${liquorsActionBean.userIsPermittedToEdit || liquorsActionBean.userIsPermittedToDelete}">
                <td style="width:150px;text-align:right" class="no-link">

                    <s:form beanclass="cz.muni.fi.pa165.bottler.web.LiquorsActionBean">
                        <c:if test="${liquorsActionBean.userIsPermittedToEdit }">
                            <s:link class="btn btn-primary btn-sm" beanclass="cz.muni.fi.pa165.bottler.web.LiquorsActionBean" event="edit">
                                <s:param name="liquor.id" value="${liquor.id}"/><f:message key="edit"/>
                            </s:link>
                        </c:if>

                        <s:hidden name="liquor.id" value="${liquor.id}"/>

                        <f:message key="areyousuretodelete" var="msg">
                <f:param value="${liquor.name}" />
            </f:message>

            <c:if test="${liquorsActionBean.userIsPermittedToDelete}">
                <s:submit class="btn btn-danger btn-sm" onclick="return confirm('${msg}');" name="delete"> <f:message key="delete"/></s:submit>
            </c:if>
        </s:form>
    </td>
    </c:if>
</tr>
</c:forEach>
</tbody>
</table>

<c:if test="${fn:length(liquorsActionBean.liquors) == 0}">
    <div class="alert alert-warning"> <f:message key="noitemsfound" /></div>

</c:if>