package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dto.CompanyDto;
import cz.muni.fi.pa165.bottler.data.dto.ProducerDto;
import cz.muni.fi.pa165.bottler.data.dto.StoreDto;
import java.util.List;

/**
 * Service for administration of companies - Stores and Producers
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
  */
public interface CompanyRegisterService {
    
    /**
     * Creates a producer
     * 
     * @param producer
     * @return Created Producer object with filled ID
     */
    public ProducerDto createProducer(ProducerDto producer);
    
    /**
     * Updates a producer
     * 
     * @param producer Producer to be updated
     * @return producerDto Updated producer
     */
    public ProducerDto updateProducer(ProducerDto producer);
    
    /**
     * Removes a producer
     * @param producer 
     */
    public void removeProducer(ProducerDto producer);
    
    /**
     * Creates a store
     * 
     * @param store
     * @return Created Store object with filled ID
     */
    public StoreDto createStore(StoreDto store);
    
    /**
     * Updates a store
     * @param store
     * @return StoreDto Updated store
     */
    public StoreDto updateStore(StoreDto store);
    
    /**
     * Removes a store
     * @param store 
     */
    public void removeStore(StoreDto store);

    /**
     * Finds a store by ID. If no store found, returns null
     * @param id
     * @return Store object or null
     */
    public StoreDto findStoreById(long id);

    /**
     * Finds stores by their name
     * @param name
     * @return List of found stores, empty list if no stores found
     */
    public List<StoreDto> findStoreByName(String name);


     /**
     * Finds stores by their address
     * @param address
     * @return List of found stores, empty list if no stores found
     */
    public List<StoreDto> findStoreByAddress(String address);

    /**
     * Returns all stores
     * @return List of all stores
     */
    public List<StoreDto> getAllStores();
    
    /**
     * Finds a producer by ICO. If no producer found, returns null
     * @param ico
     * @return Producer object or null
     */
    public ProducerDto findProducerByIco(int ico);
    
    /**
     * Finds a producer by ID. If no producer found, returns null
     * @param id
     * @return Producer object or null
     */
    public ProducerDto findProducerById(long id);

    /**
     * Returns all producers
     * @return List<ProducerDto> List of all producers
     */
    public List<ProducerDto> getAllProducers();

    /**
     * Finds a company (store, producer) by ICO
     * @param ico
     * @return CompanyDto object or null if not found
     */
    public CompanyDto findByIco(int ico);

}
