/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165.actions.base;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.apache.taglibs.standard.functions.Functions;

/**
 *
 * @author Auron
 */
    public abstract class BaseActionBean implements ActionBean {
    private ActionBeanContext context = null;

    /**
     *
     * @param context
     */
    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    /**
     *
     * @return
     */
    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    /**
     *
     * @param s
     * @return
     */
    public static String escapeHTML(String s) {
        return Functions.escapeXml(s);
    }
    

}
