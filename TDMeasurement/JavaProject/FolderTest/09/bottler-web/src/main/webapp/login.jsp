<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="index.login" page="login">
    <s:layout-component name="header">
    </s:layout-component>
    <s:layout-component name="body">

                <div class="col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><f:message key="login.prompt"/></h3>
                        </div>
                        <div class="panel-body">
                            <form name="loginform" action="" method="POST" accept-charset="UTF-8" role="form">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="<f:message key="login.usernamePlaceholder"/>" name="username"
                                               type="text">
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="<f:message key="login.passwordPlaceholder"/>" name="password"
                                               type="password" value="">
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input name="rememberMe" type="checkbox" value="true"> <f:message key="login.remember"/>
                                        </label>
                                    </div>
                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="<f:message key="index.login"/>">
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>

    </s:layout-component>
</s:layout-render>



