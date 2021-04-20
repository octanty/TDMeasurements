package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Stamp;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
@Repository
public class StampDaoImpl implements StampDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public StampDaoImpl() {
    }

    public StampDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Stamp create(Stamp stamp) {
        validate(stamp);
        if (stamp.getId() != null) {
            throw new IllegalArgumentException("Stamp already has an id.");
        }
        try {
            entityManager.persist(stamp);
        } catch(PersistenceException pe){
            throw new IllegalArgumentException("Incorrect stamp attributes.", pe);
        }
        return stamp;
    }

    @Override
    public Stamp update(Stamp stamp) {
        validate(stamp);
        if (stamp.getId() == null) {
            throw new IllegalArgumentException("Stamp id is null.");
        }
        Stamp updatedStamp = entityManager.merge(stamp);
        return updatedStamp;
    }

    @Override
    public void remove(Stamp stamp) {
        validate(stamp);
        if (stamp.getId() == null) {
            throw new IllegalArgumentException("Stamp id is null.");
        }
        Stamp stampRemove;
        stampRemove = entityManager.find(Stamp.class, stamp.getId());
        entityManager.remove(stampRemove);
    }

    @Override
    public Stamp findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null.");
        }
        if (id < 0) {
            throw new IllegalArgumentException("Id is lower than 0.");
        }
        Stamp stamp = entityManager.find(Stamp.class, id);
        return stamp;
    }

    @Override
    public Stamp findByNumber(String numberOfStamp) {
        if (numberOfStamp == null) {
            throw new IllegalArgumentException("Number of stamp is null.");
        }
        if (numberOfStamp.length() == 0) {
            throw new IllegalArgumentException("Lenght of stamp number is 0.");
        }
        Query query = entityManager.createQuery("SELECT s FROM Stamp s WHERE s.numberOfStamp LIKE :numberOfStamp", Stamp.class);
        query.setParameter("numberOfStamp", numberOfStamp);
        Stamp stamp;
        try {
            stamp = (Stamp) query.getSingleResult();
        } catch (NoResultException nre) {
            stamp = null;
        }

        return stamp;
    }

    @Override
    public List<Stamp> getAll() {
        Query query = entityManager.createQuery("SELECT s FROM Stamp s", Stamp.class);
        return query.getResultList();
    }

    public static void validate(Stamp stamp) {
        if (stamp == null) {
            throw new IllegalArgumentException("Stamp is null.");
        }
        if (stamp.getNumberOfStamp() == null) {
            throw new IllegalArgumentException("Number of stamp is null.");
        }
        if (stamp.getNumberOfStamp().length() == 0) {
            throw new IllegalArgumentException("Incorrect stamp number.");
        }
    }

}
