package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dao.ProducerDao;
import cz.muni.fi.pa165.bottler.data.dao.StoreDao;
import cz.muni.fi.pa165.bottler.data.dto.CompanyDto;
import cz.muni.fi.pa165.bottler.data.dto.ProducerDto;
import cz.muni.fi.pa165.bottler.data.dto.StoreDto;
import cz.muni.fi.pa165.bottler.data.model.Producer;
import cz.muni.fi.pa165.bottler.data.model.Store;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of company register
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@Service
@Transactional
public class CompanyRegisterServiceImpl implements CompanyRegisterService
{
    @Autowired
    private ProducerDao producerDao;
    
    @Autowired
    private StoreDao storeDao;
    
    // <editor-fold desc="Setters" defaultstate="collapsed">
    public void setProducerDao(ProducerDao producerDao) {
        this.producerDao = producerDao;
    }

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }
    // </editor-fold>
    
    @Override
    public ProducerDto createProducer(ProducerDto producer) {
        return EntityAndDtoMapping.producerToProducerDto(producerDao.create(EntityAndDtoMapping.producerDtoToProducer(producer, true)));
    }

    @Override
    public ProducerDto updateProducer(ProducerDto producer) {
        // find current
        Producer p = producerDao.findById(producer.getId());
        p.setAddress(producer.getAddress());
        p.setIco(producer.getIco());
        p.setName(producer.getName());
        
        return EntityAndDtoMapping.producerToProducerDto(producerDao.update(p));
    }

    @Override
    public void removeProducer(ProducerDto producer) {
        // find current
        Producer p = producerDao.findById(producer.getId());
        producerDao.remove(p);
    }

    @Override
    public StoreDto createStore(StoreDto store) {
        return EntityAndDtoMapping.storeToStoreDto(storeDao.create(EntityAndDtoMapping.storeDtoToStore(store, true)));
    }

    @Override
    public StoreDto updateStore(StoreDto store) {
        // find current
        Store s = storeDao.findById(store.getId());
        s.setAddress(store.getAddress());
        s.setIco(store.getIco());
        s.setName(store.getName());
        return EntityAndDtoMapping.storeToStoreDto(storeDao.update(s));
    }

    @Override
    public void removeStore(StoreDto store) {
        // find current
        Store s = storeDao.findById(store.getId());
        storeDao.remove(s);
    }

    @Override
    public StoreDto findStoreById(long id) {
        return EntityAndDtoMapping.storeToStoreDto(storeDao.findById(id));
    }

    @Override
    public List<StoreDto> findStoreByName(String name) {
        return EntityAndDtoMapping.storeToStoreDto(storeDao.findByName(name));
    }

    @Override
    public List<StoreDto> findStoreByAddress(String address) {
        return EntityAndDtoMapping.storeToStoreDto(storeDao.findByAddress(address));

    }

    @Override
    public List<StoreDto> getAllStores() {
        return EntityAndDtoMapping.storeToStoreDto(storeDao.getAll());
    }

    @Override
    public ProducerDto findProducerByIco(int ico) {
        return EntityAndDtoMapping.producerToProducerDto(producerDao.findByIco(ico));
    }

    @Override
    public ProducerDto findProducerById(long id) {
        return EntityAndDtoMapping.producerToProducerDto(producerDao.findById(id));
    }

    @Override
    public List<ProducerDto> getAllProducers() {
        return EntityAndDtoMapping.producerToProducerDto(producerDao.getAll());
    }

    @Override
    public CompanyDto findByIco(int ico) {
        
        Producer producer = producerDao.findByIco(ico);
        if(producer != null){
            CompanyDto c = new CompanyDto();
            c.setAddress(producer.getAddress());
            c.setIco(producer.getIco());
            c.setName(producer.getName());
            return c;
        }
        
        Store store = storeDao.findByIco(ico);
        if(store != null){
            CompanyDto c = new CompanyDto();
            c.setAddress(store.getAddress());
            c.setIco(store.getIco());
            c.setName(store.getName());
            return c;
        }
        
        return null;
    }

}
