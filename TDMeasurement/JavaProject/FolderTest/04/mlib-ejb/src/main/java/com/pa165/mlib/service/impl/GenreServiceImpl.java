package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.GenreService;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

/**
 *
 * @author xbek
 */
@MlibService
@Stateless
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed("ADMIN")
public class GenreServiceImpl implements GenreService {
    
    @Inject
    GenreDao gd;
    
    @Inject
    EntityDTOTransformer transformer;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public GenreTO createNewGenre(GenreTO genre) throws DuplicateException {
        Genre g = new Genre();
        g.setName(genre.getName());
        gd.addGenre(g);
        return transformer.transformGenreTO(g);
    }

    @Override
    @PermitAll
    public List<GenreTO> getAllGenres() {
        List<GenreTO> list = new ArrayList<>();
        for (Genre g : gd.getAll()) {
            list.add(transformer.transformGenreTO(g));
        }
        return list;
    }

    @Override
    @PermitAll
    public GenreTO getGenre(String name) {
        return transformer.transformGenreTO(gd.getGenre(name));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public GenreTO updateGenre(GenreTO oldGenre, GenreTO newGenre) {
        Genre genre = gd.getGenre(oldGenre.getName());
        genre.setName(newGenre.getName());
        gd.updateGenre(genre);
        return newGenre;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeGenre(GenreTO genreTO) {
        Genre genre = gd.getGenre(genreTO.getName());
        if (genre == null) {
            return false;
        }
        gd.removeGenre(genre);
        return true;
    }
    
    public void setGenreDao(GenreDao genreDao) {
        this.gd = genreDao;
    }
    
    public void setTransformer(EntityDTOTransformer transformer) {
        this.transformer = transformer;
    }
    
}
