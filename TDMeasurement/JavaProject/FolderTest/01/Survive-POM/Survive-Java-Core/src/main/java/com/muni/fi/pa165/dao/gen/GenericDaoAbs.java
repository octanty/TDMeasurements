package com.muni.fi.pa165.dao.gen;

import com.muni.fi.pa165.dao.GenericDao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Aubrey Oosthuizen
 *
 * Abstract class used for implementing CRUD operations on generic types. Used
 * to decouple data access and persistence from business layer. Will be extended
 * by each DAO object class for every entity type
 */
public abstract class GenericDaoAbs<T, ID> implements GenericDao<T, ID> {

    private Class<T> persistentClass;
    private EntityManagerFactory emf;

    public GenericDaoAbs(Class<T> persistenceClass) {
        this.persistentClass = persistenceClass;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Autowired
    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    @Override
    public T save(T entity) {

        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
            return entity;
        } finally {

            em.close();

        }
    }

    @Override
    public T update(T entity) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T mergedEntity = em.merge(entity);
            tx.commit();
            return mergedEntity;
        } finally {

            em.close();

        }
    }

    @Override
    public void delete(T entity) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            tx.commit();
        } finally {

            em.close();

        }
    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T entity = em.find(getPersistentClass(), id);
            em.merge(entity);
            em.remove(entity);
            tx.commit();
        } finally {

            em.close();

        }
    }

    /**
     *
     * @param id
     * @return Generic entity
     */
    @Override
    public T findById(ID id) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T entity = (T) em.find(getPersistentClass(), id);
            tx.commit();
            return entity;
        } finally {

            em.close();

        }
    }

    /**
     *
     * @return List<T> 
     */
    @Override
    public List<T> findAll() {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<T> list = new ArrayList();
        try {
            tx.begin();
            list = em.createQuery("select e from  " + getPersistentClass().getSimpleName() + " e").getResultList();
            tx.commit();
        } finally {

            em.close();

        }
        return list;
    }

    /**
     * Flushed current transaction
     */
    @Override
    public void flush() {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.flush();
            tx.commit();
        } finally {

            em.close();

        }
    }
}
