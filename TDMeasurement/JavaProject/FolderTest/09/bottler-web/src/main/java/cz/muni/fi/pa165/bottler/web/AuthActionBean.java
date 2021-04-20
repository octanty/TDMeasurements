package cz.muni.fi.pa165.bottler.web;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;



/**
 * Auth Action Bean
 *
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@UrlBinding("/auth/{$event}")
public class AuthActionBean extends BaseActionBean {

    // main auth page
    @DefaultHandler
    public Resolution main() {

        return new RedirectResolution("/");
    }
    
    public Resolution logout() {
       
        // performs logout
       getContext().getRequest().getSession().invalidate();
       
       return new RedirectResolution("/");
    }
    
    public Resolution test()
    {
        return new ForwardResolution("/auth_test.jsp");
    }

}
