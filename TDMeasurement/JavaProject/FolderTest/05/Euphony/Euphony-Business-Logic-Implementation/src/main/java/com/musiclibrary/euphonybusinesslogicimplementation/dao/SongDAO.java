package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public interface SongDAO {

    void create(Song entity);

    void update(Song entity);

    void delete(Song entity);

    Song getById(Long id);

    List<Song> getAll();

    List<Song> getByTitle(String title);

    List<Song> getByGenre(Genre genre);

    List<Song> getByArtist(Artist artist);

    List<Song> getByAlbum(Album album);
}
