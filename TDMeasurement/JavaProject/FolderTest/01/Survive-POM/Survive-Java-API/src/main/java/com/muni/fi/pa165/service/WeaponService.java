package com.muni.fi.pa165.service;

import com.muni.fi.pa165.dto.WeaponDto;
import java.util.List;

/**
 *
 * @author Michal Vinkler
 */
public interface WeaponService {

    /**
     * Saves WeaponDto
     *
     * @param dto object of type WeaponDto
     * @return 
     */
    public WeaponDto save(WeaponDto dto);

    /**
     * Updates WeaponDto
     *
     * @param dto object of type WeaponDto
     * @return 
     */
    public WeaponDto update(WeaponDto dto);

  

    /**
     * Finds WeaponDto by id
     *
     * @param id ID of the weapon
     * @return Weapon object of type WeaponDto
     */
    public WeaponDto findById(Long id);

    /**
     *
     * @return
     */
    public List<WeaponDto> findAll();


    /**
     *
     * @param id
     */
    public void delete(Long id);

    public WeaponDto checkAndSave(WeaponDto dto);
}
