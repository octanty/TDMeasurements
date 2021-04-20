package cz.muni.fi.pa165.fleetmanagement.service.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.ServiceIntervalDao;
import cz.muni.fi.pa165.fleetmanagement.dao.impl.ServiceIntervalDaoImpl;
import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.ServiceIntervalDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.ServiceInterval;
import cz.muni.fi.pa165.fleetmanagement.exception.ServiceDataAccesException;
import cz.muni.fi.pa165.fleetmanagement.service.ServiceIntervalService;
import cz.muni.fi.pa165.fleetmanagement.utils.EntityToDTOConvertor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eduard Dobai
 */
@Service
public class ServiceIntervalServiceImpl implements ServiceIntervalService {

    @Inject
    private ServiceIntervalDao serviceIntervalDao;

    public ServiceIntervalDao getServiceDao() {
        return serviceIntervalDao;
    }

    public void setServiceDao(ServiceIntervalDao serviceIntervalDao) {
        this.serviceIntervalDao = serviceIntervalDao;
    }

    @Transactional
    @Override
    public void deleteServiceInterval(ServiceIntervalDTO serviceInterval) {
        try {
            serviceIntervalDao.deleteServiceInterval(EntityToDTOConvertor.toEntity(serviceInterval));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ServiceIntervalDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public void updateServiceInterval(ServiceIntervalDTO serviceInterval) {
        try {
            serviceIntervalDao.updateServiceInterval(EntityToDTOConvertor.toEntity(serviceInterval));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ServiceIntervalDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public ServiceIntervalDTO getServiceIntervalById(long id) {
        try {
            ServiceInterval serviceInterval = serviceIntervalDao.getServiceIntervalById(id);
            return EntityToDTOConvertor.toDTO(serviceInterval);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ServiceIntervalDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public List<ServiceIntervalDTO> getServiceIntervalForCar(CarDTO car) {
        try {
            List<ServiceInterval> services = serviceIntervalDao.getServiceIntervalForCar(EntityToDTOConvertor.toEntity(car));
            List<ServiceIntervalDTO> dtos = new ArrayList<ServiceIntervalDTO>();
            for (ServiceInterval s : services) {

                dtos.add(EntityToDTOConvertor.toDTO(s));
            }
            return dtos;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ServiceIntervalDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public List<ServiceIntervalDTO> getAllServiceInterval() {
        try {
            List<ServiceInterval> services = serviceIntervalDao.getAllServiceInterval();
            List<ServiceIntervalDTO> dtos = new ArrayList<ServiceIntervalDTO>();
            for (ServiceInterval s : services) {

                dtos.add(EntityToDTOConvertor.toDTO(s));
            }
            return dtos;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ServiceIntervalDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }
}
