package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Store;
import java.util.List;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
public interface StoreDao {

    /**
     * Return all stores.
     *
     * @return list of all stores in DB
     */
    List<Store> getAll();

    /**
     * Finds Stores by their name.
     *
     * @param name Store name
     * @return List of Store objects, empty if no Store is found
     * @throws IllegalArgumentException if name is null
     */
    List<Store> findByName(String name);

    /**
     * Finds Stores by their address.
     *
     * @param address Store address
     * @return List of Store objects, empty if no Store is found
     * @throws IllegalArgumentException if address is null
     */
    List<Store> findByAddress(String address);

    /**
     * Returns store with given id or null if there is no store with this id.
     *
     * @param id Id of store.
     * @return Store object or null if there is no store with given id.
     * @throws IllegalArgumentException if id is null or has incorrect format.
     */
    Store findById(Long id);
    
     /**
     * Returns store with given ico or null if there is no store with this
     * ico.
     *
     * @param ico store ico.
     * @return Store object or null if there is no store with given ico.
     * @throws IllegalArgumentException if ico is null or has incorrect format.
     */
    Store findByIco(Integer ico);

    /**
     * Stores store into DB.
     *
     * @param store Store entity which will be stored into DB.
     * @return created instance
     * @throws IllegalArgumentException if store is null, store already has id
     */
    Store create(Store store);

    /**
     * Checks if store is in DB and updates it.
     *
     * @param store Store entity which will be updated.
     * @return updated instance
     * @throws IllegalArgumentException if parameter is null.
     */
    Store update(Store store);

    /**
     * Checks if store is in DB and removes it.
     *
     * @param store Store entity which will be removed from DB.
     * @throws IllegalArgumentException if parameter is null.
     */
    void remove(Store store);
}
