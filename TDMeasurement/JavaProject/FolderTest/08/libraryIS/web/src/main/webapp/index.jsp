<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:layout-render name="/layout.jsp" titlekey="main.title">    
    <s:layout-component name="tab">
        <li class="active"><a href="${pageContext.request.contextPath}/index.jsp"><span><f:message key="welcome.title"/></span></a></li>
        <li><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean"><span><f:message key="impression.title"/></span></s:link></li>
        <sec:authorize access="hasRole('ROLE_USER')">
            <li><s:link beanclass="cz.muni.fi.pa165.web.LoanEditBean" event="myLoans"><span><f:message key="myloans.title"/></span></s:link></li>      
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean"><span><f:message key="readers.title"/></span></s:link></li>      
        </sec:authorize>
        </s:layout-component>
        <s:layout-component name="body">
        <h1>Welcome in our library</h1>
        <div class="post">
            <p>
                The library of IBTS, one of the largest English language theological libraries on the continent of Europe, is a rich resource for theological study. The library holds more than 70.000 volumes, including materials in German and other European languages, and subscribes to about 180 mainly theological periodicals.
                The Library is vital to theological education at IBTS. Students in various programmes learn to undertake independent research to receive the greatest benefit from their studies. Beside focussing on the needs of IBTS, the Library also serves the diverse EBF family and the wider scholarly community in Prague and beyond.
                The IBTS library is a highly respected academic resource provider within the Czech Republic and Europe. Two recent prestigious awards of book collections have further promoted the international standing of the library: it was selected as one of the twenty most important missiological libraries worldwide and was given the prestigious ISSR Award by the International Society for Science and Religion.
                <br/><br/><b>You can visit us here:</b><br/>
                <iframe width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Allama+Iqbal+Open+University,+Islamabad,+Islamabad+Capital+Territory,+Pakistan&amp;aq=0&amp;oq=allama+iqbal+open&amp;sll=33.682872,73.057258&amp;sspn=0.016284,0.033023&amp;ie=UTF8&amp;hq=Allama+Iqbal+Open+University,+Islamabad,+Islamabad+Capital+Territory,+Pakistan&amp;t=m&amp;ll=33.685068,73.054705&amp;spn=0.024997,0.036478&amp;z=14&amp;output=embed"></iframe><br /><small><a href="https://maps.google.com/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=Allama+Iqbal+Open+University,+Islamabad,+Islamabad+Capital+Territory,+Pakistan&amp;aq=0&amp;oq=allama+iqbal+open&amp;sll=33.682872,73.057258&amp;sspn=0.016284,0.033023&amp;ie=UTF8&amp;hq=Allama+Iqbal+Open+University,+Islamabad,+Islamabad+Capital+Territory,+Pakistan&amp;t=m&amp;ll=33.685068,73.054705&amp;spn=0.024997,0.036478&amp;z=14" style="color:#0000FF;text-align:left">View Larger Map</a></small>
                <br/>
                <br/>
                <b>We are looking forward to your visit!</b><br/>
                <img src="http://upload.wikimedia.org/wikipedia/commons/c/cd/Allama_Iqbal_Open_University,_Islamabad.JPG" width="400" border="3"/>
            </p>
        </div>          
    </s:layout-component>
</s:layout-render>