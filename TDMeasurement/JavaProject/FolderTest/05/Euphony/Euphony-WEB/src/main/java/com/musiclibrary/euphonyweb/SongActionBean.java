package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Sebastian
 */
@AdminOnly
@UrlBinding("/songs/{$event}/{song.id}")
public class SongActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected MusicFacade musicFacade;

    private List<PlaylistDTO> playlists;
    private PlaylistDTO playlist;
    private List<SongDTO> songs;
    private List<AlbumDTO> albums;
    private List<GenreDTO> genres;
    private List<ArtistDTO> artists;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "title", required = true),
        @Validate(on = {"add", "save"}, field = "bitrate", required = true, minvalue = 1, maxvalue = 2500),
        @Validate(on = {"add", "save"}, field = "trackNumber", required = true, minvalue = 1)
    })
    private SongDTO song;
    @Validate(on = {"add", "save"}, required = true)
    private Long album;
    @Validate(on = {"add", "save"}, required = true)
    private Long genre;
    @Validate(on = {"add", "save"}, required = true)
    private Long artist;

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public PlaylistDTO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistDTO playlist) {
        this.playlist = playlist;
    }

    @DefaultHandler
    public Resolution list() {
        songs = musicFacade.getAllSongs();
        albums = musicFacade.getAllAlbums();
        genres = musicFacade.getAllGenres();
        artists = musicFacade.getAllArtists();
        HttpSession session = getContext().getRequest().getSession();        
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return new ForwardResolution("/song/list.jsp");
    }

    public List<SongDTO> getSongs() {
        return songs;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public List<AlbumDTO> getAlbums() {
        return albums;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setAlbum(long album) {
        this.album = album;
    }

    public void setArtist(long artist) {
        this.artist = artist;
    }

    public void setGenre(long genre) {
        this.genre = genre;
    }

    public Resolution add() {
        song.setAlbum(musicFacade.getAlbumById(album));
        song.setArtist(musicFacade.getArtistById(artist));
        song.setGenre(musicFacade.getGenreById(genre));

        musicFacade.create(song);
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        songs = musicFacade.getAllSongs();
        albums = musicFacade.getAllAlbums();
        genres = musicFacade.getAllGenres();
        artists = musicFacade.getAllArtists();
        HttpSession session = getContext().getRequest().getSession();        
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return null;
    }

    public SongDTO getSong() {
        return song;
    }

    public void setSong(SongDTO song) {
        this.song = song;
    }

    public Resolution delete() {
        song = musicFacade.getSongById(song.getId());
        try {
            musicFacade.delete(song);
        } catch (DataAccessException ex) {
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "details"})
    public void loadSongFromDatabase() {
        String ids = getContext().getRequest().getParameter("song.id");
        if (ids == null) {
            return;
        }
        song = musicFacade.getSongById(Long.parseLong(ids));
    }

    public Resolution edit() {
        albums = musicFacade.getAllAlbums();
        genres = musicFacade.getAllGenres();
        artists = musicFacade.getAllArtists();
        HttpSession session = getContext().getRequest().getSession();        
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return new ForwardResolution("/song/edit.jsp");
    }

    public Resolution save() {
        song.setAlbum(musicFacade.getAlbumById(album));
        song.setArtist(musicFacade.getArtistById(artist));
        song.setGenre(musicFacade.getGenreById(genre));
        musicFacade.update(song);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution cancel() {
        return new RedirectResolution(this.getClass(), "list");
    }
}
