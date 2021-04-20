package com.muni.fi.pa165.dao;

import com.muni.fi.pa165.entities.MonsterWeapon;
import com.muni.fi.pa165.entities.MonsterWeaponPK;
import java.util.List;

/**
 * Interface MonsterWeaponDao. Interface implemented by typed JpaDao objects
 * containing type specific methods.
 *
 * @author Michal Vinkler
 */
public interface MonsterWeaponDao extends GenericDao<MonsterWeapon, Long> {

    /**
     *
     * @param id
     * @return
     */
    public List<MonsterWeapon> getByMonsterId(Long id);

    /**
     *
     * @param id
     * @return
     */
    public List<MonsterWeapon> getByWeaponId(Long id);

    /**
     *
     * @param id
     */
    public void delete(MonsterWeaponPK id);

    /**
     *
     * @param id
     * @return
     */
    public MonsterWeapon findById(MonsterWeaponPK id);
}
