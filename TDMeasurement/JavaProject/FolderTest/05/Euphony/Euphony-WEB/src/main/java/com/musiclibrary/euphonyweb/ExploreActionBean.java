package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.AccountDTO;
import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 * Explore action bean for showing songs, albums and artists in index.
 *
 * @author Tomas Smetanka #396209
 */
@UrlBinding("/explore/{$event}/{mainId}")
public class ExploreActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected MusicFacade musicFacade;

    private GenreDTO genre;
    private SongDTO song;
    private AlbumDTO album;
    private ArtistDTO artist;
    private AccountDTO account;
    private PlaylistDTO playlist;
    private List<GenreDTO> genres;
    private List<SongDTO> songs;
    private List<SongDTO> songsInAlbum;
    private List<SongDTO> songsInArtist;
    private List<AlbumDTO> albums;
    private List<ArtistDTO> artists;
    private List<PlaylistDTO> playlists;

    @DefaultHandler
    public Resolution songs() {
        songs = musicFacade.getAllSongs();
        HttpSession session = getContext().getRequest().getSession();
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return new ForwardResolution("/explore/songs.jsp");
    }

    public Resolution albums() {
        albums = musicFacade.getAllAlbums();
        HttpSession session = getContext().getRequest().getSession();
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return new ForwardResolution("/explore/albums.jsp");
    }

    public Resolution artists() {
        artists = musicFacade.getAllArtists();
        HttpSession session = getContext().getRequest().getSession();
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return new ForwardResolution("/explore/artists.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        songs = musicFacade.getAllSongs();
        return null;
    }

    public Resolution showAlbum() {
        String ids = getContext().getRequest().getParameter("mainId");
        if (ids == null) {
            return new ForwardResolution("/explore");
        }

        HttpSession session = getContext().getRequest().getSession();
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        album = musicFacade.getAlbumById(Long.parseLong(ids));
        songsInAlbum = musicFacade.getSongsByAlbum(album);
        return new ForwardResolution("/explore/album.jsp");
    }

    public Resolution showArtist() {
        String ids = getContext().getRequest().getParameter("mainId");
        if (ids == null) {
            return new ForwardResolution("/explore");
        }

        HttpSession session = getContext().getRequest().getSession();
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        artist = musicFacade.getArtistById(Long.parseLong(ids));
        songsInArtist = musicFacade.getSongsByArtist(artist);

        return new ForwardResolution("/explore/artist.jsp");
    }

    public Resolution showSong() {
        String ids = getContext().getRequest().getParameter("mainId");
        if (ids == null) {
            return new ForwardResolution("/explore");
        }

        HttpSession session = getContext().getRequest().getSession();
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        song = musicFacade.getSongById(Long.parseLong(ids));

        return new ForwardResolution("/explore/song.jsp");
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

    public List<SongDTO> getSongs() {
        return songs;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    public SongDTO getSong() {
        return song;
    }

    public void setSong(SongDTO song) {
        this.song = song;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDTO album) {
        this.album = album;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtis(ArtistDTO artist) {
        this.artist = artist;
    }

    public PlaylistDTO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistDTO playlist) {
        this.playlist = playlist;
    }

    public List<SongDTO> getSongsInAlbum() {
        return songsInAlbum;
    }

    public void setSongsInAlbum(List<SongDTO> songsInAlbum) {
        this.songsInAlbum = songsInAlbum;
    }

    public List<SongDTO> getSongsInArtist() {
        return songsInArtist;
    }

    public void setSongsInArtist(List<SongDTO> songsInArtist) {
        this.songsInArtist = songsInArtist;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}
