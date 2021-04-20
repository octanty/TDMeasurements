package com.pa165.mlib.dto;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Genre Transfer Object
 * 
 * @author xbek
 */
@XmlRootElement
public class GenreTO {
    
    private String name;
    
    public GenreTO() {
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
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
        final GenreTO other = (GenreTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Genre{ name=" + name + "}";
    }
    
}
