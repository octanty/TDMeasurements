package com.pa165.mlib.service;

import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;

/**
 * Artist Service
 * 
 * @author ibek
 */
public interface ArtistService {
    
    ArtistTO createNewArtist(ArtistTO artist) throws DuplicateException;
       
    ArtistTO getArtist(String name);
    
    ArtistTO updateArtist(ArtistTO oldArtist, ArtistTO newArtist);
            
    boolean removeArtist(String name);
    
    boolean removeArtist(ArtistTO artist);
    
    List<ArtistTO> getAllArtists();
    
    
}
