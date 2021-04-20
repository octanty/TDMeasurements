<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:useActionBean beanclass="cz.muni.fi.pa165.bottler.web.LiquorsActionBean" var="liquorsActionBean"/>

<div class="form-group">
    <label for="liquor-ean-input"><f:message key="liquor.ean"/></label>
    <s:text name="liquor.ean" class="form-control" id="liquor-ean-input" />
</div>

<div class="form-group">
    <label for="liquor-name-input"><f:message key="liquor.name"/></label>
    <s:text name="liquor.name" class="form-control" id="liquor-name-input" />
</div>

<div class="form-group">
    <label for="liquor-alcoholPercentage-input"><f:message key="liquor.alcoholPercentage"/></label>
    <s:text name="liquor.alcoholPercentage" class="form-control" id="liquor-alcoholPercentage-input" />
</div>

<div class="form-group">
    <label for="liquor-volume-input"><f:message key="liquor.volume"/></label>
    <s:text name="liquor.volume" class="form-control" id="liquor-volume-input" />
</div>

<div class="form-group">
    <label for="liquor-producer-input"><f:message key="liquor.producer"/></label>
    <s:select name="liquor.producer" class="form-control" id="liquor-producer-input">
        <s:options-collection collection="${liquorsActionBean.producers}" value="id" label="name"/>
    </s:select>
</div>