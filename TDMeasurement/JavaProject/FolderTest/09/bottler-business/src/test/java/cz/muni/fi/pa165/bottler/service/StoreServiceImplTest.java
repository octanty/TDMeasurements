package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.dao.MockFactory;
import cz.muni.fi.pa165.bottler.data.dao.BottleDao;
import cz.muni.fi.pa165.bottler.data.dao.StoreDao;
import cz.muni.fi.pa165.bottler.data.dto.BottleDto;
import cz.muni.fi.pa165.bottler.data.dto.StoreDto;
import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.Store;
import cz.muni.fi.pa165.bottler.service.EntityAndDtoMapping;
import cz.muni.fi.pa165.bottler.service.StoreServiceImpl;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Test of StoreServiceImpl using Mockito
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@RunWith(MockitoJUnitRunner.class)
public class StoreServiceImplTest  extends TestCase {

    @InjectMocks
    StoreServiceImpl storeService = new StoreServiceImpl();
    
    @Mock
    private BottleDao bottleDao;
    
    @Mock
    private StoreDao storeDao;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
   }

   
    @Test
    public void testAddBottleToStore() {
        
        final Bottle someBottle = MockFactory.on(Bottle.class).create(null);
        // no store is set when bottle created
        someBottle.setStore(null);
        final Store someStore = MockFactory.on(Store.class).create(null);
        
        BottleDto someBottleDto = EntityAndDtoMapping.bottleToBottleDto(someBottle);
        StoreDto someStoreDto = EntityAndDtoMapping.storeToStoreDto(someStore);
        
        // mocking DAO
        Mockito.when(bottleDao.findById(someBottle.getId())).thenReturn(someBottle);
        Mockito.when(storeDao.findById(someStore.getId())).thenReturn(someStore);

        // make call
        storeService.addBottleToStore(someBottleDto, someStoreDto);
        
        // verify, that update on BottleDao is called
        Mockito.verify(bottleDao).update(someBottle);
        
        // Check that bottle has the store
        Assert.assertEquals("Stores not same.", someStore, someBottle.getStore());
        
        
        
    }
    
    @Test
    public void testSellBottle()
    {
        final Bottle someBottle = MockFactory.on(Bottle.class).create(null);
        someBottle.setSold(false);
        
        BottleDto someBottleDto = EntityAndDtoMapping.bottleToBottleDto(someBottle);
        
        Mockito.when(bottleDao.findById(someBottle.getId())).thenReturn(someBottle);
        
        storeService.sellBottle(someBottleDto);
        
        assertTrue("Bottle.isSold() should return true", someBottle.isSold());
        
        
    }

}