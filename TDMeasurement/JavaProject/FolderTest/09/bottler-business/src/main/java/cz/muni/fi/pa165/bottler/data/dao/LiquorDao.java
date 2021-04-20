package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Liquor;
import cz.muni.fi.pa165.bottler.data.model.Producer;
import java.util.List;

/**
 * Methods for manipulating with Liquor entities.
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
public interface LiquorDao {
    
    /**
     * Stores a liquor into a DB 
     * @param liquor Liquor object
     * @return created object
     * @throws IllegalArgumentException if liquor is null, liquor already has id, or some attributes are missing     
     */
    Liquor create(Liquor liquor);
    
    
    /**
     * Checks if liquor is in DB and updates it.
     * @param liquor Liquor entity which will be updated.
     * @return updated instance
     * @throws IllegalArgumentException if the instance is not an entity or the entity is not managed
     * @throws EntityNotFoundException if the entity no longer exists in the database
     */
    Liquor update(Liquor liquor);
    
    /**
     * Checks if liquor is in DB and removes it.
     * @param liqor Liquor entity which will be removed from DB.
     * @throws IllegalArgumentException if the instance is not an entity
     */
    void remove(Liquor liquor);

    
    /**
     * Finds a Liquor by its ID.
     * 
     * @param id Liquor ID
     * @return Liquor object, or null if not found
     * @throws IllegalArgumentException if id is null
     */
    Liquor findById(Long id);
    
    /**
     * Finds Liquors by their name.
     * 
     * @param name Liquor name
     * @return List of Liquor objects, empty if no Liquor found
     * @throws IllegalArgumentException if name is null
     */
    List<Liquor> findByName(String name);
    
     /**
     * Finds Liquors by their producer.
     * 
     * @param producer Producer object
     * @return List of Liquor objects, empty if no Liquor found
     * @throws IllegalArgumentException if producer is null
     */
    List<Liquor> findByProducer(Producer producer);
    
    /**
     * Returns all liquors.
     * 
     * @return List of Liquor objects
     */
    List<Liquor> getAll();
    
    
    
    
    
}
