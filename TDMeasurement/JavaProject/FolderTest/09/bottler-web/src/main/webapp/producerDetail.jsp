<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="producers" page="producers">
    <s:layout-component name="header">
        <!-- adding to header -->
    </s:layout-component>
    <s:layout-component name="body">

        <s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.ProducersActionBean" var="actionBean"/>
      
        <h2>${actionBean.producer.name}</h2>
        
        
        <p><strong><f:message key="producer.address" />:</strong> ${actionBean.producer.address}</p>
        <p><strong><f:message key="producer.ico" />:</strong> ${actionBean.producer.ico}</p>

        <c:if test="${actionBean.statistics.allBottlesCount > 0}">
        <h3><f:message key="statistics" /></h3>
        <p><strong><f:message key="producer.count_bottles" />:</strong> ${actionBean.statistics.allBottlesCount}</p>
        <p><strong><f:message key="toxic" />:</strong> ${actionBean.statistics.toxicBottlesCount}</p>

        <script type="text/javascript">
            google.load("visualization", "1", {packages:["corechart"]});
            google.setOnLoadCallback(drawChart);
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['<f:message key="producer.count_bottles" />', '<f:message key="toxic_bottles" />'],
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