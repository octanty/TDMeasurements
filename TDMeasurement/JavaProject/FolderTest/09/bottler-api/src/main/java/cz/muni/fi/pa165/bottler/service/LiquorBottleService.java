package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dto.*;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Interface for LiquorBottle service layer.
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
public interface LiquorBottleService {

    /**
     * Store liquor
     * @param liquorDto Liquor to be saved
     * @return Stored liquor
     */
    LiquorDto createLiquor(LiquorDto liquorDto);

    /**
     * Update liquor
     * @param liquorDto Liquor to be updated
     * @return Updated liquor
     */
    LiquorDto updateLiquor(LiquorDto liquorDto);

    /**
     * Remove liquor
     * @param liquorDto to be removed
     */
    void removeLiquor(LiquorDto liquorDto);

    /**
     * Find liquor by id
     * @param id ID of specific liqor
     * @return LiquorDto if liquor is found, null otherwise
     */
    LiquorDto findLiquorById(Long id);

    /**
     * Find all liquors
     * @return List of all liquors
     */
    List<LiquorDto> getAllLiquors();

    /**
     * Store bottle
     * @param bottleDto Bottle to be saved
     * @return Stored bottle
     */
    BottleDto createBottle(BottleDto bottleDto);

    /**
     * Update bottle
     * @param bottleDto Bottle to be updated
     * @return Updated bottle
     */
    BottleDto updateBottle(BottleDto bottleDto);

    /**
     * Remove bottle
     * @param bottleDto Bottle to be removed
     */
    void removeBottle(BottleDto bottleDto);

    /**
     * Find bottle by ID
     * @param id
     * @return Bottle with given id, null otherwise
     */
    BottleDto findBottleById(Long id);

    /**
     * Find all bottles
     * @return List of all bottles
     */
    List<BottleDto> getAllBottles();
}
