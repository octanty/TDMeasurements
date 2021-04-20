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
        <div class="form-group">
            <s:label for="name"  class="col-sm-2 control-label" name="weapon.name" />
            <div class="col-sm-10">
                <s:text class="form-control" id="name" name="weapon.name"/>
            </div>
        </div>
                <div class="form-group">
            <s:label for="weaponClass" class="col-sm-2 control-label" name="weapon.weaponClass"/>
            <div class="col-sm-10">
                <s:select class="form-control"  id="weaponClass" name="weapon.weaponClass"><s:options-enumeration enum="com.muni.fi.pa165.enums.WeaponClass"/></s:select>
                </div>
            </div>

                    <div class="form-group">
            <s:label for="weaponType" class="col-sm-2 control-label" name="weapon.weaponType"/>
            <div class="col-sm-10">
                <s:select class="form-control"  id="weaponType" name="weapon.weaponType"><s:options-enumeration enum="com.muni.fi.pa165.enums.WeaponType"/></s:select>
                </div>
            </div>


        <div class="form-group">
            <s:label for="range" class="col-sm-2 control-label" name="weapon.range" />
            <div class="col-sm-10">
            <b>0 m</b> <input type="text" class="form-control slider-input" id="range" name="weapon.range"  value="${actionBean.weapon.range.intValue()}" data-slider-min="0" data-slider-max="1000" data-slider-step="5" data-slider-value="${actionBean.weapon.range.intValue()}" /> <b>1000 m </b>
            </div>
        </div>
        <div class="form-group">
            <s:label for="caliber" class="col-sm-2 control-label"  name="weapon.caliber"/>
            <div class="col-sm-10">
                 <b>0 mm </b> <input type="text" class="form-control slider-input" id="caliber" name="weapon.caliber"  value="${actionBean.weapon.caliber}" data-slider-min="0" data-slider-max="50" data-slider-step="0.1" data-slider-value="${actionBean.weapon.caliber}" /> <b>50 mm </b>
            </div>
        </div>
        <div class="form-group">
           <s:label for="rounds" class="col-sm-2 control-label" name="weapon.rounds"/>
            <div class="col-sm-10">
              <b>0 </b> <input type="text" class="form-control slider-input" id="rounds" name="weapon.rounds"  value="${actionBean.weapon.rounds}" data-slider-min="0" data-slider-max="50" data-slider-step="1" data-slider-value="${actionBean.weapon.rounds}" /> <b>50 </b>
            </div>
        </div>
            <div class="form-group">
            <s:label for="description" class="col-sm-2 control-label" name="weapon.description"/>
            <div class="col-sm-10">
                <s:textarea class="form-control" name="weapon.description" rows="3" id="description"/>
            </div>
        </div>
    </div>