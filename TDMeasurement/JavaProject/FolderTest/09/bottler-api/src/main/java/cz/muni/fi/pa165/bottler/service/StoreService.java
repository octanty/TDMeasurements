package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dto.BottleDto;
import cz.muni.fi.pa165.bottler.data.dto.StoreDto;
import java.util.List;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
public interface StoreService {

    void addBottleToStore(BottleDto bottle, StoreDto store);

    void sellBottle(BottleDto bottle);
    
    List<BottleDto> getAvailableBottles(StoreDto store);
}
