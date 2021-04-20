package cz.muni.fi.pa165.bottler.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
public class SecurityManagerSetting {

    public void setSecurityManagerFromFile() {
        String resource = "applicationContext.xml";
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(resource);
        org.apache.shiro.mgt.SecurityManager securityManager = (SecurityManager)appCtx.getBean("securityManager");
        SecurityUtils.setSecurityManager(securityManager);
    }

}
