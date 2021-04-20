package com.musiclibrary.euphonyrest.client;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.apache.taglibs.standard.functions.Functions;
import org.springframework.http.HttpHeaders;

/**
 * Base actionBean implementing the required methods for setting and getting
 * context.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
public abstract class BaseActionBean implements ActionBean {

    private ActionBeanContext context;
    final static String URI = "http://localhost:8080/pa165/server";

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    public HttpHeaders getHttpHeader() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Authorization", "Basic rest:rest");
        return requestHeaders;
    }

    public static String escapeHTML(String s) {
        return Functions.escapeXml(s);
    }
}
