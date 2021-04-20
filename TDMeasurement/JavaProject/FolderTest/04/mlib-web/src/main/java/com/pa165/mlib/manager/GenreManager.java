package com.pa165.mlib.manager;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.GenreService;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ibek
 */
@SessionScoped
@ManagedBean
@Named
public class GenreManager implements Serializable {
    
    @Inject
    transient Logger logger;
    
    @Inject
    GenreService service;
    
    private GenreTO gto = new GenreTO();
    
    public GenreTO init() {
        gto = new GenreTO();
        return gto;
    }
    
    public GenreTO init(String name) {
        gto = service.getGenre(name);
        return gto;
    }
 
    
    public GenreService getService() {
        return service;
    }
    
    public GenreTO getGenreTO() {
        return gto;
    }
    
    public String create() {
        logger.log(Level.INFO, "Creating {0}", gto);
        try {
            service.createNewGenre(gto);
            init();
        } catch (DuplicateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The genre cannot be created because it already exists."));
            return "/genre_detail";
        }
        return "/genres";
    }
    
    public String remove() {
        logger.log(Level.INFO, "Removing {0}", gto);
        service.removeGenre(gto);
        return "/genres";
    }
    
}
