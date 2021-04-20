/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.mlib.manager;

import com.pa165.mlib.dto.Role;
import com.pa165.mlib.dto.UserTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.UserService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ibek
 */
@SessionScoped
@Named
public class LoginManager implements Serializable {

    @Inject
    transient Logger logger;

    @Inject
    private CredentialsManager cm;

    @Inject
    private UserService us;

    private UserTO user;

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();

        final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        try {
            logger.log(Level.INFO, "Logining {0}", cm.getUsername());
            request.login(cm.getUsername(), cm.getPassword());
            this.user = us.getUser(cm.getUsername());
            return "/home";
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Wrong username and/or password.");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong username and/or password."));
        }
        return "/login";
    }

    public String logout() {
        user = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();

        final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        request.getSession(false).invalidate();
        return "/home";
    }
    
    public String register() {
        try {
            logger.log(Level.INFO, "Registering {0}", cm.getUsername());
            UserTO newuser = new UserTO();
            newuser.setUsername(cm.getUsername());
            newuser.setPassword(cm.getPassword());
            us.createNewUser(newuser, Role.valueOf(cm.getRole()));
            return "/home";
        } catch (DuplicateException ex) {
            logger.log(Level.WARNING, "User already exists.");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User already exists."));
        }
        return "/administration/newuser";
    }

    public boolean isLoggedIn() {
        return getUser() != null;
    }

    /**
     * @return the us
     */
    public UserService getUs() {
        return us;
    }

    /**
     * @param us the us to set
     */
    public void setUs(UserService us) {
        this.us = us;
    }

    /**
     * @return the user
     */
    public UserTO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserTO user) {
        this.user = user;
    }
    
    public void remove(UserTO user) {
        us.removeUser(user);
    }
    
    public List<Role> getAllRoles() {
        return Arrays.asList(Role.values());
    }

}
