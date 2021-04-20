package com.pa165.mlib.service;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;

/**
 * Album Service
 * 
 * @author brazdil
 */
public interface AlbumService {
    
    List<AlbumTO> getAllAlbums();
    
    List<AlbumTO> getAlbumsWithArtist(ArtistTO artist);
    
    AlbumTO createNewAlbum(AlbumTO album) throws DuplicateException;
    
    AlbumTO getAlbum(String title);
    
    AlbumTO updateAlbum(AlbumTO oldAlbum, AlbumTO newAlbum);
    
    boolean removeAlbum(String title);
    
    boolean removeAlbum(AlbumTO album);
    
}
