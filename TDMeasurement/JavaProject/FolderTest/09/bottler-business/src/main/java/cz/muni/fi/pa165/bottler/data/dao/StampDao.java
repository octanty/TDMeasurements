package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Stamp;

import java.util.List;

/**
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
public interface StampDao {

    /**
     * Stores stamp into DB.
     * @param stamp Stamp entity which will be stored into DB.
     * @return created instance
     * @throws IllegalArgumentException if stamp is null, stamp already has id, stamp number is null or not unique.
     */
    Stamp create(Stamp stamp);

    /**
     * Checks if stamp is in DB and updates it.
     * @param stamp Stamp entity which will be updated.
     * @return updated instance
     * @throws IllegalArgumentException if parameter is null.
     */
    Stamp update(Stamp stamp);

    /**
     * Checks if stamp is in DB and removes it.
     * @param stamp Stamp entity which will be removed from DB.
     * @throws IllegalArgumentException if parameter is null.
     */
    void remove(Stamp stamp);

    /**
     * Returns stamp with given id or null if there is no stamp with this id.
     * @param id Id of stamp.
     * @return Stamp object or null if there is no stamp with given id.
     * @throws IllegalArgumentException if id is null or has incorrect format.
     */
    Stamp findById(Long id);

    /**
     * Returns stamp with given number or null if there is no stamp with this number.
     * @param numberOfStamp Number of stamp.
     * @return Stamp object or null if there is no stamp with given number.
     * @throws IllegalArgumentException if number is null or has incorrect format.
     */
    Stamp findByNumber(String numberOfStamp);

    /**
     * Return all stamps.
     * @return list of all stamps in DB
     */
    List<Stamp> getAll();

}
