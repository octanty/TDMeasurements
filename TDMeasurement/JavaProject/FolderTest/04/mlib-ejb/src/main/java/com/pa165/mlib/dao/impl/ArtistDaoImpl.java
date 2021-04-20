package com.pa165.mlib.dao.impl;

import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.entity.Artist;
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
 * Artist DAO Transaction are managed by container
 *
 * @author brazdil
 */
public class ArtistDaoImpl implements ArtistDao {
    
    @Inject
    Logger logger;

    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;

    /**
     * Persists the given artist to persistence context
     *
     * @param artist
     */
    @Override
    public void addArtist(Artist artist) throws DuplicateException {
        try {
            em.persist(artist);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Cannot create a new artist due to: {0}", ex.getMessage());
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new DuplicateException();
            }
            throw ex;
        }
    }

    /**
     * Update the given artist
     *
     * @param artist
     * @return
     */
    @Override
    public Artist updateArtist(Artist artist) {
        if (artist == null) {
            return null;
        }
        return em.merge(artist);
    }

    /**
     * Remove the given artist from persistence context
     *
     * @param artist
     */
    @Override
    public void removeArtist(Artist artist) {
        if (artist != null && !em.contains(artist)) {
            artist = em.merge(artist);
        }
        em.remove(artist);
    }

    /**
     * Read all artists
     *
     * @return
     */
    @Override
    public List<Artist> getAll() {
        return em.createQuery("SELECT a FROM Artist a ORDER by name", Artist.class)
                .getResultList();
    }

    /**
     * Read single artist via artist ID
     *
     * @param id
     * @return
     */
    @Override
    public Artist getArtist(Long id) {
        Artist a = null;
        try {
            a = em.createQuery("SELECT a FROM Artist a WHERE a.id = :id", Artist.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {

        }
        return a;
    }

    /**
     * Read artist/-s via artist name
     *
     * @param name
     * @return
     */
    @Override
    public Artist getArtist(String name) {
        Artist a = null;
        try {
            a = em.createQuery("SELECT a FROM Artist a WHERE a.name = :name", Artist.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {

        }
        return a;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

}
