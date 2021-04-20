package com.muni.fi.pa165.dao.impl;

import com.muni.fi.pa165.dao.MonsterWeaponDao;
import com.muni.fi.pa165.dao.gen.GenericDaoAbs;
import com.muni.fi.pa165.entities.MonsterWeapon;
import com.muni.fi.pa165.entities.MonsterWeaponPK;
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
public class MonsterWeaponDaoImpl extends GenericDaoAbs<MonsterWeapon, Long> implements MonsterWeaponDao {

    /**
     *
     */
    public MonsterWeaponDaoImpl() {
        super(MonsterWeapon.class);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<MonsterWeapon> getByMonsterId(Long id) {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        Query query = em.createNamedQuery("MonsterWeapon.findByMonsterid").setParameter("monsterid", id);
        EntityTransaction tx = em.getTransaction();
        List<MonsterWeapon> list = new ArrayList();
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
    public List<MonsterWeapon> getByWeaponId(Long id) {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        Query query = em.createNamedQuery("MonsterWeapon.findByWeaponid").setParameter("weaponid", id);
        EntityTransaction tx = em.getTransaction();
        List<MonsterWeapon> list = new ArrayList();
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
    public void delete(MonsterWeaponPK pk) {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Query query = em.createNamedQuery("MonsterWeapon.findById");
        query.setParameter("monsterweaponPK", pk);
        MonsterWeapon obj = null;
        try {
            tx.begin();
            obj = (MonsterWeapon) query.getSingleResult();
            //em.merge(pk);
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
    public List<MonsterWeapon> findAll() {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        Query query = em.createNamedQuery("MonsterWeapon.findAll");
        EntityTransaction tx = em.getTransaction();
        List<MonsterWeapon> list = new ArrayList();
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
    public MonsterWeapon findById(MonsterWeaponPK id) {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        Query query = em.createNamedQuery("MonsterWeapon.findById");
        query.setParameter("monsterweaponPK", id);
        EntityTransaction tx = em.getTransaction();
        MonsterWeapon obj = null;
        try {
            tx.begin();
            obj = (MonsterWeapon) query.getSingleResult();
            tx.commit();
        } finally {

            if (em != null) {
                em.close();
            }
        }
        return obj;

    }
}
