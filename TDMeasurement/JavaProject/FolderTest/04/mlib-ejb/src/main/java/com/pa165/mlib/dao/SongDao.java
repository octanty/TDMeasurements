package com.pa165.mlib.dao;

import com.pa165.mlib.entity.Song;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;

/**
 *
 * @author xbek
 */
public interface SongDao {

    /**
     * Persist the given song to persistence context.
     * @param song to be persisted
     */
    void addSong(Song song) throws DuplicateException;

    /**
     * Get all the songs.
     * @return all the songs
     */
    List<Song> getAll();

    /**
     * Get all the songs from album.
     * @return the songs from album
     */
    List<Song> getSongsInAlbum(long album_id);

    /**
     * Get song with given unique identifier.
     * @param id unique identifier for song
     * @return song
     */
    Song getSong(long id);

    /**
     * Get a song with the given title.
     * @param title what should be song called
     * @return song with defined title
     */
    Song getSong(String title);

    /**
     * Remove song from the persistence context and database after the commit.
     * @param song to be removed
     */
    void removeSong(Song song);

    /**
     * Update the given song in persistence context and database after the commit.
     * @param song to be updated
     * @return updated song
     */
    Song updateSong(Song song);
    
}
