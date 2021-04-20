package com.muni.fi.pa165.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *Data transfer object for the {@link Monsterweapon}.
 * 
 * @author irina
 */
@XmlRootElement
public class MonsterWeaponDto {

    private MonsterDto monster;
    private WeaponDto weapon;
    private Integer hitRate;
    private Integer damage;
    private Integer efficiency;
    private String description;

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
    public MonsterDto getMonster() {
        return monster;
    }

    /**
     *
     * @param monster
     */
    public void setMonster(MonsterDto monster) {
        this.monster = monster;
    }

    /**
     *
     * @return
     */
    public WeaponDto getWeapon() {
        return weapon;
    }

    /**
     *
     * @param weapon
     */
    public void setWeapon(WeaponDto weapon) {
        this.weapon = weapon;
    }

    /**
     *
     * @return
     */
    public Integer getHitRate() {
        return hitRate;
    }

    /**
     *
     * @param hitRate
     */
    public void setHitRate(Integer hitRate) {
        this.hitRate = hitRate;
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
        final MonsterWeaponDto other = (MonsterWeaponDto) obj;
        return true;
    }
}
