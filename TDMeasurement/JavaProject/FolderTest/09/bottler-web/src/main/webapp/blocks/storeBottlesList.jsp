<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.StoresActionBean" var="storesActionBean"/>
<script>
    $(document).ready(function() {
        // apply the table sorter
        $("#bottlesList").tablesorter({
            theme: "bootstrap",
            // disable sorting on buttons
            headers: {6: {sorter: false}},
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

<table class="table table-hover" id="bottlesList">
    <thead>
        <tr>
            <th style="width:50px">ID</th>
            <th><f:message key="bottle.liquor"/></th>
            <th><f:message key="bottle.stamp"/></th>
            <th><f:message key="bottle.producedDate"/></th>
            <th><f:message key="bottle.lotCode"/></th>

        </tr>
    </thead>

    <tbody>
        <c:forEach items="${storesActionBean.bottles}" var="bottle">
            <tr class="with-button row-link">
                <td>
                    <s:link beanclass="cz.muni.fi.pa165.bottler.web.BottlesActionBean" event="detail">
                        <s:param name="bottle.id" value="${bottle.id}"/><c:out value="${bottle.id}"/>
                    </s:link>
                </td>

                <td>
                    <s:link beanclass="cz.muni.fi.pa165.bottler.web.BottlesActionBean" event="detail">
                        <s:param name="bottle.id" value="${bottle.id}"/><c:out value="${bottle.liquor.name}"/>
                    </s:link>
                </td>
                <td><c:out value="${bottle.stamp.numberOfStamp}"/></td>


                <td>
                    <joda:format value="${bottle.producedDate}" pattern="yyyy-MM-dd HH:mm:ss" />

                </td>


                <td><c:out value="${bottle.lotCode}"/></td>


            </tr>
        </c:forEach>
    </tbody>
</table>

<c:if test="${fn:length(storesActionBean.bottles) == 0}">
    <div class="alert alert-warning"> <f:message key="noitemsfound" /></div>

</c:if>