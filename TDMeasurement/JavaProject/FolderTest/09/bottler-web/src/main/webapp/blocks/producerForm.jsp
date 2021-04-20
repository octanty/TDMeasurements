<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
   

    <div class="form-group">
        <label for="producer_name_input"><f:message key="producer.name"/></label>
        <s:text name="producer.name" class="form-control" id="producer_name_input" />
    </div>

    <div class="form-group">
        <label for="producer_address_input"><f:message key="producer.address"/></label>
        <s:text name="producer.address" class="form-control" id="producer_address_input" />
    </div>

    <div class="form-group">
        <label for="producer_ico_input"><f:message key="producer.ico"/></label>
       <s:text name="producer.ico" class="form-control" id="producer_ico_input" />
    </div>

   <!-- <button type="submit" class="btn btn-default">Create producer</button>-->
