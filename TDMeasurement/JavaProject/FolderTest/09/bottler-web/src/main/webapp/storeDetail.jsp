<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="stores" page="stores">
    <s:layout-component name="header">
        <!-- adding to header -->
    </s:layout-component>
    <s:layout-component name="body">

        <s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.StoresActionBean" var="actionBean"/>
      
        <h2>${actionBean.store.name}</h2>
        
        
        <p><strong><f:message key="store.address" />:</strong> ${actionBean.store.address}</p>
        <p><strong><f:message key="store.ico" />:</strong> ${actionBean.store.ico}</p>
        
        <h3><f:message key="available_bottles" /></h3>

 <%@include file="blocks/storeBottlesList.jsp"%>

        <c:if test="${fn:length(actionBean.bottles) > 0}">
        <h3><f:message key="store.statistics" /></h3>
        <p><strong><f:message key="store.count_bottles" />:</strong> ${actionBean.statistics.allBottlesCount}</p>
        <p><strong><f:message key="toxic" />:</strong> ${actionBean.statistics.toxicBottlesCount}</p>

        <script type="text/javascript">
            google.load("visualization", "1", {packages:["corechart"]});
            google.setOnLoadCallback(drawChart);
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['<f:message key="store.count_bottles" />', '<f:message key="toxic_bottles" />'],
                    ['<f:message key="toxic" />', ${actionBean.statistics.toxicBottlesCount}],
                    ['<f:message key="nontoxic" />', ${actionBean.statistics.allBottlesCount - actionBean.statistics.toxicBottlesCount}]
                ]);

                var options = {
                    title: '<f:message key="bottles" />',
                    is3D: true
                };

                var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
                chart.draw(data, options);
            }
        </script>

        <div id="chart_div"></div>
        </c:if>

    </s:layout-component>
</s:layout-render>