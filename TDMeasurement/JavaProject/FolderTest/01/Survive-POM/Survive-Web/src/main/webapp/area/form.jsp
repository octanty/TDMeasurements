<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:errors/>
<div class="form-group">
           <div class="form-group">
            <div class="form-group">
                <s:label for="name"  class="col-sm-2 control-label" name="area.res.name" />
                <div class="col-sm-10">
                    <s:text class="form-control" id="area.name" name="area.name"/>
                </div>
            </div>
            <div class="form-group">
                <s:label for="areaTerrain" class="col-sm-2 control-label" name="area.res.terrain"/>
                <div class="col-sm-10">
                    <s:select  id="terrainType" name="area.terrain"><s:options-enumeration enum="com.muni.fi.pa165.enums.TerrainType"/></s:select>
                    </div>
                </div>
            </div>
            

                <div class="form-group">
                <s:label for="description" class="col-sm-2 control-label" name="area.res.description"/>
                <div class="col-sm-10">
                    <s:textarea class="form-control" name="area.description" rows="3" id="description"/>
                </div>
            </div>
        </div>
                
            