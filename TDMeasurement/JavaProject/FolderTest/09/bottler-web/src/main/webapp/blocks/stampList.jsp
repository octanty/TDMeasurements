<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.StampsActionBean" var="actionBean"/>
<script>
    $(document).ready(function() {

        // apply the table sorter
        $("#stampsList").tablesorter({
            theme: "bootstrap",
            // disable sorting on buttons
            headers: {2: {sorter: false}},
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
<table class="table table-hover" id="stampsList">
    <thead>
        <tr>
            <th><f:message key="stamp.number_of_stamp"/></th>
            <th><f:message key="stamp.issued_date"/></th>
            <c:if test="${actionBean.userIsPermittedToEdit || actionBean.userIsPermittedToDelete}">
                <th class="filter-false" style="width:160px">&nbsp;</th>
            </c:if>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${actionBean.tests}" var="stamp">
            <tr class="with-button row-link">
                <td>
                    <s:link beanclass="cz.muni.fi.pa165.bottler.web.StampsActionBean" event="detail">
                        <s:param name="stamp.id" value="${stamp.id}"/><c:out value="${stamp.numberOfStamp}"/>
                    </s:link>
                </td>
                <td>
                    <time class="timeago" datetime="<c:out value="${stamp.issuedDate}"/>">
                        <c:out value="${stamp.issuedDate}"/>
                    </time>
                </td>

                <c:if test="${actionBean.userIsPermittedToEdit || actionBean.userIsPermittedToDelete}">

                <td style="width:150px;text-align:right" class="no-link">
                   <!-- <button type="button" class="btn btn-default btn-sm" onclick="stampEdit(${stamp.id});">
                        <span class="glyphicon glyphicon-edit"></span> Edit
                    </button>-->

                    <s:form beanclass="cz.muni.fi.pa165.bottler.web.StampsActionBean">

                        <c:if test="${actionBean.userIsPermittedToEdit }">
                            <s:link class="btn btn-primary btn-sm" beanclass="cz.muni.fi.pa165.bottler.web.StampsActionBean" event="edit">
                                <s:param name="stamp.id" value="${stamp.id}"/><f:message key="edit"/>
                            </s:link>
                        </c:if>


                        <s:hidden name="stamp.id" value="${stamp.id}"/>

                        <f:message key="areyousuretodelete" var="msg">
                <f:param value="${stamp.numberOfStamp}" />
            </f:message>

            <s:submit class="btn btn-danger btn-sm" onclick="return confirm('${msg}');" name="delete"> <f:message key="delete"/></s:submit>


        </s:form>



    </td>
    </c:if>
</tr>

</c:forEach>

</tbody>



</table>
<c:if test="${fn:length(actionBean.tests) == 0}">
    <div class="alert alert-warning"> <f:message key="noitemsfound" /></div>
</c:if>