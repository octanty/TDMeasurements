package cz.muni.fi.pa165.web;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 *
 * @author Mjartan
 */
public abstract class BaseBean implements ActionBean {

    private ActionBeanContext context;

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
}
