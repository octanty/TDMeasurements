package com.pa165.mlib.manager;

import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.SongTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.AlbumService;
import com.pa165.mlib.service.ArtistService;
import com.pa165.mlib.service.GenreService;
import com.pa165.mlib.service.SongService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ibek
 */
@SessionScoped
@ManagedBean
@Named
public class SongManager implements Serializable {
    
    @Inject
    transient Logger logger;
    
    @Inject
    SongService songService;
    
    @Inject
    ArtistService artistService;
    
    @Inject
    AlbumService albumService;
    
    @Inject
    GenreService genreService;
    
    private SongTO songTO;
    
    private String genre = "";
    
    private String artist = "";
    
    private String album = "";
    
    public SongTO initNew(String album) {
        songTO = new SongTO();
        if (album != null) {
            this.album = album;
        }
        return songTO;
    }
    
    public SongTO init(String title) {
        songTO = songService.getSong(title);
        genre = songTO.getGenre().getName();
        setArtist(songTO.getArtist().getName());
        setAlbum(songTO.getAlbum().getTitle());
        return songTO;
    }

    public SongService getSongService() {
        return songService;
    }

    public ArtistService getArtistService() {
        return artistService;
    }

    public AlbumService getAlbumService() {
        return albumService;
    }

    public GenreService getGenreService() {
        return genreService;
    }
    
    public SongTO getSongTO() {
        return songTO;
    }
    
    public String create() {
        convert();
        logger.log(Level.INFO, "Creating {0}", songTO);
        try {
            songService.createNewSong(songTO);
            initNew("");
        } catch (DuplicateException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The song cannot be created because a song with this title already exists."));
            return "/song_detail";
        }
        return "/songs";
    }
    
    public String updateSong() {
        convert();
        logger.log(Level.INFO, "Updating {0}", songTO);
        songService.updateSong(songService.getSong(songTO.getTitle()), songTO);
        //init();
        return "/songs";
    }
    
    public String removeSong() {
        convert();
        logger.log(Level.INFO, "Removing {0}", songTO);
        songService.removeSong(songTO);
        //init();
        return "/songs";
    }
    
    public String removeSong(SongTO song, String returnPath) {
        logger.log(Level.INFO, "Removing {0}", song);
        songService.removeSong(song);
        return returnPath;
    }
    
    private void convert() {
        songTO.setGenre(genreService.getGenre(genre));
        songTO.setArtist(artistService.getArtist(artist));
        songTO.setAlbum(albumService.getAlbum(album));
    }
    
    public List<SongTO> getSongsInAlbum(AlbumTO album) {
        return songService.getSongsInAlbum(album);
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(String album) {
        this.album = album;
    }
    
}
