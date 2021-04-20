package com.muni.fi.pa165.dao;

import com.muni.fi.pa165.entities.Weapon;

/**
 * Interface implemented by typed JpaDao objects containing type specific
 * methods.
 *
 * @author Aubrey Oosthuizen
 * 
 * @throws IllegalArgumentException when null is passed
 */
public interface WeaponDao extends GenericDao<Weapon, Long> {

    boolean checkAvailable(String weaponName)throws IllegalArgumentException;
}
