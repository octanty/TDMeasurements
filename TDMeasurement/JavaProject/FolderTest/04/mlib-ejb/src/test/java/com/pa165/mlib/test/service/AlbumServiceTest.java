package com.pa165.mlib.test.service;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.dto.AlbumTO;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.pa165.mlib.entity.Album;
import com.pa165.mlib.service.impl.AlbumServiceImpl;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class AlbumServiceTest {
    
    @Test
    public void createNewAlbumTest() throws Exception {
        AlbumServiceImpl as = new AlbumServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        AlbumDao ad = mock(AlbumDao.class);
        Album album = new Album();
        album.setTitle("bestOf");
        album.setReleased(2013);
        when(ad.getAlbum("bestOf")).thenReturn(album);
        
        as.setAlbumDao(ad);
        AlbumTO ato = new AlbumTO();
        ato.setTitle("bestOf");
        ato.setReleased(2013);
        
        AlbumTO bestOf = as.createNewAlbum(ato);
        assertEquals("bestOf", bestOf.getTitle());
        assertEquals(2013, (int) bestOf.getReleased());
    }
    
    @Test
    public void getAlbumTest() throws Exception {
        AlbumServiceImpl as = new AlbumServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        AlbumDao ad = mock(AlbumDao.class);
        Album album = new Album();
        album.setTitle("bestOf");
        album.setReleased(2013);
        when(ad.getAlbum("bestOf")).thenReturn(album);
        
        as.setAlbumDao(ad);
        AlbumTO ato = new AlbumTO();
        ato.setTitle("bestOf");
        ato.setReleased(2013);
        
        AlbumTO bestOf = as.createNewAlbum(ato);
        AlbumTO bestOf2 = as.getAlbum("bestOf");
        assertEquals(bestOf, bestOf2);
    }
    
    @Test
    public void updateAlbumTest() throws Exception {
        AlbumServiceImpl as = new AlbumServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        AlbumDao ad = mock(AlbumDao.class);
        Album album = new Album();
        album.setTitle("bestOf");
        album.setReleased(2013);
        when(ad.getAlbum("bestOf")).thenReturn(album);
        
        as.setAlbumDao(ad);
        AlbumTO ato = new AlbumTO();
        ato.setTitle("bestOf");
        ato.setReleased(2013);
        
        AlbumTO bestOf = as.createNewAlbum(ato);
        AlbumTO bestOf2 = as.getAlbum("bestOf");
        bestOf2.setReleased(2012);
        bestOf2 = as.updateAlbum(bestOf, bestOf2);
        assertEquals(2012, (int) bestOf2.getReleased());
    }
    
    @Test
    public void removeAlbumByTitleTest() throws Exception {
        AlbumServiceImpl as = new AlbumServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        AlbumDao ad = mock(AlbumDao.class);
        Album album = new Album();
        album.setTitle("bestOf");
        album.setReleased(2013);
        when(ad.getAlbum("bestOf")).thenReturn(album);
        
        as.setAlbumDao(ad);
        AlbumTO ato = new AlbumTO();
        ato.setTitle("bestOf");
        ato.setReleased(2013);
        
        as.createNewAlbum(ato);
        boolean removed = as.removeAlbum("bestOf");
        assertTrue(removed);
    }
    
    @Test
    public void removeAlbumByTOTest() throws Exception {
        AlbumServiceImpl as = new AlbumServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        
        AlbumDao ad = mock(AlbumDao.class);
        Album album = new Album();
        album.setTitle("bestOf");
        album.setReleased(2013);
        when(ad.getAlbum("bestOf")).thenReturn(album);
        
        as.setAlbumDao(ad);
        AlbumTO ato = new AlbumTO();
        ato.setTitle("bestOf");
        ato.setReleased(2013);
        
        AlbumTO bestOf = as.createNewAlbum(ato);
        AlbumTO bestOf2 = as.getAlbum("bestOf");
        boolean removed = as.removeAlbum(bestOf2);
        assertTrue(removed);
    }
    
    @Test
    public void getAllAlbumsTest() throws Exception {
        AlbumServiceImpl as = new AlbumServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        as.setTransformer(transformer);
        AlbumDao ad = mock(AlbumDao.class);
        
        when(ad.getAll()).thenReturn(new ArrayList<Album>(){{
            Album a1 = new Album();
            a1.setTitle("a1");
            a1.setReleased(2012);
            add(a1);
            Album a2 = new Album();
            a2.setTitle("a2");
            a2.setReleased(2013);
            add(a2);
        }});
        
        as.setAlbumDao(ad);
        List<AlbumTO> all = as.getAllAlbums();
        assertEquals(2, all.size());
        assertEquals("a1", all.get(0).getTitle());
        assertEquals("a2", all.get(1).getTitle());
    }
}
