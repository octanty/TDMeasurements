package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Store;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
@Repository
public class StoreDaoImpl implements StoreDao {

    private EntityManager entityManager;

    public StoreDaoImpl() {
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public StoreDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Store> getAll() {
        Query query = entityManager.createQuery("SELECT s FROM Store s", Store.class);
        return query.getResultList();
    }

    @Override
    public List<Store> findByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }

        Query query = entityManager.createQuery("SELECT s FROM Store s WHERE UPPER(s.name) LIKE UPPER(:storeName)", Store.class);
        query.setParameter("storeName", name);

        return query.getResultList();
    }

    @Override
    public Store findByIco(Integer ico) {

        if (ico == null) {
            throw new IllegalArgumentException("ico is null");
        }

        Query query = entityManager.createQuery("SELECT s FROM Store s WHERE s.ico LIKE :ico", Store.class);
        query.setParameter("ico", ico);

        Store store;

        try {
            store = (Store) query.getSingleResult();
        } catch (NoResultException e) {
            store = null;
        }
        return store;
    }
    
    @Override
    public List<Store> findByAddress(String address) {

        if (address == null) {
            throw new IllegalArgumentException("address is null");
        }

        Query query = entityManager.createQuery("SELECT s FROM Store s WHERE UPPER(s.address) LIKE UPPER(:storeAddress)", Store.class);
        query.setParameter("storeAddress", address);

        return query.getResultList();
    }

    @Override
    public Store findById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        Store s = entityManager.find(Store.class, id);
        return s;
    }

    @Override
    public Store create(Store store) {

        validate(store);
        if (store.getId() != null) {
            throw new IllegalArgumentException("Store id is not null.");
        }
        entityManager.persist(store);
        return store;
    }

    @Override
    public void remove(Store store) {
        entityManager.remove(store);
    }

    @Override
    public Store update(Store store) {

        validate(store);
        entityManager.merge(store);
        return store;
    }

    public static void validate(Store store) {
        if (store == null) {
            throw new IllegalArgumentException("Store is null.");
        }
        if (store.getName() == null) {
            throw new IllegalArgumentException("Name of store is null.");
        }
        if (store.getName().length() == 0) {
            throw new IllegalArgumentException("Name of store is empty.");
        }
    }
}
