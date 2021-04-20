package com.pa165.mlib.dto;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User Transfer Object
 * 
 * @author ibek
 */
@XmlRootElement
public class UserTO {
    
    private String username;
    private String password;
    
    public UserTO() {
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.getUsername());
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
        final UserTO other = (UserTO) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{ username=" + getUsername() + ","
                + " password=" + getPassword() + "}";
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
