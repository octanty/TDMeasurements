<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
     
        <div style="width: 440px; margin-left: auto; margin-right: auto">
      <form class="form-signin">
          <h4 class="form-signin-heading"><f:message key="logon.header"/></h4>
        <input type="text" class="form-control" placeholder="Email address" required autofocus>
        <input type="password" class="form-control" placeholder="Password" required>
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> <f:message key="logon.remember"/>
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><f:message key="navigation.signin"/></button>
      </form>
        </div>
  


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
 </s:layout-component>
</s:layout-render>