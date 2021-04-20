package com.muni.fi.pa165.dao.impl;

import com.muni.fi.pa165.dao.MonsterDao;
import com.muni.fi.pa165.dao.gen.GenericDaoAbs;
import com.muni.fi.pa165.entities.Monster;
import com.muni.fi.pa165.enums.MonsterClass;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * This class represents the implementation of all basic operations. Typed
 * JpaDao objects extending the GenericDaoAbs abstract class and implementing a
 * Type specific interface. This Dao object will be used to perform all
 * operations within the business layer.
 *
 * @author Michal Vikler
 */
@Repository
public class MonsterDaoImpl extends GenericDaoAbs<Monster, Long> implements MonsterDao {

    /**
     *
     */
    public MonsterDaoImpl() {
        super(Monster.class);
    }

    @Override
    public boolean checkAvailable(MonsterClass monsterClass) {

        if (monsterClass == null) {
            throw new IllegalArgumentException("Null argument.");
        }


        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        TypedQuery<Monster> query = em.createQuery("select p from " + getPersistentClass().getSimpleName() + " p where p.monsterclass = :class", Monster.class);
        query.setParameter("class", monsterClass);

        if (!query.getResultList().isEmpty()) {
            return true;
        }
        return false;

    }
}
