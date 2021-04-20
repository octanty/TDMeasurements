package com.pa165.mlib.test.entity;

import com.pa165.mlib.dao.impl.ArtistDaoImpl;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.test.EntityTestBase;
import java.util.List;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class ArtistEntityTest extends EntityTestBase {

    @Test
    public void testAddArtist() throws Throwable {
        Artist artist = new Artist();
        artist.setName("Ales");
        ArtistDaoImpl ad = new ArtistDaoImpl();
        EntityManager em = getTestEntityManager();
        ad.setEntityManager(em);
        em.getTransaction().begin();
        ad.addArtist(artist);
        em.getTransaction().commit();
        Artist a2 = ad.getArtist(artist.getId());
        assertEquals(artist, a2);
    }
    
    @Test
    public void testGetAll() throws Throwable {
        Artist artist = new Artist();
        artist.setName("Hugo");
        Artist artist2 = new Artist();
        artist2.setName("Kokoska");
        ArtistDaoImpl ad = new ArtistDaoImpl();
        EntityManager em = getTestEntityManager();
        ad.setEntityManager(em);
        em.getTransaction().begin();
        ad.addArtist(artist);
        ad.addArtist(artist2);
        em.getTransaction().commit();
        List<Artist> list = ad.getAll();
        assertTrue(list != null);
        assertTrue(list.contains(artist));
        assertTrue(list.contains(artist2));
        
    }
    
    @Test
    public void testGetArtistById() throws Throwable {
        Artist artist = new Artist();
        artist.setName("Kamil");
        ArtistDaoImpl ad = new ArtistDaoImpl();
        EntityManager em = getTestEntityManager();
        ad.setEntityManager(em);
        em.getTransaction().begin();
        ad.addArtist(artist);
        em.getTransaction().commit();
        Artist a2 = ad.getArtist(artist.getId());
        assertEquals(artist, a2);
    }
    
    @Test
    public void testArtistRemove() throws Throwable {
        Artist artist = new Artist();
        artist.setName("Lukas");
        ArtistDaoImpl ad = new ArtistDaoImpl();
        EntityManager em = getTestEntityManager();
        ad.setEntityManager(em);
        em.getTransaction().begin();
        ad.addArtist(artist);
        em.getTransaction().commit();
        Artist a2 = ad.getArtist(artist.getId());
        em.getTransaction().begin();
        ad.removeArtist(a2);
        em.getTransaction().commit();
        Artist empty = ad.getArtist(artist.getId());
        assertNull(empty);
    }
    
    @Test
    public void testGetArtistByName() throws Throwable {
        Artist artist = new Artist();
        artist.setName("George");
        ArtistDaoImpl ad = new ArtistDaoImpl();
        EntityManager em = getTestEntityManager();
        ad.setEntityManager(em);
        em.getTransaction().begin();
        ad.addArtist(artist);
        em.getTransaction().commit();
        Artist a2 = ad.getArtist(artist.getName());
        assertEquals(artist, a2);
    }
    
    @Test
    public void testArtistUpdate() throws Exception {
        Artist mike = new Artist();
        mike.setName("Mike");
        ArtistDaoImpl am = new ArtistDaoImpl();
        EntityManager em = getTestEntityManager();
        am.setEntityManager(em);
        em.getTransaction().begin();
        am.addArtist(mike);
        em.getTransaction().commit();
        
        Artist mike2 = am.getArtist(mike.getId());
        assertEquals(mike, mike2);
        
        mike.setName("Michael");
        em.getTransaction().begin();
        mike = am.updateArtist(mike);
        em.getTransaction().commit();
        
    }
    
}
