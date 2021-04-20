<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:errors/> 


    <div class="form-group">
             <div class="form-group">
            <s:label for="id"  class="col-sm-2 control-label" name="user.id" />
            <div class="col-sm-10">
                <input readonly="true" class="form-control" id="id" name="user.id" />
            </div>
        </div>
        <div class="form-group">
            <s:label for="username"  class="col-sm-2 control-label" name="user.username" />
            <div class="col-sm-10">
                <s:text class="form-control" id="username" name="user.username" value="${actionBean.user.username}"/>
            </div>
        </div>
                <div class="form-group">
            <s:label for="password" class="col-sm-2 control-label" name="user.password"  />
            <div class="col-sm-10">
                <s:password class="form-control" id="password" name="user.password"  value="${actionBean.user.password}" /> </div>
            </div>

             <div class="form-group">
         <s:label for="authority" class="col-sm-2 control-label" name="user.authority"/>
            <div class="col-sm-10">
                <s:select class="form-control"   id="authority" name="user.authority" ><s:options-enumeration  enum="com.muni.fi.pa165.enums.UserGroup"/></s:select>
                </div>
            </div>
                 <div class="form-group">
        <s:label for="enabled" class="col-sm-2 control-label" name="user.enabled"/>
            <div class="col-sm-10">
                <s:select class="form-control"   id="authority" name="user.enabled"   ><s:options-enumeration  enum="com.muni.fi.pa165.enums.UserStatus"/></s:select>
                </div>
            </div>

    </div>