package com.muni.fi.pa165.entities;

import java.io.Serializable;
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
@Table(name = "MONSTERWEAPON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonsterWeapon.findAll", query = "SELECT m FROM MonsterWeapon m"),
    @NamedQuery(name = "MonsterWeapon.findByMonsterid", query = "SELECT m FROM MonsterWeapon m WHERE m.monsterweaponPK.monsterid = :monsterid"),
    @NamedQuery(name = "MonsterWeapon.findByWeaponid", query = "SELECT m FROM MonsterWeapon m WHERE m.monsterweaponPK.weaponid = :weaponid"),
    @NamedQuery(name = "MonsterWeapon.findByHitrate", query = "SELECT m FROM MonsterWeapon m WHERE m.hitrate = :hitrate"),
    @NamedQuery(name = "MonsterWeapon.findByEfficiency", query = "SELECT m FROM MonsterWeapon m WHERE m.efficiency = :efficiency"),
    @NamedQuery(name = "MonsterWeapon.findByDamage", query = "SELECT m FROM MonsterWeapon m WHERE m.damage = :damage"),
    @NamedQuery(name = "MonsterWeapon.findById", query = "SELECT m FROM MonsterWeapon m WHERE m.monsterweaponPK = :monsterweaponPK"),
    @NamedQuery(name = "MonsterWeapon.findByDescription", query = "SELECT m FROM MonsterWeapon m WHERE m.description = :description")})
public class MonsterWeapon implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @EmbeddedId
    protected MonsterWeaponPK monsterweaponPK;
    @Column(name = "HITRATE")
    private Integer hitrate;
    @Column(name = "EFFICIENCY")
    private Integer efficiency;
    @Column(name = "DAMAGE")
    private Integer damage;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "WEAPONID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Weapon weapon;
    @JoinColumn(name = "MONSTERID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Monster monster;

    /**
     *
     */
    public MonsterWeapon() {
    }

    /**
     *
     * @param monsterweaponPK
     */
    public MonsterWeapon(MonsterWeaponPK monsterweaponPK) {
        this.monsterweaponPK = monsterweaponPK;
    }

    /**
     *
     * @param monsterid
     * @param weaponid
     */
    public MonsterWeapon(long monsterid, long weaponid) {
        this.monsterweaponPK = new MonsterWeaponPK(monsterid, weaponid);
    }

    /**
     *
     * @return
     */
    public MonsterWeaponPK getMonsterweaponPK() {
        return monsterweaponPK;
    }

    /**
     *
     * @param monsterweaponPK
     */
    public void setMonsterweaponPK(MonsterWeaponPK monsterweaponPK) {
        this.monsterweaponPK = monsterweaponPK;
    }

    /**
     *
     * @return
     */
    public Integer getHitrate() {
        return hitrate;
    }

    /**
     *
     * @param hitrate
     */
    public void setHitrate(Integer hitrate) {
        this.hitrate = hitrate;
    }

    /**
     *
     * @return
     */
    public Integer getEfficiency() {
        return efficiency;
    }

    /**
     *
     * @param efficiency
     */
    public void setEfficiency(Integer efficiency) {
        this.efficiency = efficiency;
    }

    /**
     *
     * @return
     */
    public Integer getDamage() {
        return damage;
    }

    /**
     *
     * @param damage
     */
    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     *
     * @param weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monsterweaponPK != null ? monsterweaponPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonsterWeapon)) {
            return false;
        }
        MonsterWeapon other = (MonsterWeapon) object;
        if ((this.monsterweaponPK == null && other.monsterweaponPK != null) || (this.monsterweaponPK != null && !this.monsterweaponPK.equals(other.monsterweaponPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.muni.fi.pa165.entities.Monsterweapon[ monsterweaponPK=" + monsterweaponPK + " ]";
    }
}
