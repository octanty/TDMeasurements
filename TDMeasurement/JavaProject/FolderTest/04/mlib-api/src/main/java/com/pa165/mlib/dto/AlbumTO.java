package com.pa165.mlib.dto;

import java.util.List;
import java.util.Objects;

/**
 * Album Transfer Object
 * 
 * @author tomparys
 */
public class AlbumTO {
    
    private String title;
    
    private Integer released;
    
    private List<SongTO> songs;
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getReleased() {
        return released;
    }

    public void setReleased(Integer released) {
        this.released = released;
    }

    public List<SongTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongTO> songs) {
        this.songs = songs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.title);
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
        final AlbumTO other = (AlbumTO) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Album{ title= " + title + 
               ", released= " + released + "}";
    }

}
