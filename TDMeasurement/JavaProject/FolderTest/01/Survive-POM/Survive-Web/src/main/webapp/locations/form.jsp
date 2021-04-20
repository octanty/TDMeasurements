<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
    if (top.location != location) {
         top.location.href = document.location.href ;
    }
		$(function(){
			window.prettyPrint && prettyPrint();

        $('.slider-input').slider({
          formater: function(value) {
            return 'Current value: '+value;
          }
        });

   

    });
</script>

                      
       <div class="form-group">
            <s:label for="monsterQuantity" class="col-sm-2 control-label" name="monsterArea.monsterQuantity" />
            <div class="col-sm-10">
               <b>0 </b> <input type="text" class="form-control slider-input" id="monsterQuantity" name="monsterArea.monsterQuantity"  value="${actionBean.monsterArea.monsterQuantity}" data-slider-min="0" data-slider-max="10000" data-slider-step="1" data-slider-value="${actionBean.monsterArea.monsterQuantity}" /> 
            </div>
        </div>


    