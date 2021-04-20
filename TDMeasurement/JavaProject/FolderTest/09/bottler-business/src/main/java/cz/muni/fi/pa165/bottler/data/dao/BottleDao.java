package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.Liquor;
import cz.muni.fi.pa165.bottler.data.model.Producer;
import cz.muni.fi.pa165.bottler.data.model.Store;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.joda.time.DateTime;

/**
 * Methods for manipulating with Bottle entities.
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
public interface BottleDao {
    
     /**
     * Stores a bottle into a DB 
     * @param bottle Bottle object
     * @return created object
     * @throws IllegalArgumentException if bottle is null, bottle already has id, or some attributes are missing     
     */
    Bottle create(Bottle bottle);
    
    
    /**
     * Checks if bottle is in DB and updates it.
     * @param bottle Bottle entity which will be updated.
     * @return updated object
     * @throws IllegalArgumentException if the instance is not an entity or the entity is not managed
     * @throws EntityNotFoundException if the entity no longer exists in the database
     */
    Bottle update(Bottle bottle);
    
    /**
     * Checks if bottle is in DB and removes it.
     * @param bottle Bottle entity which will be removed from DB.
     * @throws IllegalArgumentException if the instance is not an entity
     */
    void remove(Bottle bottle);

    
    /**
     * Finds a Liquor by its ID.
     * 
     * @param id Liquor ID
     * @return Liquor object, or null if not found
     * @throws IllegalArgumentException if id is null
     */
    Bottle findById(Long id);
    
    /**
     * Finds Bottles by their LOT ("cislo sarze").
     * 
     * @param lot Bottle LOT
     * @return List of Bottle objects, empty list if not found
     */
    List<Bottle> findByLot(String lot);
    
    /**
     * Finds Bottles by their producer.
     * 
     * @param producer Bottle producer
     * @return List of Bottle objects, empty list if not found
     */
    List<Bottle> findByProducer(Producer producer);
    
    /**
     * Finds Bottles by Liquor their contain.
     * 
     * @param liquor Liquor
     * @return List of Bottle objects, empty list if not found
     */
    List<Bottle> findByLiquor(Liquor liquor);
    
    /**
     * Finds Bottles by Store they are in
     * 
     * @param store Store
     * @return List of Bottle objects, empty list if not found
     */
    List<Bottle> findByStore(Store store);
    
      /**
     * Finds Bottles by Liquor their contain and Store they are in.
     * 
     * @param liquor Liquor
     * @param store Store
     * @return List of Bottle objects, empty list if not found
     */
    List<Bottle> findByLiquorAndStore(Liquor liquor, Store store);
    
    /**
     * Finds Bottles by date and time they were produced.
     * 
     * @param start Start of the interval
     * @param end End of the interval
     * @returnList of Bottle objects, empty list if not found
     */
    List<Bottle> findByProducedDate(DateTime start, DateTime end);
    
    /**
     * Returns all Bottles
     * @return List of all bottles in the DB
     */
    List<Bottle> getAll();
    
    
}
