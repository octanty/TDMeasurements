package com.muni.fi.pa165.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Auron
 */
@Embeddable
public class MonsterWeaponPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MONSTERID")
    private long monsterid;
    @Basic(optional = false)
    @Column(name = "WEAPONID")
    private long weaponid;

    /**
     *
     */
    public MonsterWeaponPK() {
    }

    /**
     *
     * @param monsterid
     * @param weaponid
     */
    public MonsterWeaponPK(long monsterid, long weaponid) {
        this.monsterid = monsterid;
        this.weaponid = weaponid;
    }

    /**
     *
     * @return
     */
    public long getMonsterid() {
        return monsterid;
    }

    /**
     *
     * @param monsterid
     */
    public void setMonsterid(long monsterid) {
        this.monsterid = monsterid;
    }

    /**
     *
     * @return
     */
    public long getWeaponid() {
        return weaponid;
    }

    /**
     *
     * @param weaponid
     */
    public void setWeaponid(long weaponid) {
        this.weaponid = weaponid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) monsterid;
        hash += (int) weaponid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterWeaponPK)) {
            return false;
        }
        MonsterWeaponPK other = (MonsterWeaponPK) object;
        if (this.monsterid != other.monsterid) {
            return false;
        }
        if (this.weaponid != other.weaponid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.muni.fi.pa165.entities.MonsterweaponPK[ monsterid=" + monsterid + ", weaponid=" + weaponid + " ]";
    }
}
