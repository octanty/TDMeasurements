package cz.muni.fi.pa165.bottler.service;


import cz.muni.fi.pa165.bottler.data.dao.BottleDao;
import cz.muni.fi.pa165.bottler.data.dao.LiquorDao;
import cz.muni.fi.pa165.bottler.data.dto.*;
import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.Liquor;
import static cz.muni.fi.pa165.bottler.service.EntityAndDtoMapping.*;
import cz.muni.fi.pa165.bottler.data.model.Stamp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/**
 * LiquorBottle service class.
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */

@Service
@Transactional
public class LiquorBottleServiceImpl implements LiquorBottleService {

    @Autowired
    private BottleDao bottleDao;
    @Autowired
    private LiquorDao liquorDao;

    private Subject currentUser;

    public void setBottleDao(BottleDao bottleDao) {
        this.bottleDao = bottleDao;
    }

    public void setLiquorDao(LiquorDao liquorDao) {
        this.liquorDao = liquorDao;
    }

    @Override
    public LiquorDto createLiquor(LiquorDto liquorDto) {
        currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("admin")) {
            Liquor liquor = liquorDtoToLiquor(liquorDto, true);
            return liquorToLiquorDto(liquorDao.create(liquor));
        } else {
            return null;
        }
    }

    @Override
    public LiquorDto updateLiquor(LiquorDto liquorDto) {
        currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("admin")) {
            Liquor liquor = liquorDao.findById(liquorDto.getId());
            liquor.setAlcoholPercentage(liquorDto.getAlcoholPercentage());
            liquor.setName(liquorDto.getName());
            liquor.setEan(liquorDto.getEan());
            liquor.setVolume(liquorDto.getVolume());
            liquor.setProducer(producerDtoToProducer(liquorDto.getProducer()));
            return liquorToLiquorDto(liquorDao.update(liquor));
        } else {
            return null;
        }
    }

    @Override
    public void removeLiquor(LiquorDto liquorDto) {
        currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("admin")) {
            Liquor liquor = liquorDao.findById(liquorDto.getId());
            liquorDao.remove(liquor);
        }
    }

    @Override
    public LiquorDto findLiquorById(Long id) {
        return liquorToLiquorDto(liquorDao.findById(id));
    }

    @Override
    public List<LiquorDto> getAllLiquors() {
        return liquorToLiquorDto(liquorDao.getAll());
    }

    @Override
    public BottleDto createBottle(BottleDto bottleDto) {
        currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("admin")) {
            Bottle bottle = bottleDtoToBottle(bottleDto, true);
            return bottleToBottleDto(bottleDao.create(bottle));
        } else {
            return null;
        }
    }

    @Override
    public BottleDto updateBottle(BottleDto bottleDto) {
        currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("admin")) {
            Bottle bottle = bottleDao.findById(bottleDto.getId());
            bottle.setLotCode(bottleDto.getLotCode());
            bottle.setSold(bottleDto.isSold());
            bottle.setProducedDate(bottleDto.getProducedDate());
            bottle.setStamp(stampDtoToStamp(bottleDto.getStamp()));
            bottle.setStore(storeDtoToStore(bottleDto.getStore()));
            bottle.setLiquor(liquorDtoToLiquor(bottleDto.getLiquor()));
            return bottleToBottleDto(bottleDao.update(bottle));
        } else {
            return null;
        }
    }

    @Override
    public void removeBottle(BottleDto bottleDto) {
        currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("admin")) {
            Bottle bottle = bottleDao.findById(bottleDto.getId());
            bottleDao.remove(bottle);
        }
    }

    @Override
    public BottleDto findBottleById(Long id) {
        return bottleToBottleDto(bottleDao.findById(id));
    }

    @Override
    public List<BottleDto> getAllBottles() {
        return bottleToBottleDto(bottleDao.getAll());
    }

}
