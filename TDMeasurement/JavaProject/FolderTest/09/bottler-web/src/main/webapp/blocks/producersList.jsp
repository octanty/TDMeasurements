<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.ProducersActionBean" var="actionBean"/>
<script>
    $(document).ready(function() {

        // apply the table sorter
        $("#producersList").tablesorter({
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
<table class="table table-hover" id="producersList">
    <thead>
        <tr>
            <th><f:message key="producer.name"/></th>
            <th><f:message key="producer.address"/></th>
            <th><f:message key="producer.ico"/></th>
                <c:if test="${actionBean.userIsPermittedToEdit || actionBean.userIsPermittedToDelete}">
                <th class="filter-false" style="width:160px">&nbsp;</th>
                </c:if>

        </tr>
    </thead>
    <tbody>
        <c:forEach items="${actionBean.producers}" var="producer">
            <tr class="with-button row-link">
                <td>
                    <s:link beanclass="cz.muni.fi.pa165.bottler.web.ProducersActionBean" event="detail">
                        <s:param name="producer.id" value="${producer.id}"/><c:out value="${producer.name}"/>
                    </s:link>
                </td>
                <td><c:out value="${producer.address}"/></td>
                <td><c:out value="${producer.ico}"/></td>
                <c:if test="${actionBean.userIsPermittedToEdit || actionBean.userIsPermittedToDelete}">
                    <td style="width:160px;text-align:right" class="no-link">
                       <!-- <button type="button" class="btn btn-default btn-sm" onclick="producerEdit(${producer.id});">
                            <span class="glyphicon glyphicon-edit"></span> Edit
                        </button>-->

                        <s:form beanclass="cz.muni.fi.pa165.bottler.web.ProducersActionBean">


                            <c:if test="${actionBean.userIsPermittedToEdit}">
                                <s:link class="btn btn-primary btn-sm" beanclass="cz.muni.fi.pa165.bottler.web.ProducersActionBean" event="edit">
                                    <s:param name="producer.id" value="${producer.id}"/><f:message key="edit"/>
                                </s:link>
                            </c:if>


                            <s:hidden name="producer.id" value="${producer.id}"/>

                            <f:message key="areyousuretodelete" var="msg">
                                <f:param value="${producer.name}" />
                            </f:message>

                            <c:if test="${actionBean.userIsPermittedToDelete}">
                                <s:submit class="btn btn-danger btn-sm" onclick="return confirm('${msg}');" name="delete"> <f:message key="delete"/></s:submit>
                            </c:if>

                        </s:form>



                    </td>
                </c:if>
            </tr>

        </c:forEach>

    </tbody>



</table>
<c:if test="${fn:length(actionBean.producers) == 0}">
    <div class="alert alert-warning"> <f:message key="noitemsfound" /></div>
</c:if>