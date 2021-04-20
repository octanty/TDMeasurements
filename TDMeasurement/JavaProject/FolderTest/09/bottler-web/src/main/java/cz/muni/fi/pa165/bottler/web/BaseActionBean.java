package cz.muni.fi.pa165.bottler.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Base Action Bean
 *
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
public class BaseActionBean implements ActionBean {

    private ActionBeanContext context;

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    private Subject currentUser;

    public Subject getCurrentUser() {
        return currentUser;
    }

    public Map getUser() {
        return currentUser.getPrincipals().oneByType(java.util.Map.class);
    }

    public BaseActionBean() {

        currentUser = SecurityUtils.getSubject();

        boolean isAdmin = currentUser.hasRole("admin");
        boolean isTester = currentUser.hasRole("tester");
        boolean isPoliceman = currentUser.hasRole("police");

        menuItems = new ArrayList<>();
        menuItems.add("index");

        if (isAdmin || isPoliceman) {
            menuItems.add("producers");
        }

        if (isAdmin || isTester) {
            menuItems.add("bottles");
            menuItems.add("testresults");
        }

        menuItems.add("stores");


        if (isAdmin) {
            menuItems.add("liquors");
            menuItems.add("stamps");
            menuItems.add("monitoring");
        }

    }

    private List<String> menuItems;

    public List<String> getMenuItems() {
        return this.menuItems;

    }

}
