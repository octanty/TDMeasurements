/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Producer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
@Repository
public class ProducerDaoImpl implements ProducerDao {

    private EntityManager entityManager;

    public ProducerDaoImpl() {
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ProducerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Producer> getAll() {
        Query query = entityManager.createQuery("SELECT p FROM Producer p", Producer.class);
        return query.getResultList();
    }

    public List<Producer> findByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }

        Query query = entityManager.createQuery("SELECT p FROM Producer p WHERE UPPER(p.name) LIKE UPPER(:producerName)", Producer.class);
        query.setParameter("producerName", name);

        return query.getResultList();
    }

    public Producer findByIco(Integer ico) {

        if (ico == null) {
            throw new IllegalArgumentException("ICO is null");
        }
        Query query = entityManager.createQuery("SELECT p FROM Producer p WHERE p.ico = :ico", Producer.class);
        query.setParameter("ico", ico);
        Producer producer;

        try {
            producer = (Producer) query.getSingleResult();
        } catch (NoResultException e) {
            producer = null;
        }
        return producer;
    }

    public Producer findById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        Producer p = entityManager.find(Producer.class, id);
        return p;
    }

    public Producer create(Producer producer) {

        validate(producer);
        if (producer.getId() != null) {
            throw new IllegalArgumentException("Producer id is not null.");
        }
        entityManager.persist(producer);
        return producer;
    }

    public void remove(Producer producer) {
        entityManager.remove(producer);
    }

    public Producer update(Producer producer) {

        validate(producer);
        entityManager.merge(producer);
        return producer;
    }

    public static void validate(Producer producer) {
        if (producer == null) {
            throw new IllegalArgumentException("Producer is null.");
        }
        if (producer.getName() == null) {
            throw new IllegalArgumentException("Name of producer is null.");
        }
        if (producer.getName().length() == 0) {
            throw new IllegalArgumentException("Incorrect producer name.");
        }
    }
}
