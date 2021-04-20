package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dao.ProducerDao;
import cz.muni.fi.pa165.bottler.data.dao.StoreDao;
import cz.muni.fi.pa165.bottler.data.dto.CompanyDto;
import cz.muni.fi.pa165.bottler.data.dto.ProducerDto;
import cz.muni.fi.pa165.bottler.data.dto.StoreDto;
import cz.muni.fi.pa165.bottler.data.model.Producer;
import cz.muni.fi.pa165.bottler.data.model.Store;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Josef Ludvicek <374268@mail.muni.cz>
 */

@RunWith(MockitoJUnitRunner.class)
public class CompanyRegisterServiceImplTest {


    @InjectMocks
    CompanyRegisterServiceImpl companyRegisterService = new CompanyRegisterServiceImpl();


    @Mock
    private ProducerDao producerDao;

    @Mock
    private StoreDao storeDao;


    /**
     * valid producer dto
     */
    ProducerDto producerDto;
    /**
     * dao store dto
     */
    StoreDto storeDto;

    Long testId;

    /**
     * store attributes capturing
     */
    ArgumentCaptor<Store> storeArgument;



    /**
     * Helper method that creates new Producer instance from dto
     *
     * @param dto producer dto
     * @return producer instance with same attributes
     */
    private Producer producerFromDto(ProducerDto dto) {
        Producer p = new Producer();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setAddress(dto.getAddress());
        p.setIco(dto.getIco());
        return p;
    }

    /**
     * Helper method that creates new Store instance from dto
     *
     * @param dto store dto
     * @return producer instance with same attributes
     */
    private Store storeFromDto(StoreDto dto) {
        Store store = new Store();
        store.setId(dto.getId());
        store.setName(dto.getName());
        store.setAddress(dto.getAddress());
        store.setIco(dto.getIco());
        return store;
    }


