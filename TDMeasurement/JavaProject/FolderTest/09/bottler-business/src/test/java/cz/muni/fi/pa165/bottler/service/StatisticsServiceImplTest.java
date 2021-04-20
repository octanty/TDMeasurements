package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.dao.MockFactory;
import cz.muni.fi.pa165.bottler.data.dao.*;
import cz.muni.fi.pa165.bottler.data.dto.*;
import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.Liquor;
import cz.muni.fi.pa165.bottler.data.model.Producer;
import cz.muni.fi.pa165.bottler.data.model.Store;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static cz.muni.fi.pa165.bottler.service.EntityAndDtoMapping.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Test of StoreServiceImpl using Mockito
 *
 * @author Jakub Macák <374551@mail.muni.cz>
 */
@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceImplTest  extends TestCase {

    @InjectMocks
    StatisticsServiceImpl statisticsService = new StatisticsServiceImpl();

    @Mock
    private BottleDao bottleDao;
    @Mock
    private LiquorDao liquorDao;
    @Mock
    private StoreDao storeDao;
    @Mock
    private ProducerDao producerDao;
    @Mock
    private TestResultDao testResultDao;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testGetStatisticsForStore() {
        Store store = MockFactory.on(Store.class).create(null);
        Bottle bottle1 = MockFactory.on(Bottle.class).create(null);
        Bottle bottle2 = MockFactory.on(Bottle.class).create(null);

        bottle1.setStore(store);
        bottle2.setStore(store);

        ArrayList<Bottle> list = new ArrayList<>();
        list.add(bottle1);
        list.add(bottle2);

        when(bottleDao.findByStore(store)).thenReturn(list);
        assertEquals(list, bottleDao.findByStore(store));
        verify(bottleDao).findByStore(store);
    }

    @Test
    public void testGetStatisticsForProducer() {
        Producer producer = MockFactory.on(Producer.class).create(null);
        Liquor liquor = MockFactory.on(Liquor.class).create(null);
        liquor.setProducer(producer);
        Bottle bottle1 = MockFactory.on(Bottle.class).create(null);
        Bottle bottle2 = MockFactory.on(Bottle.class).create(null);

        bottle1.setLiquor(liquor);
        bottle2.setLiquor(liquor);

        ArrayList<Bottle> list = new ArrayList<>();
        list.add(bottle1);
        list.add(bottle2);

        when(bottleDao.findByProducer(producer)).thenReturn(list);
        assertEquals(list, bottleDao.findByProducer(producer));
        verify(bottleDao).findByProducer(producer);
    }

    @Test
    public void testGetStatisticsForCompanyMappedAsStore() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setIco(123);
        Store store = MockFactory.on(Store.class).create(null);
        store.setIco(123);
        when(storeDao.findByIco(123)).thenReturn(store);

        StoreDto storeDto = storeToStoreDto(store);
        assertSame(storeDto.getIco(), companyDto.getIco());

        StatisticsDto stats1 = statisticsService.getStatistics(companyDto);

        Bottle bottle1 = MockFactory.on(Bottle.class).create(null);
        Bottle bottle2 = MockFactory.on(Bottle.class).create(null);
        bottle1.setStore(store);
        bottle2.setStore(store);
        ArrayList<Bottle> list = new ArrayList<>();
        list.add(bottle1);
        list.add(bottle2);

        when(bottleDao.findByStore(store)).thenReturn(list);
        StatisticsDto stats2 = statisticsService.getStatisticsForStore(storeDto);

        assertEquals(stats1, stats2);
    }

    @Test
    public void testGetStatisticsForCompanyMappedAsProducer() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setIco(12);
        Producer producer = MockFactory.on(Producer.class).create(null);
        producer.setIco(12);
        when(producerDao.findByIco(12)).thenReturn(producer);

        ProducerDto producerDto = producerToProducerDto(producer);
        assertSame(producer.getIco(), companyDto.getIco());

        StatisticsDto stats1 = statisticsService.getStatistics(companyDto);

        Bottle bottle1 = MockFactory.on(Bottle.class).create(null);
        Bottle bottle2 = MockFactory.on(Bottle.class).create(null);

        Liquor liquor = MockFactory.on(Liquor.class).create(null);
        liquor.setProducer(producer);

        bottle1.setLiquor(liquor);
        bottle2.setLiquor(liquor);

        assertEquals(bottle1.getLiquor(), bottle2.getLiquor());
        assertEquals(bottle1.getLiquor().getProducer(), bottle2.getLiquor().getProducer());

        ArrayList<Bottle> list = new ArrayList<>();
        list.add(bottle1);
        list.add(bottle2);

        when(bottleDao.findByProducer(producer)).thenReturn(list);
        StatisticsDto stats2 = statisticsService.getStatisticsForProducer(producerDto);

        assertEquals(stats1, stats2);
    }
}