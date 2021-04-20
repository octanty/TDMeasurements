package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dto.StampDto;
import java.util.List;

/**
 * Service for stamps
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
public interface StampService {

    /**
     * Creates a stamp
     * @param resultDto Result to be created
     * @return Created stamp
     */
    StampDto createStamp(StampDto resultDto);

    /**
     * Updates stamp
     * @param resultDto Result to be updated
     * @return Updated stamp
     */
    StampDto updateStamp(StampDto resultDto);

    /**
     * Removes stamp
     * @param resultDto Result to be removed
     */
    void removeStamp(StampDto resultDto);

    /**
     * List of all stamps
     * @return List<StampDto> List of all stamps
     */
    List<StampDto> getAllStamps();

    /**
     * Finds stamp
     * @param id Id of required stamp
     * @return StampDto Found stamp
     */
    StampDto findStampById(Long id);
}