    @Before
    public void setUp() throws Exception {

        producerDto = new ProducerDto();
        producerDto.setAddress("under the hood");
        producerDto.setName("john");
        producerDto.setIco(123456);

        storeDto = new StoreDto();
        storeDto.setName("store 1");
        storeDto.setAddress("addres 333");
        storeDto.setIco(778899);

        testId = new Long(999);


        storeArgument = ArgumentCaptor.forClass(Store.class);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testCreateProducerDTOtoEntityMapping() throws Exception {
        companyRegisterService.createProducer(producerDto);

        ArgumentCaptor<Producer> arguments = ArgumentCaptor.forClass(Producer.class);
        verify(producerDao).create(arguments.capture());

        assertEquals("compare after DTO -> ENTITY mapping: names", producerDto.getName(), arguments.getValue().getName());
        assertEquals("compare after DTO -> ENTITY mapping: address", producerDto.getAddress(), arguments.getValue().getAddress());
        assertEquals("compare after DTO -> ENTITY mapping: ico", producerDto.getIco(), arguments.getValue().getIco());
        assertEquals("compare after DTO -> ENTITY mapping: id", producerDto.getId(), arguments.getValue().getId());
    }

    @Test
    public void testCreateProducerEntityToDTOMapping() throws Exception {


        final Long id = new Long(666);
        when(producerDao.create(any(Producer.class))).thenAnswer(
                new Answer<Producer>() {
                    @Override
                    public Producer answer(InvocationOnMock invocationOnMock) throws Throwable {
                        Producer p = producerFromDto(producerDto);
                        p.setId(id);
                        return p;
                    }
                }

        );
        ProducerDto result = companyRegisterService.createProducer(producerDto);

        assertEquals("Entity to DTO mapping: ", id, result.getId());
        assertEquals("Entity to DTO mapping: ", producerDto.getAddress(), result.getAddress());
        assertEquals("Entity to DTO mapping: ", producerDto.getName(), result.getName());
        assertEquals("Entity to DTO mapping: ", producerDto.getIco(), result.getIco());
    }

    @Test
    public void testUpdateProducer() throws Exception {

        final Long id = new Long(654);
        producerDto.setId(id);

        final String newName = "Josef Ludvicek";
        final String newAddres = "Brnoooo";
        final Integer newIco = 1;

        //mock find by id
        when(producerDao.findById(id)).thenReturn(producerFromDto(producerDto));

        ArgumentCaptor<Producer> argument = ArgumentCaptor.forClass(Producer.class);

        //backup producer :)
        Producer originalProducer = producerFromDto(producerDto);

        producerDto.setIco(newIco);
        producerDto.setAddress(newAddres);
        producerDto.setName(newName);

        Producer changedProducer = producerFromDto(producerDto);

        //mock update dao method
        when(producerDao.update(any(Producer.class))).thenReturn(changedProducer);


        ProducerDto updated = companyRegisterService.updateProducer(producerDto);


        assertEquals("returned correct DTO attributes?", changedProducer.getIco(), updated.getIco());
        assertEquals("returned correct DTO attributes?", changedProducer.getId(), updated.getId());
        assertEquals("returned correct DTO attributes?", changedProducer.getName(), updated.getName());
        assertEquals("returned correct DTO attributes?", changedProducer.getAddress(), updated.getAddress());

        verify(producerDao, times(1)).update(argument.capture());

        assertEquals("update with correct arguments after DTO -> Entity?", newAddres, argument.getValue().getAddress());
        assertEquals("update with correct arguments after DTO -> Entity?", newIco, argument.getValue().getIco());
        assertEquals("update with correct arguments after DTO -> Entity?", newName, argument.getValue().getName());
        assertEquals("update with correct arguments after DTO -> Entity?", id, argument.getValue().getId());
    }

    @Test
    public void testRemoveProducer() throws Exception {
        final Long id = new Long(111);
        producerDto.setId(id);


        when(producerDao.findById(id)).thenAnswer(new Answer<Producer>() {
            @Override
            public Producer answer(InvocationOnMock invocationOnMock) throws Throwable {
                Producer p = producerFromDto(producerDto);
                p.setId(id);
                return p;
            }
        });
        companyRegisterService.removeProducer(producerDto);

        ArgumentCaptor<Producer> arguments = ArgumentCaptor.forClass(Producer.class);
        verify(producerDao, times(1)).findById(id);
        verify(producerDao, times(1)).remove(arguments.capture());

        assertEquals("compare after DTO -> ENTITY mapping: id", id, arguments.getValue().getId());
    }

    @Test
    public void testCreateStore() throws Exception {
        final Long id = new Long(999);

        when(storeDao.create(any(Store.class))).thenAnswer(new Answer<Store>() {
            @Override
            public Store answer(InvocationOnMock invocationOnMock) throws Throwable {
                Store s = storeFromDto(storeDto);
                s.setId(id);
                return s;
            }
        });

        StoreDto created = companyRegisterService.createStore(storeDto);


        ArgumentCaptor<Store> argument = ArgumentCaptor.forClass(Store.class);

        verify(storeDao, times(1)).create(argument.capture());

        assertEquals("proper DTO->Entity mapping for store?", storeDto.getAddress(), argument.getValue().getAddress());
        assertEquals("proper DTO->Entity mapping for store?", storeDto.getIco(), argument.getValue().getIco());
        assertEquals("proper DTO->Entity mapping for store?", storeDto.getName(), argument.getValue().getName());
        assertEquals("proper DTO->Entity mapping for store?", storeDto.getId(), argument.getValue().getId());

        assertEquals("create result?", storeDto.getAddress(), created.getAddress());
        assertEquals("create result?", storeDto.getIco(), created.getIco());
        assertEquals("create result?", storeDto.getName(), created.getName());
        assertEquals("create result?", id, created.getId());


    }

    @Test
    public void testUpdateStore() throws Exception {
        final Long id = new Long(654);
        storeDto.setId(id);

        final String newName = "Josef Ludvicek";
        final String newAddres = "Brnoooo";
        final Integer newIco = 1;

        //mock find by id
        when(storeDao.findById(id)).thenReturn(storeFromDto(storeDto));


        //backup store :)
        Store originalStore = storeFromDto(storeDto);

        storeDto.setIco(newIco);
        storeDto.setAddress(newAddres);
        storeDto.setName(newName);

        Store changedStore = storeFromDto(storeDto);

        //mock update dao method
        when(storeDao.update(any(Store.class))).thenReturn(changedStore);


        StoreDto updated = companyRegisterService.updateStore(storeDto);


        assertEquals("returned correct DTO attributes?", changedStore.getIco(), updated.getIco());
        assertEquals("returned correct DTO attributes?", changedStore.getId(), updated.getId());
        assertEquals("returned correct DTO attributes?", changedStore.getName(), updated.getName());
        assertEquals("returned correct DTO attributes?", changedStore.getAddress(), updated.getAddress());

        verify(storeDao, times(1)).update(storeArgument.capture());

        assertEquals("update with correct arguments after DTO -> Entity?", newAddres, storeArgument.getValue().getAddress());
        assertEquals("update with correct arguments after DTO -> Entity?", newIco, storeArgument.getValue().getIco());
        assertEquals("update with correct arguments after DTO -> Entity?", newName, storeArgument.getValue().getName());
        assertEquals("update with correct arguments after DTO -> Entity?", id, storeArgument.getValue().getId());
    }

    @Test
    public void testRemoveStore() throws Exception {

        //need dto with id
        storeDto.setId(testId);

        //mock find store by testId
        when(storeDao.findById(testId)).thenReturn(storeFromDto(storeDto));

        companyRegisterService.removeStore(storeDto);

        //force exactly one db lookup
        verify(storeDao, times(1)).findById(anyLong());

        verify(storeDao, times(1)).remove(storeArgument.capture());
        assertEquals("equality of id between DTO on service layer and DAO call", testId, storeArgument.getValue().getId());
    }

    @Test
    public void testFindStoreById() throws Exception {
        storeDto.setId(testId);
        when(storeDao.findById(testId)).thenReturn(storeFromDto(storeDto));
        StoreDto found = companyRegisterService.findStoreById(testId);

        assertEquals(storeDto.getId(), found.getId());
        verify(storeDao, times(1)).findById(testId);

        assertEquals("returned correct DTO attributes?", storeDto.getIco(), found.getIco());
        assertEquals("returned correct DTO attributes?", storeDto.getId(), found.getId());
        assertEquals("returned correct DTO attributes?", storeDto.getName(), found.getName());
        assertEquals("returned correct DTO attributes?", storeDto.getAddress(), found.getAddress());
    }

    @Test
    public void testFindStoreByAddress() throws Exception {
        final String address = "asdfasdfa6655, 10";

        final List<Store> storeList = new ArrayList<Store>();
        for (int i = 0; i < 10; i++) {
            Store s = new Store();
            s.setName("Jozin " + i);
            s.setId(new Long(i * 23));
            s.setAddress(address);
            s.setIco(i * 36);
            storeList.add(s);
        }

        when(storeDao.findByName(address)).thenReturn(storeList);

        List<StoreDto> found = companyRegisterService.findStoreByName(address);
        assertEquals("right count of found objects", storeList.size(), found.size());

        for (int i = 0; i < storeList.size(); i++) {
            assertEquals("right order?", storeList.get(i).getId(), found.get(i).getId());
        }

    }

    @Test
    public void testGetAllStores() throws Exception {
        final List<Store> storeList = new ArrayList<Store>();
        for (int i = 0; i < 10; i++) {
            Store s = new Store();
            s.setName("Jozin " + i);
            s.setId(new Long(i * 23));
            s.setAddress("address " + i);
            s.setIco(i * 36);
            storeList.add(s);
        }
        when(storeDao.getAll()).thenReturn(storeList);

        List<StoreDto> found = companyRegisterService.getAllStores();
        assertEquals("right count of found objects", storeList.size(), found.size());

    }

    @Test
    public void testFindProducerByIco() throws Exception {
        producerDto.setId(testId);

        when(producerDao.findByIco(producerDto.getIco())).thenReturn(producerFromDto(producerDto));

        ProducerDto found = companyRegisterService.findProducerByIco(producerDto.getIco());
        assertEquals("compare DTO's", producerDto, found);
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(producerDao, times(1)).findByIco(argument.capture());
        assertEquals("really searching for right ico?", producerDto.getIco(), argument.getValue());
    }

    @Test
    public void testFindProducerById() throws Exception {
        producerDto.setId(testId);
        when(producerDao.findById(testId)).thenReturn(producerFromDto(producerDto));
        ProducerDto found = companyRegisterService.findProducerById(testId);

        assertEquals(producerDto.getId(), found.getId());
        verify(producerDao, times(1)).findById(testId);

        assertEquals("returned correct DTO attributes?", producerDto.getIco(), found.getIco());
        assertEquals("returned correct DTO attributes?", producerDto.getId(), found.getId());
        assertEquals("returned correct DTO attributes?", producerDto.getName(), found.getName());
        assertEquals("returned correct DTO attributes?", producerDto.getAddress(), found.getAddress());
    }

    @Test
    public void testGetAllProducers() throws Exception {
        final List<Producer> producerList = new ArrayList<Producer>();
        for (int i = 0; i < 10; i++) {
            Producer s = new Producer();
            s.setName("Jozin " + i);
            s.setId(new Long(i * 23));
            s.setAddress("address " + i);
            s.setIco(i * 36);
            producerList.add(s);
        }
        when(producerDao.getAll()).thenReturn(producerList);

        List<ProducerDto> found = companyRegisterService.getAllProducers();
        assertEquals("right count of found objects", producerList.size(), found.size());

    }

    @Test
    public void testFindByIco() throws Exception {

        storeDto.setId(testId);
        producerDto.setId(new Long(1122112211));

        when(producerDao.findByIco(producerDto.getIco())).thenReturn(producerFromDto(producerDto));
        when(storeDao.findByIco(storeDto.getIco())).thenReturn(storeFromDto(storeDto));

        CompanyDto found = companyRegisterService.findByIco(storeDto.getIco());
        assertEquals("found right company?", (long) storeDto.getIco(), (long) found.getIco());
        assertNotSame("shouldnt find this", producerDto, found);
    }

}
