package com.muni.fi.pa165.service;

import com.muni.fi.pa165.dto.MonsterWeaponDto;
import com.muni.fi.pa165.dto.MonsterWeaponPkDto;
import java.util.List;

/**
 *
 * @author irina
 */
public interface MonsterWeaponService {


    /**
     *
     * @param dto
     * @return
     */
    public MonsterWeaponDto save(MonsterWeaponDto dto);
    
    /**
     *
     * @param keyone
     * @param keytwo
     * @return
     */
    public MonsterWeaponPkDto getCompositeKey(Long keyone, Long keytwo);

    /**
     * Updates MonsterWeaponDto
     *
     * @param dto object of type MonsterWeaponDto
     */
    public MonsterWeaponDto update(MonsterWeaponDto dto);


    /**
     * Deletes MonsterWeaponDto
     *
     * @param dto object of type MonsterWeaponDto
     */
    public void delete(MonsterWeaponPkDto dto);

    /**
     * Finds MonsterWeaponDto by monsterId
     *
     * @param id ID of the monster
     * @return list object of type MonsterWeaponDto
     */
    public List<MonsterWeaponDto> findByMonsterId(Long id);

    /**
     * Finds MonsterWeaponDto by weaponId
     *
     * @param id ID of the weapon
     * @return list object of type MonsterWeaponDto
     */
    public List<MonsterWeaponDto> findByWeaponId(Long id);

    /**
     *
     * @return
     */
    public List<MonsterWeaponDto> findAll();

    
    /**
     *
     * @param monsterId
     * @param weaponId
     * @return
     */
    public MonsterWeaponDto findById(Long monsterId, Long weaponId);
}
