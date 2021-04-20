<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">

        <h1 style="text-align: center"><f:message key="text.title"/></h1>
          <div class="row">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="docs-assets/images/monsters.png" alt="Monsters" width="400" height="400"/>
                    <div class="caption">
                        <h3><f:message key="index.monster.title"/></h3>
                        <p><f:message key="index.monster.context"/></p>
                        <p><s:link beanclass="com.muni.fi.pa165.actions.monster.MonsterActionBean" class="btn btn-primary"><f:message key="index.monster.button"/></s:link></p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="docs-assets/images/areas.png" alt="Area" width="400" height="400"/>
                    <div class="caption">
                        <h3><f:message key="index.area.title"/></h3>
                        <p><f:message key="index.area.context"/></p>
                        <p><s:link beanclass="com.muni.fi.pa165.actions.area.AreaActionBean" class="btn btn-primary"><f:message key="index.area.button"/></s:link></p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="docs-assets/images/weapons.png" alt="Weapons" width="400" height="400"/>
                    <div class="caption">
                        <h3><f:message key="index.weapon.title"/></h3>
                        <p><f:message key="index.weapon.context"/></p>
                        <p><s:link beanclass="com.muni.fi.pa165.actions.weapon.WeaponActionBean" class="btn btn-primary"><f:message key="index.weapon.button"/></s:link></p>
                    </div>
                </div>
            </div>
        </div>
        <div style="text-align: center">         
            <p><f:message key="index.headline"/></p>
            <p><f:message key="index.headline2"/></p>
            Please <a href="/login.jsp">log in </a> to access the SURVIVE resources

        </div>
            
           
       

    </s:layout-component>
</s:layout-render>

