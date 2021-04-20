package com.pa165.mlib.service;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;

/**
 *
 * @author xbek
 */
public interface GenreService {
    
    GenreTO createNewGenre(GenreTO genre) throws DuplicateException ;
    
    GenreTO getGenre(String name);
    
    GenreTO updateGenre(GenreTO oldGenre, GenreTO newGenre);
    
    boolean removeGenre(GenreTO genre);
    
    List<GenreTO> getAllGenres();
    
}
