package com.pa165.mlib.test.service;

import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dto.ArtistTO;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.service.impl.ArtistServiceImpl;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class ArtistServiceTest {
    
    @Test
    public void createNewArtistTest() throws Exception {
       
        ArtistServiceImpl as = new ArtistServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        ArtistDao ad = mock(ArtistDao.class);
        Artist artist = new Artist();
        artist.setName("Lou Reed");
        when(ad.getArtist("Lou Reed")).thenReturn(artist);
        as.setArtistDao(ad);
        
        ArtistTO ato = new ArtistTO();
        ato.setName("Lou Reed");
        ArtistTO a = as.createNewArtist(ato);
        assertEquals(a.getName(), "Lou Reed");
        
        ArtistTO a2 = as.getArtist("Lou Reed");
        assertEquals(a, a2);
    }
    
    @Test
    public void updateArtistTest() throws Exception {
       
        ArtistServiceImpl as = new ArtistServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        ArtistDao ad = mock(ArtistDao.class);
        Artist artist = new Artist();
        artist.setName("Lou Reed");
        when(ad.getArtist("Lou Reed")).thenReturn(artist);
        as.setArtistDao(ad);
        
        ArtistTO ato = new ArtistTO();
        ato.setName("Lou Reed");
        ArtistTO a = as.createNewArtist(ato);
        
        ArtistTO a2 = as.getArtist("Lou Reed");
        a2.setName("Velvet Underground");
        
        ArtistTO updated = as.updateArtist(a, a2);
        assertEquals(a2, updated);
    }
    
    @Test
    public void removeArtistByNameTest() throws Exception {
       
        ArtistServiceImpl as = new ArtistServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        ArtistDao ad = mock(ArtistDao.class);
        Artist artist = new Artist();
        artist.setName("Lou Reed");
        when(ad.getArtist("Lou Reed")).thenReturn(artist);
        as.setArtistDao(ad);
        
        ArtistTO ato = new ArtistTO();
        ato.setName("Lou Reed");
        ArtistTO a = as.createNewArtist(ato);
        
        boolean removed = as.removeArtist("Lou Reed");
        assertTrue(removed);
    }
    
    @Test
    public void removeArtistByTOTest() throws Exception {
       
        ArtistServiceImpl as = new ArtistServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        ArtistDao ad = mock(ArtistDao.class);
        Artist artist = new Artist();
        artist.setName("Lou Reed");
        when(ad.getArtist("Lou Reed")).thenReturn(artist);
        when(ad.getArtist("James Bond")).thenReturn(null);
        as.setArtistDao(ad);
        
        ArtistTO ato = new ArtistTO();
        ato.setName("Lou Reed");
        ArtistTO a = as.createNewArtist(ato);
        
        ArtistTO a2 = as.getArtist("Lou Reed");
        boolean removed = as.removeArtist(a2);
        assertTrue(removed);
    }
    
    @Test
    public void getArtistTest() throws Exception {
       
        ArtistServiceImpl as = new ArtistServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        ArtistDao ad = mock(ArtistDao.class);
        Artist artist = new Artist();
        artist.setName("Lou Reed");
        when(ad.getArtist("Lou Reed")).thenReturn(artist);
        as.setArtistDao(ad);
        
        ArtistTO ato = new ArtistTO();
        ato.setName("Lou Reed");
        ArtistTO a = as.createNewArtist(ato);
        
        ArtistTO a2 = as.getArtist("Lou Reed");
        assertEquals(a, a2);
    }
    
    @Test
    public void getAllArtistsTest() throws Exception {
        ArtistServiceImpl as = new ArtistServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        ArtistDao ad = mock(ArtistDao.class);
        
        when(ad.getAll()).thenReturn(new ArrayList<Artist>(){{
            Artist a1 = new Artist();
            a1.setName("Billy Preston");
            add(a1);
            Artist a2 = new Artist();
            a2.setName("Leon Russell");
            add(a2);
        }});
        
        as.setArtistDao(ad);
        List<ArtistTO> all = as.getAllArtists();
        assertEquals(2, all.size());
        assertEquals("Billy Preston", all.get(0).getName());
        assertEquals("Leon Russell", all.get(1).getName());
    }
}
