package com.pa165.mlib.test.service;

import com.pa165.mlib.dao.SongDao;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.entity.Song;
import com.pa165.mlib.service.impl.SongServiceImpl;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class SongServiceTest {
    
    private static final String title = "Title";
    private static final String newTitle = "Remastered";
    private static final Integer bitrate = 256;
    private static final Integer position = 3;
    private static final String commentary = "Social Commentary";
        
    @Test
    public void createNewSongTest() throws Exception {
        SongServiceImpl songService = new SongServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        songService.setTransformer(transformer);
        SongDao songDao = mock(SongDao.class);
        songService.setSongDao(songDao);
        
        SongTO testSong = new SongTO();
        testSong.setTitle(title);
        testSong.setBitrate(bitrate);
        testSong.setPosition(position);
        testSong.setCommentary(commentary);
        Song s = new Song();
        s.setTitle(title);
        s.setBitrate(bitrate);
        s.setPosition(position);
        s.setCommentary(commentary);
        when(songDao.getSong(title)).thenReturn(s);       
        
        SongTO proTestSong = songService.createNewSong(testSong);
        assertEquals(title, proTestSong.getTitle());
        assertEquals(bitrate, proTestSong.getBitrate());
        assertEquals(position, proTestSong.getPosition());
        assertEquals(commentary, proTestSong.getCommentary());
    }
    
    @Test
    public void getSongTest() throws Exception {
        SongServiceImpl songService = new SongServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        songService.setTransformer(transformer);
        SongDao songDao = mock(SongDao.class);
        songService.setSongDao(songDao);
        
        SongTO testSong = new SongTO();
        testSong.setTitle(title);
        testSong.setBitrate(bitrate);
        testSong.setPosition(position);
        testSong.setCommentary(commentary);
        Song s = new Song();
        s.setTitle(title);
        s.setBitrate(bitrate);
        s.setPosition(position);
        s.setCommentary(commentary);
        when(songDao.getSong(title)).thenReturn(s);       
        
        SongTO proTestSong = songService.createNewSong(testSong);
        SongTO protiTestSong = songService.getSong(title);
        assertEquals(proTestSong, protiTestSong);
    }
    
    @Test
    public void updateSongTest() throws Exception {
        SongServiceImpl songService = new SongServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        songService.setTransformer(transformer);
        SongDao songDao = mock(SongDao.class);
        songService.setSongDao(songDao);
        
        SongTO testSong = new SongTO();
        testSong.setTitle(title);
        testSong.setBitrate(bitrate);
        testSong.setPosition(position);
        testSong.setCommentary(commentary);
        Song s = new Song();
        s.setTitle(title);
        s.setBitrate(bitrate);
        s.setPosition(position);
        s.setCommentary(commentary);
        when(songDao.getSong(title)).thenReturn(s);       
        
        SongTO proTestSong = songService.createNewSong(testSong);
        SongTO protiTestSong = songService.getSong(title);
        
        protiTestSong.setTitle(newTitle);
        protiTestSong = songService.updateSong(proTestSong, protiTestSong);
        assertEquals(newTitle, protiTestSong.getTitle());
    }
    
    @Test
    public void removeSongByTitleTest() throws Exception {
        SongServiceImpl songService = new SongServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        songService.setTransformer(transformer);
        SongDao songDao = mock(SongDao.class);
        songService.setSongDao(songDao);
        
        SongTO testSong = new SongTO();
        testSong.setTitle(title);
        testSong.setBitrate(bitrate);
        testSong.setPosition(position);
        testSong.setCommentary(commentary);
        Song s = new Song();
        s.setTitle(title);
        s.setBitrate(bitrate);
        s.setPosition(position);
        s.setCommentary(commentary);
        when(songDao.getSong(title)).thenReturn(s);       
        
        SongTO proTestSong = songService.createNewSong(testSong);
        SongTO protiTestSong = songService.getSong(title);
        boolean removed = songService.removeSong(title);
        assertTrue(removed);
    }
    
    @Test
    public void removeSongByTOTest() throws Exception {
        SongServiceImpl songService = new SongServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        songService.setTransformer(transformer);
        SongDao songDao = mock(SongDao.class);
        songService.setSongDao(songDao);
        
        SongTO testSong = new SongTO();
        testSong.setTitle(title);
        testSong.setBitrate(bitrate);
        testSong.setPosition(position);
        testSong.setCommentary(commentary);
        Song s = new Song();
        s.setTitle(title);
        s.setBitrate(bitrate);
        s.setPosition(position);
        s.setCommentary(commentary);
        when(songDao.getSong(title)).thenReturn(s);       
        
        SongTO proTestSong = songService.createNewSong(testSong);
        SongTO protiTestSong = songService.getSong(title);
        boolean removed = songService.removeSong(proTestSong);
        assertTrue(removed);
    }
    
    @Test
    public void getAllSongsTest() throws Exception {
        SongServiceImpl songService = new SongServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        songService.setTransformer(transformer);
        SongDao songDao = mock(SongDao.class);
        songService.setSongDao(songDao);
        
        when(songDao.getAll()).thenReturn(new ArrayList<Song>(){{
            Song s1 = new Song();
            s1.setTitle("Song 1");
            add(s1);
            Song s2 = new Song();
            s2.setTitle("Song 2");
            add(s2);
        }});
        
        List<SongTO> all = songService.getAllSongs();
        assertEquals(2, all.size());
        assertEquals("Song 1", all.get(0).getTitle());
        assertEquals("Song 2", all.get(1).getTitle());
        /**/
    }
}
