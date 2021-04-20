<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="form-group">
    <label for="store_name_input"><f:message key="store.name"/></label>
    <s:text name="store.name" class="form-control" id="store_name_input" />
</div>

<div class="form-group">
    <label for="store_address_input"><f:message key="store.address"/></label>
    <s:text name="store.address" class="form-control" id="store_address_input" />
</div>

<div class="form-group">
    <label for="store_ico_input"><f:message key="store.ico"/></label>
    <s:text name="store.ico" class="form-control" id="store_ico_input" />
</div>