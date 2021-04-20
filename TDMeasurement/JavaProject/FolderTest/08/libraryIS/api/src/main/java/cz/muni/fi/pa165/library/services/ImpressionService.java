package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import java.util.List;

/**
 *
 * @author Matej
 */
public interface ImpressionService {

    /**
     * Inserts impression into DB
     *
     * @param impression to be stored
     */
    void createImpression(ImpressionTO impression);

    /**
     * Returns all impressions from DB
     *
     * @return List of all impressions found in DB
     */
    List<ImpressionTO> findAllImpressions();

    /**
     * Finds impression with given ID
     *
     * @param long ID of impression to be found
     * @return impression with given ID or NULL if search was unsuccessful
     */
    ImpressionTO findImpressionById(Long id);

    /**
     * Deletes impression and all its books in DB
     *
     * @param impression to be deleted
     */
    void deleteImpression(ImpressionTO impression);

    /**
     * updates impression in DB
     *
     * @param impression to be updated
     */
    void updateImpression(ImpressionTO impression);

    /**
     * Searches in names and authors of impressions
     *
     * @param search
     * @return list of impressions which author or name contains search
     */
    List<ImpressionTO> findImpressions(String search);

}
