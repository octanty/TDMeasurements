package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.Liquor;
import cz.muni.fi.pa165.bottler.data.model.Producer;
import cz.muni.fi.pa165.bottler.data.model.Store;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

/**
 * Implementation of Bottle DAO
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@Repository
public class BottleDaoImpl implements BottleDao{

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BottleDaoImpl() {
    }

    public BottleDaoImpl(EntityManager em){
        this.entityManager = em;        
    }
    
    @Override
    public Bottle create(Bottle bottle) {
        
         if(bottle == null){
            throw new IllegalArgumentException("Bottle is null");
        }
        
        if(bottle.getId() != null){
            throw new IllegalArgumentException("Bottle already has its ID");
        }
        
        try{
          entityManager.persist(bottle);
        }catch(PersistenceException e){
            throw new IllegalArgumentException("Incorrect attributes in Bottle object", e);
        }
        return bottle;
    }

    @Override
    public Bottle update(Bottle bottle) {
        entityManager.merge(bottle);
        return bottle;
    }

    @Override
    public void remove(Bottle bottle) {
        entityManager.remove(bottle);
    }

    @Override
    public Bottle findById(Long id) {
        
        if(id == null){
            throw new IllegalArgumentException("ID cannot be null");
        }
        
        Bottle b = entityManager.find(Bottle.class, id);
        return b;
    }

    @Override
    public List<Bottle> findByLot(String lot) {
        
        if(lot == null){
            throw new IllegalArgumentException("lot cannot be null");
        }
        
        Query query = entityManager.createQuery("SELECT b FROM Bottle b WHERE b.lotCode LIKE :lot", Bottle.class);
        query.setParameter("lot", lot);
        
        return query.getResultList();
        
    }

    @Override
    public List<Bottle> findByProducer(Producer producer) {
        
        if(producer == null){
            throw new IllegalArgumentException("producer cannot be null");
        }
        
        if(producer.getId() == null){
            throw new IllegalArgumentException("producer ID cannot be null");
        }
        
        Query query = entityManager.createQuery("SELECT b FROM Bottle b WHERE b.liquor.producer.id = :producer", Bottle.class);
        query.setParameter("producer", producer.getId());

        return query.getResultList();
        
        
    }

    @Override
    public List<Bottle> findByLiquor(Liquor liquor) {
        if(liquor == null){
            throw new IllegalArgumentException("liquor cannot be null");
        }
        
        if(liquor.getId() == null){
            throw new IllegalArgumentException("liquor ID cannot be null");
        }
        
        Query query = entityManager.createQuery("SELECT b FROM Bottle b WHERE b.liquor.id = :liquor", Bottle.class);
        query.setParameter("liquor", liquor.getId());
        
        return query.getResultList();
    }
    
    @Override
    public List<Bottle> findByStore(Store store) {
      
         if(store == null){
            throw new IllegalArgumentException("store cannot be null");
        }
        
        if(store.getId() == null){
            throw new IllegalArgumentException("store ID cannot be null");
        }
        
        Query query = entityManager.createQuery("SELECT b FROM Bottle b WHERE b.store.id = :store", Bottle.class);
        query.setParameter("store", store.getId());
        
        return query.getResultList();
    }
    
    @Override
    public List<Bottle> findByLiquorAndStore(Liquor liquor, Store store) {
        if(liquor == null){
            throw new IllegalArgumentException("liquor cannot be null");
        }
        
        if(liquor.getId() == null){
            throw new IllegalArgumentException("liquor ID cannot be null");
        }
        
         if(store == null){
            throw new IllegalArgumentException("store cannot be null");
        }
        
        if(store.getId() == null){
            throw new IllegalArgumentException("store ID cannot be null");
        }
        
        Query query = entityManager.createQuery("SELECT b FROM Bottle b WHERE b.liquor.id = :liquor AND b.store.id = :store", Bottle.class);
        query.setParameter("liquor", liquor.getId());
        query.setParameter("store", store.getId());
        
        return query.getResultList();
    }

    @Override
    public List<Bottle> findByProducedDate(DateTime start, DateTime end) {
        
        if(start == null || end == null){
            throw new IllegalArgumentException("start nor end can be null");
        }
        
        
        Query query = entityManager.createQuery("SELECT b FROM Bottle b WHERE b.producedDate BETWEEN :start AND :end", Bottle.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
        
    }

    @Override
    public List<Bottle> getAll() {
        
        Query query = entityManager.createQuery("SELECT b FROM Bottle b", Bottle.class);
        return query.getResultList();
    }
    
}
