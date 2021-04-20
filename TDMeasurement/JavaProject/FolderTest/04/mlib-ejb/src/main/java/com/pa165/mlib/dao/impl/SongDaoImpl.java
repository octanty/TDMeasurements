package com.pa165.mlib.dao.impl;

import com.pa165.mlib.dao.SongDao;
import com.pa165.mlib.entity.Song;
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
 * Song DAO. Transactions are managed by container.
 *
 * @author ibek
 */
public class SongDaoImpl implements SongDao {

    @Inject
    Logger logger;

    @PersistenceContext(unitName = "mlib-pu")
    EntityManager em;

    /**
     * Persist the given song to persistence context.
     *
     * @param song to be persisted
     */
    @Override
    public void addSong(Song song) throws DuplicateException {
        try {
            em.persist(song);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Cannot create a new song due to: {0}", ex.getMessage());
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new DuplicateException();
            }
            throw ex;
        }
    }

    /**
     * Update the given song in persistence context and database after the
     * commit.
     *
     * @param song to be updated
     * @return updated song
     */
    @Override
    public Song updateSong(Song song) {
        if (song == null) {
            return null;
        }
        return em.merge(song);
    }

    /**
     * Remove song from the persistence context and database after the commit.
     *
     * @param song to be removed
     */
    @Override
    public void removeSong(Song song) {
        if (song != null && !em.contains(song)) {
            song = em.merge(song);
        }
        em.remove(song);
    }

    /**
     * Get all the songs.
     *
     * @return all the songs
     */
    @Override
    public List<Song> getAll() {
        return em.createQuery("SELECT s FROM Song s ORDER BY title", Song.class)
                .getResultList();
    }

    /**
     * Get song with given unique identifier.
     *
     * @param id unique identifier for song
     * @return song
     */
    @Override
    public Song getSong(long id) {
        Song s = null;
        try {
            s = em.createQuery("SELECT s FROM Song s WHERE s.id = :id", Song.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {

        }
        return s;
    }

    /**
     * Get all the songs with given title.
     *
     * @param title what should be song called
     * @return songs with defined title
     */
    @Override
    public Song getSong(String title) {
        Song s = null;
        try {
            s = em.createQuery("SELECT s FROM Song s WHERE s.title = :title", Song.class)
                    .setParameter("title", title)
                    .getSingleResult();
        } catch (NoResultException e) {

        }
        return s;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Song> getSongsInAlbum(long album_id) {
        return em.createQuery("SELECT s FROM Song s WHERE s.album.id = :albumId ORDER BY s.position", Song.class)
                .setParameter("albumId", album_id)
                .getResultList();
    }

}
