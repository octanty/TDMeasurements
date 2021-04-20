package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Producer;
import java.util.List;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
public interface ProducerDao {

    /**
     * Return all producers.
     *
     * @return list of all producers in DB
     */
    List<Producer> getAll();

    /**
     * Finds Producers by their name.
     *
     * @param name Producer name
     * @return List of Producer objects, empty if no Producer is found
     * @throws IllegalArgumentException if name is null
     */
    List<Producer> findByName(String name);

    /**
     * Returns producer with given ico or null if there is no producer with this
     * ico.
     *
     * @param ico producer ico.
     * @return Producer object or null if there is no producer with given ico.
     * @throws IllegalArgumentException if ico is null or has incorrect format.
     */
    Producer findByIco(Integer ico);

    /**
     * Returns producer with given id or null if there is no producer with this
     * id.
     *
     * @param id Id of producer.
     * @return Producer object or null if there is no producer with given id.
     * @throws IllegalArgumentException if id is null or has incorrect format.
     */
    Producer findById(Long id);

    /**
     * Stores producer into DB.
     *
     * @param producer Producer entity which will be stored into DB.
     * @return created instance
     * @throws IllegalArgumentException if producer is null, producer already
     * has id
     */
    Producer create(Producer producer);

    /**
     * Checks if producer is in DB and updates it.
     *
     * @param producer Producer entity which will be updated.
     * @return updated instance
     * @throws IllegalArgumentException if parameter is null.
     */
    Producer update(Producer producer);

    /**
     * Checks if producer is in DB and removes it.
     *
     * @param producer Producer entity which will be removed from DB.
     * @throws IllegalArgumentException if parameter is null.
     */
    void remove(Producer producer);
}
