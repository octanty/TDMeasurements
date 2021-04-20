package com.muni.fi.pa165.dto;

import com.muni.fi.pa165.enums.UserGroup;
import com.muni.fi.pa165.enums.UserStatus;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *Data transfer object for the {@link Systemuser}.
 * 
 * @author irina
 */
@XmlRootElement
public class SystemUserDto {
    private Long id;
    private String username;
    private String password;
    private UserStatus enabled;
    private UserGroup authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getEnabled() {
        return enabled;
    }

    public void setEnabled(UserStatus enabled) {
        this.enabled = enabled;
    }

    public UserGroup getAuthority() {
        return authority;
    }

    public void setAuthority(UserGroup authority) {
        this.authority = authority;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final SystemUserDto other = (SystemUserDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }


   
    
    
}
