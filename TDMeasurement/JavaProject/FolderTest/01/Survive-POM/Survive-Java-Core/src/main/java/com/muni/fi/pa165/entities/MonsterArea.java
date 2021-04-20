package com.muni.fi.pa165.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Auron
 */
@Entity
@Table(name = "MONSTERAREA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsterArea.findAll", query = "SELECT m FROM MonsterArea m"),
    @NamedQuery(name = "MonsterArea.findByMonsterquantity", query = "SELECT m FROM MonsterArea m WHERE m.monsterquantity = :monsterquantity"),
    @NamedQuery(name = "MonsterArea.findByMonsterId", query = "SELECT m FROM MonsterArea m WHERE m.monsterareaPK.monsterid = :monsterid"),
    @NamedQuery(name = "MonsterArea.findById", query = "SELECT m FROM MonsterArea m WHERE m.monsterareaPK = :monsterareaPK"),
    @NamedQuery(name = "MonsterArea.findByAreaId", query = "SELECT m FROM MonsterArea m WHERE m.monsterareaPK.areaid = :areaid")
})
public class MonsterArea implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @EmbeddedId
    protected MonsterAreaPK monsterareaPK;
    @Basic(optional = false)
    @Column(name = "MONSTERQUANTITY")
    private int monsterquantity;
    @JoinColumn(name = "MONSTERID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Monster monster;
    @JoinColumn(name = "AREAID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Area area;

    /**
     *
     */
    public MonsterArea() {
    }

    /**
     *
     * @param monsterareaPK
     */
    public MonsterArea(MonsterAreaPK monsterareaPK) {
        this.monsterareaPK = monsterareaPK;
    }

    /**
     *
     * @param monsterareaPK
     * @param monsterquantity
     */
    public MonsterArea(MonsterAreaPK monsterareaPK, int monsterquantity) {
        this.monsterareaPK = monsterareaPK;
        this.monsterquantity = monsterquantity;
    }

    /**
     *
     * @param monsterId
     * @param areaId
     */
    public MonsterArea(long monsterId, long areaId) {
        this.monsterareaPK = new MonsterAreaPK(monsterId, areaId);
    }

    /**
     *
     * @return
     */
    public MonsterAreaPK getMonsterareaPK() {
        return monsterareaPK;
    }

    /**
     *
     * @param monsterareaPK
     */
    public void setMonsterareaPK(MonsterAreaPK monsterareaPK) {
        this.monsterareaPK = monsterareaPK;
    }

    /**
     *
     * @return
     */
    public int getMonsterquantity() {
        return monsterquantity;
    }

    /**
     *
     * @param monsterquantity
     */
    public void setMonsterquantity(int monsterquantity) {
        this.monsterquantity = monsterquantity;
    }

    /**
     *
     * @return
     */
    public Monster getMonster() {
        return monster;
    }

    /**
     *
     * @param monster
     */
    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    /**
     *
     * @return
     */
    public Area getArea() {
        return area;
    }

    /**
     *
     * @param area
     */
    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monsterareaPK != null ? monsterareaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterArea)) {
            return false;
        }
        MonsterArea other = (MonsterArea) object;
        if ((this.monsterareaPK == null && other.monsterareaPK != null) || (this.monsterareaPK != null && !this.monsterareaPK.equals(other.monsterareaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.muni.fi.pa165.entities.Monsterarea[ monsterareaPK=" + monsterareaPK + " ]";
    }
}
