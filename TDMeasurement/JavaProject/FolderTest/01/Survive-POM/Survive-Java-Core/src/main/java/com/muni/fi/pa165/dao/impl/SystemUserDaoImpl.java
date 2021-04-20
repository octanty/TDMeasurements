package com.muni.fi.pa165.dao.impl;

import com.muni.fi.pa165.dao.SystemUserDao;
import com.muni.fi.pa165.dao.gen.GenericDaoAbs;
import com.muni.fi.pa165.entities.SystemUser;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Typed JpaDao objects extending the GenericDaoAbs abstract class and implementing a Type specific interface. This Dao
 * object will be used to perform all operations within the business layer.
 *
 * @author Maria
 */
@Repository
public class SystemUserDaoImpl extends GenericDaoAbs<SystemUser, Long> implements SystemUserDao {

    /**
     *
     */
    public SystemUserDaoImpl() {
        super(SystemUser.class);
    }


    @Override
    public List<SystemUser> findAll() {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        Query query = em.createNamedQuery("SystemUser.findAll");
        EntityTransaction tx = em.getTransaction();
        List<SystemUser> list = new ArrayList();
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
    



    @Override
    public SystemUser findByName(String username) {
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        Query query = em.createNamedQuery("SystemUser.findByName");
        query.setParameter("username", username);
        EntityTransaction tx = em.getTransaction();
        SystemUser obj = null;
        try {
            tx.begin();
            obj = (SystemUser) query.getSingleResult();
            tx.commit();
        } finally {

            if (em != null) {
                em.close();
            }
        }
        return obj;
    }
}
