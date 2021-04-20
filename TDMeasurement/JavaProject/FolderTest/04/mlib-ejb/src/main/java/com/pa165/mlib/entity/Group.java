package com.pa165.mlib.entity;

import com.pa165.mlib.dto.Role;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author ibek
 */
@Entity(name = "USERS_GROUPS")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "roleid")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Id
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    public Group() {
        
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Group other = (Group) obj;
        if (this.role != other.role) {
            return false;
        }
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.role != null ? this.role.hashCode() : 0);
        hash = 89 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Group{name=" + role + "}";
    }
}
