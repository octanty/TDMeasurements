<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:errors/> 
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
            <s:label for="efficiency" class="col-sm-2 control-label" name="monsterWeapon.efficiency" />
            <div class="col-sm-10">
            <b>0% </b> <input type="text" class="form-control slider-input" id="efficiency" name="monsterWeapon.efficiency"  value="${actionBean.monsterWeapon.efficiency.intValue()}" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="${actionBean.monsterWeapon.efficiency.intValue()}" /> <b>100 % </b>
            </div>
        </div>
        <div class="form-group">
            <s:label for="hitRate" class="col-sm-2 control-label"  name="monsterWeapon.hitRate"/>
            <div class="col-sm-10">
                 <b>0 m</b> <input type="text" class="form-control slider-input" id="hitRate" name="monsterWeapon.hitRate"  value="${actionBean.monsterWeapon.hitRate.intValue()}" data-slider-min="0" data-slider-max="1000" data-slider-step="5" data-slider-value="${actionBean.monsterWeapon.hitRate.intValue()}" /> <b>1000 m </b>
            </div>
        </div>
        <div class="form-group">
            <s:label for="damage" class="col-sm-2 control-label" name="monsterWeapon.damage"/>
            <div class="col-sm-10">
               <b>0% </b> <input type="text" class="form-control slider-input" id="damage" name="monsterWeapon.damage"  value="${actionBean.monsterWeapon.damage.intValue()}" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="${actionBean.monsterWeapon.damage.intValue()}" /> <b>100 % </b>
            </div>
        </div>
            <div class="form-group">
            <s:label for="description" class="col-sm-2 control-label" name="monsterWeapon.description"/>
            <div class="col-sm-10">
                <s:textarea class="form-control" name="monsterWeapon.description" rows="3" id="description"/>
            </div>
        </div>

