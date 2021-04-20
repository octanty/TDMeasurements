package com.muni.fi.pa165.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Auron
 */
@XmlRootElement
public class MonsterWeaponPkDto {

    private Long monsterId;
    private Long weaponId;

    /**
     *
     */
    public MonsterWeaponPkDto() {
    }

    /**
     *
     * @param monsterId
     * @param weaponId
     */
    public MonsterWeaponPkDto(Long monsterId, Long weaponId) {
        this.monsterId = monsterId;
        this.weaponId = weaponId;
    }

    /**
     *
     * @return
     */
    public Long getMonsterId() {
        return monsterId;
    }

    /**
     *
     * @param monsterId
     */
    public void setMonsterId(Long monsterId) {
        this.monsterId = monsterId;
    }

    /**
     *
     * @return
     */
    public Long getWeaponId() {
        return weaponId;
    }

    /**
     *
     * @param weaponId
     */
    public void setWeaponId(Long weaponId) {
        this.weaponId = weaponId;
    }
}
