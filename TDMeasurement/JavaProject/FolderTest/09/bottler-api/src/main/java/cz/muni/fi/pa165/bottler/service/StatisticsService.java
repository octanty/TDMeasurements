package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dto.CompanyDto;
import cz.muni.fi.pa165.bottler.data.dto.ProducerDto;
import cz.muni.fi.pa165.bottler.data.dto.StatisticsDto;
import cz.muni.fi.pa165.bottler.data.dto.StoreDto;
import org.joda.time.DateTime;

/**
 * Service for generating statistics
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
  */
public interface StatisticsService {

    /**
     * Returns statistics for a Company (ICO is required)
     * 
     * @param company CompanyDto - ICO is required
     * @return Statistics object
     */
    public StatisticsDto getStatistics(CompanyDto company);

    /**
     * Returns statistics of a bottles made by a Producer
     *
     * @param producer Producer
     * @return Statistics object
     */
    public StatisticsDto getStatisticsForProducer(ProducerDto producer);

    /**
     * Returns statistics of a bottles which are or were in a store
     * 
     * @param store Store
     * @return Statistics object
     */
    public StatisticsDto getStatisticsForStore(StoreDto store);
    
    /**
     * Returns statistics of a bottles produced between specified dates for a Company (ICO is required)
     * 
     * @param company - ICO is required
     * @param start Start date
     * @param end End date
     * @return Statistics object
     */
    public StatisticsDto getStatistics(CompanyDto company, DateTime start, DateTime end);

    /**
     * Returns statistics of a bottles produced between specified dates by a Producer
     *
     * @param producer Producer
     * @param start Start date
     * @param end End date
     * @return Statistics object
     */
    public StatisticsDto getStatisticsForProducer(ProducerDto producer, DateTime start, DateTime end);

    /**
     * Returns statistics of a bottles produced between specified dates in a Store
     * 
     * @param store Store
     * @param start Start date
     * @param end End date
     * @return Statistics object
     */
    public StatisticsDto getStatisticsForStore(StoreDto store, DateTime start, DateTime end);
    
}
