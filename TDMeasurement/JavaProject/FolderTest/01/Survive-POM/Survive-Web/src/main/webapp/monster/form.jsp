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
            <s:label for="name"  class="col-sm-2 control-label" name="monster.name" />
            <div class="col-sm-10">
                <s:text class="form-control" id="name" name="monster.name"/>
            </div>
        </div>
        <div class="form-group">
            <s:label for="stamina" class="col-sm-2 control-label" name="monster.stamina" />          
            <div class="col-sm-10">
                 <b>0 % </b> <input type="text" class="form-control slider-input" id="stamina" name="monster.stamina"  value="${actionBean.monster.stamina.intValue()}" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="${actionBean.monster.stamina.intValue()}" /> <b>100 % </b>
            </div>
        </div>
        <div class="form-group">
            <s:label for="height" class="col-sm-2 control-label"  name="monster.height"/>
            <div class="col-sm-10">
                <b>0 kg </b> <input type="text" class="form-control slider-input" id="height" name="monster.height"  value="${actionBean.monster.height.intValue()}" data-slider-min="0" data-slider-max="1000" data-slider-step="5" data-slider-value="${actionBean.monster.height.intValue()}" /> <b>1000 kg</b>
            </div>
        </div>
        <div class="form-group">
            <s:label for="strength" class="col-sm-2 control-label" name="monster.strength"/>
            <div class="col-sm-10">
              <b>0 % </b> <input type="text" class="form-control slider-input" id="strength" name="monster.strength" value="${actionBean.monster.strength.intValue()}" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="${actionBean.monster.strength.intValue()}" /> <b>100 % </b>
            </div>
        </div>
        <div class="form-group">
            <s:label for="agility" class="col-sm-2 control-label" name="monster.agility"/>
            <div class="col-sm-10">
                <b>0 % </b> <input type="text" class="form-control slider-input" id="agility" name="monster.agility" value="${actionBean.monster.agility.intValue()}" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="${actionBean.monster.strength.intValue()}" /> <b>100 % </b>
            </div>
        </div>
        <div class="form-group">
            <s:label for="dangerLevel" class="col-sm-2 control-label" name="monster.dangerLevel"/>
            <div class="col-sm-10">
             <b>0 % </b> <input type="text" class="form-control slider-input" id="dangerLevel" name="monster.dangerLevel" value="${actionBean.monster.dangerLevel.intValue()}" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="${actionBean.monster.dangerLevel.intValue()}" /> <b>100 % </b>
            </div>
        </div>
        <div class="form-group">
            <s:label for="weight" class="col-sm-2 control-label" name="monster.weight"/>
            <div class="col-sm-10">
                 <b>0 cm </b> <input type="text" class="form-control slider-input" id="weight" name="monster.weight"  value="${actionBean.monster.weight.intValue()}" data-slider-min="0" data-slider-max="10000" data-slider-step="5" data-slider-value="${actionBean.monster.weight.intValue()}" /> <b>10000 cm</b>
            </div>
        </div>
        <div class="form-group">
            <s:label for="monsterClass" class="col-sm-2 control-label" name="monster.class"/>
            <div class="col-sm-10">
                <s:select class="form-control"   id="monsterClass" name="monster.monsterClass"><s:options-enumeration  enum="com.muni.fi.pa165.enums.MonsterClass"/></s:select>
                </div>
            </div>

            <div class="form-group">
            <s:label for="description" class="col-sm-2 control-label" name="monster.description"/>
            <div class="col-sm-10">
                <s:textarea class="form-control" name="monster.description" rows="3" id="description"/>
            </div>
        </div>

        <div class="form-group">
            <s:label for="imagePath" class="col-sm-2 control-label" name="monster.image"/>
            <div class="col-sm-10">
                <s:text name="monster.imagePath" class="form-control" id="imagePath" />
            </div>
        </div>
    </div>