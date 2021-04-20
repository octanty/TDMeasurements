package com.pa165.mlib.dao.impl;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.exception.DuplicateException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Album DAO.
 * Transactions are managed by container.
 * @author ibek
 */
public class AlbumDaoImpl implements AlbumDao {
    
    @Inject
    Logger logger;
    
    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;
    
    /**
     * Persist the given album to persistence context.
     * @param album to be persisted
     */
    @Override
    public void addAlbum(Album album) throws DuplicateException {
        try {
            em.persist(album);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Cannot create a new album due to: {0}", ex.getMessage());
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new DuplicateException();
            }
            throw ex;
        }
    }
    
    /**
     * Update the given album in persistence context and database after the commit.
     * @param album to be updated
     * @return updated album
     */
    @Override
    public Album updateAlbum(Album album) {
        if (album == null) {
            return null;
        }
        return em.merge(album);
    }
    
    /**
     * Remove album from the persistence context and database after the commit.
     * @param album to be removed
     */
    @Override
    public void removeAlbum(Album album) {
        if (album != null && !em.contains(album)) {
            album = em.merge(album);
        }
        em.remove(album);
    }
    
    /**
     * Get all the albums.
     * @return all the albums
     */
    @Override
    public List<Album> getAll() {
        return em.createQuery("SELECT a FROM Album a ORDER BY title", Album.class)
                .getResultList();
    }
    
    /**
     * Get album with given unique identifier.
     * @param id unique identifier for album
     * @return album
     */
    @Override
    public Album getAlbum(long id) {
        Album a = null;
        try {
            a = em.createQuery("SELECT a FROM Album a WHERE a.id = :id", Album.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            
        }
        return a;
    }
    
    /**
     * Get the album with a given title.
     * @param title of the album in search
     * @return album with defined title
     */
    @Override
    public Album getAlbum(String title) {
        Album a = null;
        try {
            a = em.createQuery("SELECT a FROM Album a WHERE a.title = :title", Album.class)
                    .setParameter("title", title)
                    .getSingleResult();
        } catch (Exception e) {
            
        }
        return a;
    }
    
    /**
     * Get all the albums with given artist.
     * @param artist who is author of the required albums
     * @return albums with defined artist
     */
    @Override
    public List<Album> getAlbumsWithArtist(Artist artist) {
        if (artist == null) {
            return new ArrayList<>();
        }
        return em.createQuery("select distinct a from Album a inner join a.songs as song inner join  song.artist as artist where artist.id = :artist", Album.class)
                .setParameter("artist", artist.getId())
                .getResultList();
    }
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
}
