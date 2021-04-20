package com.pa165.mlib.dto;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Artist Transfer Object
 *
 * @author Ragu, ibek
 */
@XmlRootElement
public class ArtistTO {

    private Long id;
    
    private String name;

    public ArtistTO() {
    }

    public ArtistTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final ArtistTO other = (ArtistTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Artist{ id=" + id + 
               ", name= " + name + " }";
    }
    
}
