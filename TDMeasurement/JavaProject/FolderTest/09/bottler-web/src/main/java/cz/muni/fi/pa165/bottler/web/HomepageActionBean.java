package cz.muni.fi.pa165.bottler.web;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@UrlBinding("/index")
public class HomepageActionBean extends BaseActionBean {

    @DefaultHandler
    public Resolution index() {
        return new ForwardResolution("/index.jsp");
    }
}
