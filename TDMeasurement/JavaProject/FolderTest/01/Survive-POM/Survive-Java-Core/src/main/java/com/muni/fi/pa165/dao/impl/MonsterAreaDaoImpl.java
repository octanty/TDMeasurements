package com.muni.fi.pa165.dao.impl;

import com.muni.fi.pa165.dao.MonsterAreaDao;
import com.muni.fi.pa165.dao.gen.GenericDaoAbs;
import com.muni.fi.pa165.entities.MonsterArea;
import com.muni.fi.pa165.entities.MonsterAreaPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
public class MonsterAreaDaoImpl extends GenericDaoAbs<MonsterArea, Long> implements MonsterAreaDao {

    /**
     *
     */
    public MonsterAreaDaoImpl() {
        super(MonsterArea.class);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<MonsterArea> getByMonsterId(Long id) {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        Query query = em.createNamedQuery("MonsterArea.findByMonsterId").setParameter("monsterid", id);
        EntityTransaction tx = em.getTransaction();
        List<MonsterArea> list = new ArrayList();
        try {
            tx.begin();
            list = query.getResultList();
            tx.commit();
        } finally {

            if (em != null) {
                em.close();
            }
        }
        return list;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<MonsterArea> getByAreaId(Long id) {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        Query query = em.createNamedQuery("MonsterArea.findByAreaId").setParameter("areaid", id);
        EntityTransaction tx = em.getTransaction();
        List<MonsterArea> list = new ArrayList();
        try {
            tx.begin();
            list = query.getResultList();
            tx.commit();
        } finally {

            if (em != null) {
                em.close();
            }
        }
        return list;

    }

    /**
     *
     * @param pk
     */
    @Override
    public void delete(MonsterAreaPK pk) {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Query query = em.createNamedQuery("MonsterArea.findById");
        query.setParameter("monsterareaPK", pk);
        MonsterArea obj = null;
        try {
            tx.begin();
            obj = (MonsterArea) query.getSingleResult();          
            em.merge(obj);
            em.remove(obj);
            tx.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    /**
     *
     * @return
     */
    @Override
    public List<MonsterArea> findAll() {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        Query query = em.createNamedQuery("MonsterArea.findAll");
        EntityTransaction tx = em.getTransaction();
        List<MonsterArea> list = new ArrayList();
        try {
            tx.begin();
            list = query.getResultList();
            tx.commit();
        } finally {

            if (em != null) {
                em.close();
            }
        }
        return list;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public MonsterArea findById(MonsterAreaPK id) {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        Query query = em.createNamedQuery("MonsterArea.findById");
        query.setParameter("monsterareaPK", id);
        EntityTransaction tx = em.getTransaction();
        MonsterArea obj = null;
        try {
            tx.begin();
            obj = (MonsterArea) query.getSingleResult();
            tx.commit();
        } finally {

            if (em != null) {
                em.close();
            }
        }
        return obj;

    }
}
