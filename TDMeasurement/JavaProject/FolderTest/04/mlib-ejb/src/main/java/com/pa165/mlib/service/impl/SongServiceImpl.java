package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.dao.SongDao;
import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.entity.Song;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.SongService;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

/**
 * Implementation of SongService
 * 
 * @author tomparys
 */
@MlibService
@Stateless
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN", "USER"})
public class SongServiceImpl implements SongService {
    
    @Inject
    SongDao songDao;
    
    @Inject
    GenreDao genreDao;
    
    @Inject
    AlbumDao albumDao;
    
    @Inject
    ArtistDao artistDao;
    
    @Inject
    EntityDTOTransformer transformer;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SongTO createNewSong(SongTO song) throws DuplicateException {
        Song s = new Song();
        s.setTitle(song.getTitle());
        s.setBitrate(song.getBitrate());
        s.setPosition(song.getPosition());
        s.setCommentary(song.getCommentary());
        if (song.getGenre() != null) {
            s.setGenre(genreDao.getGenre(song.getGenre().getName()));
        }
        if (song.getAlbum() != null) {
            s.setAlbum(albumDao.getAlbum(song.getAlbum().getTitle()));
        }
        if (song.getArtist() != null) {
            s.setArtist(artistDao.getArtist(song.getArtist().getName()));
        }
        songDao.addSong(s);
        return transformer.transformSongTO(s);
    }

    @Override
    @PermitAll
    public List<SongTO> getAllSongs() {
        List<SongTO> songs = new ArrayList<>();
        
        for (Song s : songDao.getAll()) {
            songs.add(transformer.transformSongTO(s));
        }
        return songs;
    }

    @Override
    @PermitAll
    public SongTO getSong(String title) {
        return transformer.transformSongTO(songDao.getSong(title));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SongTO updateSong(SongTO oldSong, SongTO newSong) {
        Song s = songDao.getSong(oldSong.getTitle());
        s.setTitle(newSong.getTitle());
        s.setBitrate(newSong.getBitrate());
        s.setPosition(newSong.getPosition());
        s.setCommentary(newSong.getCommentary());
        if (newSong.getGenre() != null) {
            s.setGenre(genreDao.getGenre(newSong.getGenre().getName()));
        }
        if (newSong.getAlbum() != null) {
            s.setAlbum(albumDao.getAlbum(newSong.getAlbum().getTitle()));
        }
        if (newSong.getArtist() != null) {
            s.setArtist(artistDao.getArtist(newSong.getArtist().getName()));
        }
        songDao.updateSong(s);
        return newSong;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeSong(String title) {
        Song song = songDao.getSong(title);
        if (song == null) {
            return false;
        } else {
            songDao.removeSong(song);
            return true;
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeSong(SongTO song) {
        return removeSong(song.getTitle());
    }
    

    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }

    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    public void setArtistDao(ArtistDao artistDao) {
        this.artistDao = artistDao;
    }
        
    public void setTransformer(EntityDTOTransformer transformer) {
        this.transformer = transformer;
    }

    @Override
    @PermitAll
    public List<SongTO> getSongsInAlbum(AlbumTO album) {
        Album a = albumDao.getAlbum(album.getTitle());
        List<SongTO> songs = new ArrayList<>();
        if (a == null) {
            return songs;
        }
        
        for (Song s : songDao.getSongsInAlbum(a.getId())) {
            songs.add(transformer.transformSongTO(s));
        }
        return songs;
    }
    
}
