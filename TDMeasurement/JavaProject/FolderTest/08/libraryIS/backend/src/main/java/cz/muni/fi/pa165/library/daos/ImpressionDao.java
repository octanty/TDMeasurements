package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Impression;
import java.util.List;

/**
 *
 * @author MiškoHu
 */
public interface ImpressionDao {

    /**
     * Inserts impression into DB
     *
     * @param impression to be stored
     */
    void createImpression(Impression impression);

    /**
     * Returns all impressions from DB
     *
     * @return List of all impressions found in DB
     */
    List<Impression> findAllImpressions();

    /**
     * Finds impression with given ID
     *
     * @param long ID of impression to be found
     * @return impression with given ID or NULL if search was unsuccessful
     */
    Impression findImpressionById(Long id);

    /**
     * Deletes impression and all its books in DB
     *
     * @param impression to be deleted
     */
    void deleteImpression(Impression impression);

    /**
     * updates impression in DB
     *
     * @param impression to be updated
     */
    void updateImpression(Impression impression);
    
    /**
     * Searches in names and authors of impressions
     * @param search
     * @return list of impressions which author or name contains search
     */
    List<Impression> findImpressions(String search);
    

}
