package com.pa165.mlib.dao.impl;

import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Genre DAO
 * Transaction are managed by container
 * @author brazdil
 */
public class GenreDaoImpl implements GenreDao {
    
    @Inject
    Logger logger;
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    /**
     * Persists the given genre to persistence context
     * @param genre 
     */
    @Override
    public void addGenre(Genre genre) throws DuplicateException {
        try {
            em.persist(genre);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Cannot create a new genre due to: {0}", ex.getMessage());
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new DuplicateException();
            }
            throw ex;
        }
    }
    
    /**
     * Update the given genre
     * @param genre
     * @return 
     */
    @Override
    public Genre updateGenre(Genre genre) {
        if (genre == null) {
            return null;
        }
        return em.merge(genre);
    }
    
    /**
     * Remove the given genre from persistence context
     * @param genre 
     */
    @Override
    public void removeGenre(Genre genre) {
        if(genre != null && !em.contains(genre)) {
            em.merge(genre);
        }
        em.remove(genre);
    }
    
    /**
     * Read all genres
     * @return 
     */
    @Override
    public List<Genre> getAll() {
        return em.createQuery("SELECT g FROM Genre g ORDER BY g.name", Genre.class)
                .getResultList();
    }
    
    /**
     * Read genre via genre name
     * @param name
     * @return 
     */
    @Override
    public Genre getGenre(String name) {
         Genre g = null;
         try {
            g = em.createQuery("SELECT g FROM Genre g WHERE g.name = :name", Genre.class)
                   .setParameter("name", name)
                   .getSingleResult();
         } catch (NoResultException e) {
             e.printStackTrace();
         }
         return g;
    }
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
}
