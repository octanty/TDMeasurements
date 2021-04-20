package com.pa165.mlib.service;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;

/**
 * Song service
 * 
 * @author brazdil
 */
public interface SongService {
    
    List<SongTO> getAllSongs();
    
    List<SongTO> getSongsInAlbum(AlbumTO album);
    
    SongTO createNewSong(SongTO song) throws DuplicateException;
    
    SongTO getSong(String title);
    
    SongTO updateSong(SongTO oldSong, SongTO newSong);
    
    boolean removeSong(String title);
    
    boolean removeSong(SongTO song);
    
}
