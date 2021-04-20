package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dao.BottleDao;
import cz.muni.fi.pa165.bottler.data.dao.StoreDao;
import cz.muni.fi.pa165.bottler.data.dao.TestResultDao;
import cz.muni.fi.pa165.bottler.data.dto.BottleDto;
import cz.muni.fi.pa165.bottler.data.dto.StoreDto;
import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.Store;
import cz.muni.fi.pa165.bottler.data.model.TestResult;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    BottleDao bottleDao;
    @Autowired
    StoreDao storeDao;
    @Autowired
    TestResultDao testResultDao;

    public void setBottleDao(BottleDao bottleDao) {
        this.bottleDao = bottleDao;
    }

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }
    
     public void setTestResultDao(TestResultDao testResultDao) {
        this.testResultDao = testResultDao;
    }

    @Override
    public void addBottleToStore(BottleDto bottleDto, StoreDto storeDto) {
        Bottle bottle = bottleDao.findById(bottleDto.getId());
        Store store = storeDao.findById(storeDto.getId());
        bottle.setStore(store);
        bottleDao.update(bottle);
    }

    @Override
    public void sellBottle(BottleDto bottleDto) {
        Bottle bottle = bottleDao.findById(bottleDto.getId());
        bottle.setSold(true);
        bottleDto.setSold(true);
        bottleDao.update(bottle);
    }
    
    @Override
    public List<BottleDto> getAvailableBottles(StoreDto storeDto)
    {
        Store store = storeDao.findById(storeDto.getId());

        List<Bottle> bottles = bottleDao.findByStore(store);
        List<BottleDto> availableBottles = new ArrayList<>();

        for (Bottle b : bottles) {

            // test if sold
            if (b.isSold()) {
                continue;
            }

            // test if toxic
            boolean toxic = false;
            List<TestResult> results = testResultDao.findByBottle(b);
            for (TestResult r : results) {
                if (r.isToxic()) {
                    toxic = true;
                    break;
                }
            }
            // if toxic, skip this one
            if (toxic) {
                continue;
            }

            availableBottles.add(EntityAndDtoMapping.bottleToBottleDto(b));

        }

        return availableBottles;
    }
}
