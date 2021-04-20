package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dao.BottleDao;
import cz.muni.fi.pa165.bottler.data.dao.LiquorDao;
import cz.muni.fi.pa165.bottler.data.dao.ProducerDao;
import cz.muni.fi.pa165.bottler.data.dao.StoreDao;
import cz.muni.fi.pa165.bottler.data.dao.TestResultDao;
import cz.muni.fi.pa165.bottler.data.dto.BottleDto;
import cz.muni.fi.pa165.bottler.data.dto.CompanyDto;
import cz.muni.fi.pa165.bottler.data.dto.LiquorDto;
import cz.muni.fi.pa165.bottler.data.dto.ProducerDto;
import cz.muni.fi.pa165.bottler.data.dto.StatisticsDto;
import cz.muni.fi.pa165.bottler.data.dto.StoreDto;
import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.Liquor;
import cz.muni.fi.pa165.bottler.data.model.Producer;
import cz.muni.fi.pa165.bottler.data.model.Store;
import cz.muni.fi.pa165.bottler.data.model.TestResult;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of statistics
 *
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private BottleDao bottleDao;
    @Autowired
    private LiquorDao liquorDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private ProducerDao producerDao;
    @Autowired
    private TestResultDao testResultDao;

    // <editor-fold desc="Setters and getters." defaultstate="collapsed">
    public void setBottleDao(BottleDao bottleDao) {
        this.bottleDao = bottleDao;
    }

    public void setLiquorDao(LiquorDao liquorDao) {
        this.liquorDao = liquorDao;
    }

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public void setProducerDao(ProducerDao producerDao) {
        this.producerDao = producerDao;
    }

    public void setTestResultDao(TestResultDao testResultDao) {
        this.testResultDao = testResultDao;
    }

    // </editor-fold>

    @Override
    public StatisticsDto getStatistics(CompanyDto companyDto) {
        return this.getStatistics(companyDto, null, null);
    }

    @Override
    public StatisticsDto getStatistics(CompanyDto companyDto, DateTime start, DateTime end) {

        Store store = storeDao.findByIco(companyDto.getIco());
        if (store != null) {
            return this.getStatisticsForStore(EntityAndDtoMapping.storeToStoreDto(store), start, end);
        }

        Producer producer = producerDao.findByIco(companyDto.getIco());
        if (producer != null) {
            return this.getStatisticsForProducer(EntityAndDtoMapping.producerToProducerDto(producer), start, end);
        }
        throw new IllegalArgumentException("Company not found");
    }

    @Override
    public StatisticsDto getStatisticsForProducer(ProducerDto producerDto) {
        return this.getStatisticsForProducer(producerDto, null, null);
    }

    @Override
    public StatisticsDto getStatisticsForProducer(ProducerDto producerDto, DateTime start, DateTime end) {

        Producer producer = EntityAndDtoMapping.producerDtoToProducer(producerDto);

        StatisticsDto stats = getStatisticsForBottles(bottleDao.findByProducer(producer), start, end);

        CompanyDto statsCompany = new CompanyDto();
        statsCompany.setAddress(producerDto.getAddress());
        statsCompany.setIco(producerDto.getIco());
        statsCompany.setName(producerDto.getName());

        stats.setCompany(statsCompany);

        return stats;
    }

    @Override
    public StatisticsDto getStatisticsForStore(StoreDto producer) {
        return getStatisticsForStore(producer, null, null);
    }

    @Override
    public StatisticsDto getStatisticsForStore(StoreDto storeDto, DateTime start, DateTime end) {

        Store store = EntityAndDtoMapping.storeDtoToStore(storeDto);

        StatisticsDto stats = getStatisticsForBottles(bottleDao.findByStore(store), start, end);

        CompanyDto statsCompany = new CompanyDto();
        statsCompany.setAddress(storeDto.getAddress());
        statsCompany.setIco(storeDto.getIco());
        statsCompany.setName(storeDto.getName());

        stats.setCompany(statsCompany);

        return stats;
    }

    /**
     * Returns statistics for list of bottles. Company parameter is missing
     *
     * @param bottles
     * @param start Start date
     * @param end End date
     * @return
     */
    private StatisticsDto getStatisticsForBottles(List<Bottle> bottles, DateTime start, DateTime end) {
        long allBottles = 0;
        long toxicBottles = 0;

        // for each bottle, find test
        for (Bottle bottle : bottles) {

            // checks dates first
            if (start != null && end != null) {
                // bottle produced before start OR after end -> go to next bottle
                if (!bottle.getProducedDate().isBefore(start) && !bottle.getProducedDate().isAfter(end)) {
                } else {
                    continue;
                }
            }

            boolean toxic = false;

            List<TestResult> results = testResultDao.findByBottle(bottle);
            for (TestResult result : results) {
                // when first result is toxic -> is toxic
                if (result.isToxic()) {
                    toxic = true;
                    break;
                }
            }

            // do sums
            allBottles++;
            if (toxic) {
                toxicBottles++;
            }
        }

        double toxicPercentage = 0;
        if (allBottles != 0) {
            toxicPercentage = toxicBottles / allBottles;
        }

        // build stats object
        StatisticsDto stats = new StatisticsDto();
        stats.setAllBottlesCount(allBottles);
        stats.setToxicBottlesCount(toxicBottles);
        stats.setToxicPercentage(toxicPercentage);

        return stats;

    }
}
