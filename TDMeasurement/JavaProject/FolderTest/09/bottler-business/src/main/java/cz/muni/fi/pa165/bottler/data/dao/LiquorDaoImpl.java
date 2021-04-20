package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Liquor;
import cz.muni.fi.pa165.bottler.data.model.Producer;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * Implementation of Liquor DAO
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@Repository
public class LiquorDaoImpl implements LiquorDao{
    
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public LiquorDaoImpl() {
    }

    public LiquorDaoImpl(EntityManager em){
        this.entityManager = em;        
    }
    
    public Liquor create(Liquor liquor) {
        
        if(liquor == null){
            throw new IllegalArgumentException("Liquor is null");
        }
        
        if(liquor.getId() != null){
            throw new IllegalArgumentException("Liquor already has its ID");
        }
        
        try{
          entityManager.persist(liquor);
        }catch(PersistenceException e){
            throw new IllegalArgumentException("Incorrect attributes in Liquor object", e);
        }
        return liquor;
    }

    public Liquor update(Liquor liquor) {
        entityManager.merge(liquor);
        return liquor;
    }

    public void remove(Liquor liquor) {
        entityManager.remove(liquor);
    }

    public Liquor findById(Long id) {
        
        if(id == null){
            throw new IllegalArgumentException("ID cannot be null");
        }
        
        Liquor l = entityManager.find(Liquor.class, id);
        return l;
    }

    public List<Liquor> findByName(String name) {
        
        if(name == null){
            throw new IllegalArgumentException("name cannot be null");
        }
        
        Query query = entityManager.createQuery("SELECT l FROM Liquor l WHERE l.name LIKE :liquorName", Liquor.class);
        query.setParameter("liquorName", name);
        
        return query.getResultList();
    }

    public List<Liquor> findByProducer(Producer producer) {
       
        if(producer == null){
            throw new IllegalArgumentException("producer cannot be null");
        }
        
        if(producer.getId() == null){
            throw new IllegalArgumentException("producer ID cannot be null");
        }
        
        Query query = entityManager.createQuery("SELECT l FROM Liquor l WHERE l.producer = :producer", Liquor.class);
        query.setParameter("producer", producer);
        
        return query.getResultList();
    }

    public List<Liquor> getAll() {
        Query query = entityManager.createQuery("SELECT l FROM Liquor l", Liquor.class);
        return query.getResultList();
    }
    
}
