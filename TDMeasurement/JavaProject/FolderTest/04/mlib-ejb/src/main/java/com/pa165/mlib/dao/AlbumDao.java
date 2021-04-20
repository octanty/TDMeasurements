package com.pa165.mlib.dao;

import com.pa165.mlib.entity.Album;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;

/**
 *
 * @author xbek
 */
public interface AlbumDao {

    /**
     * Persist the given album to persistence context.
     * @param album to be persisted
     */
    void addAlbum(Album album) throws DuplicateException;

    /**
     * Get album with given unique identifier.
     * @param id unique identifier for album
     * @return album
     */
    Album getAlbum(long id);

    /**
     * Get the album with a given title.
     * @param title of the album in search
     * @return album with defined title
     */
    Album getAlbum(String title);

    /**
     * Get all the albums with given artist.
     * @param artist who is author of the required albums
     * @return albums with defined artist
     */
    List<Album> getAlbumsWithArtist(Artist artist);

    /**
     * Get all the albums.
     * @return all the albums
     */
    List<Album> getAll();

    /**
     * Remove album from the persistence context and database after the commit.
     * @param album to be removed
     */
    void removeAlbum(Album album);

    /**
     * Update the given album in persistence context and database after the commit.
     * @param album to be updated
     * @return updated album
     */
    Album updateAlbum(Album album);
    
}
