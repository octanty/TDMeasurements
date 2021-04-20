<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>


<!-- form start -->
<s:form  class="form-horizontal" beanclass="cz.muni.fi.pa165.bottler.web.StoresActionBean">


    <div class="form-group">
        <label for="store_address_input" class="col-sm-2 control-label"><f:message key="store.address"/></label>
        <div class="col-sm-5">
            <s:text name="store.address" class="form-control" id="store_address_input" />
        </div>
        <div class="col-sm-3">
            <s:submit name="search" class="btn btn-primary"><f:message key="search.search"/></s:submit>
        </div>
    </div>







</s:form>