package com.muni.fi.pa165.actions.login;

import com.muni.fi.pa165.actions.efficiencies.*;
import com.muni.fi.pa165.actions.base.BaseActionBean;
import com.muni.fi.pa165.dto.MonsterDto;
import com.muni.fi.pa165.dto.MonsterWeaponDto;
import com.muni.fi.pa165.dto.WeaponDto;
import com.muni.fi.pa165.service.MonsterService;
import com.muni.fi.pa165.service.MonsterWeaponService;
import com.muni.fi.pa165.service.WeaponService;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stripes ActionBean for handling monsterMonsterWeapon operations.
 *
 * @author Aubrey Oosthuizen
 */
@UrlBinding("/loginerror")
public class LoginActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(LoginActionBean.class);

    @DefaultHandler
    public Resolution error() {
        getContext().getMessages().add(new LocalizableMessage("login.error"));
        return new ForwardResolution("/login.jsp");
    }
}
