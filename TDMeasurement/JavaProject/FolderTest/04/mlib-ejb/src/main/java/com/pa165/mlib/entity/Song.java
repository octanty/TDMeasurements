package com.pa165.mlib.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ibek
 */
@Entity
public class Song implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String title;
    
    private Integer bitrate;
    
    private Integer position;
    
    private String commentary;
    
    @ManyToOne
    private Genre genre;
    
    @ManyToOne
    private Album album;
    
    @ManyToOne
    private Artist artist;

    @Override
    public int hashCode() {
        if (id == null) {
            return -1;
        }
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Song other = (Song) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the bitrate
     */
    public Integer getBitrate() {
        return bitrate;
    }

    /**
     * @param bitrate the bitrate to set
     */
    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    /**
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * @return the commentary
     */
    public String getCommentary() {
        return commentary;
    }

    /**
     * @param commentary the commentary to set
     */
    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    /**
     * @return the genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * @return the album
     */
    public Album getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(Album album) {
        this.album = album;
    }

    /**
     * @return the artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Song{ id=" + id + 
                ", title=" + title + 
                ", bitrate=" + bitrate + 
                ", position=" + position + 
                ", commentary=" + commentary + "}";
    }
    
}
