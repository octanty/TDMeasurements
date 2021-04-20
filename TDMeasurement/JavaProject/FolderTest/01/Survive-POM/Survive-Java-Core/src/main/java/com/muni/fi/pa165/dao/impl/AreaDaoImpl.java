package com.muni.fi.pa165.dao.impl;

import com.muni.fi.pa165.dao.AreaDao;
import com.muni.fi.pa165.dao.gen.GenericDaoAbs;
import com.muni.fi.pa165.entities.Area;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * This class represents the implementation of all basic operations. Typed
 * JpaDao objects extending the GenericJpaDao abstract class and implementing a
 * Type specific interface. This Dao object will be used to perform all
 * operations within the business layer.
 *
 * @author Aubrey Oosthuizen
 */
@Repository
public class AreaDaoImpl extends GenericDaoAbs<Area, Long> implements AreaDao {

    /**
     *
     */
    public AreaDaoImpl() {
        super(Area.class);
    }

    /**
     *
     * @param areaName
     * @return
     */
    @Override
    public boolean checkAvailable(String areaName) {
        if (areaName == null) {
            throw new IllegalArgumentException("Null argument.");
        }

        EntityManager em = this.getEntityManagerFactory().createEntityManager();

        TypedQuery<Area> query = em.createQuery("SELECT a from Area a where a.name = :name", Area.class);
        query.setParameter("name", areaName);

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            em.close();
            if (!query.getResultList().isEmpty()) {
                return true;
            }
        } finally {

            if (em != null) {
            }
        }

        return false;

    }
}
