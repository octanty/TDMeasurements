package com.muni.fi.pa165.dao;

import com.muni.fi.pa165.entities.MonsterArea;
import com.muni.fi.pa165.entities.MonsterAreaPK;
import java.util.List;

/**
 * Interface MonsterAreaDao. Interface implemented by typed JpaDao objects
 * containing type specific methods
 *
 * @author Michal Vinkler
 */
public interface MonsterAreaDao extends GenericDao<MonsterArea, Long> {

    List<MonsterArea> getByMonsterId(Long id);

    List<MonsterArea> getByAreaId(Long id);

    List<MonsterArea> findAll();

    MonsterArea findById(MonsterAreaPK id);

    void delete(MonsterAreaPK pk);
}
