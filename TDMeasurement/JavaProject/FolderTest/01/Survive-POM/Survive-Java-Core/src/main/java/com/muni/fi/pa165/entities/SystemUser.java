/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165.entities;

import com.muni.fi.pa165.enums.UserGroup;
import com.muni.fi.pa165.enums.UserStatus;
import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Auron
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SystemUser.findAll", query = "SELECT u FROM SystemUser u"),
    @NamedQuery(name = "SystemUser.findByName", query = "SELECT u FROM SystemUser u WHERE u.username = :username"),
    @NamedQuery(name = "SystemUser.findByPassword", query = "SELECT u FROM SystemUser u WHERE u.password = :password"),
    @NamedQuery(name = "SystemUser.findByEnabled", query = "SELECT u FROM SystemUser u WHERE u.enabled = :enabled")})
public class SystemUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic(optional = false)
    @Column(name = "ENABLED")
    @Enumerated(EnumType.ORDINAL)
    private UserStatus enabled;
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "AUTHORITY")
    @Enumerated(EnumType.STRING)
    private UserGroup authority;

    public SystemUser() {
    }

    public SystemUser(String username) {
        this.username = username;
    }

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

    public UserGroup getAuthority() {
        return authority;
    }

    public void setAuthority(UserGroup authority) {
        this.authority = authority;
    }

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemUser)) {
            return false;
        }
        SystemUser other = (SystemUser) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.muni.fi.pa165.entities.SystemUser[ username=" + username + " ]";
    }

    public UserStatus getEnabled() {
        return enabled;
    }

    public void setEnabled(UserStatus enabled) {
        this.enabled = enabled;
    }
}
