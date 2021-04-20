package cz.muni.fi.pa165.fleetmanagement.service.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.CarDao;
import cz.muni.fi.pa165.fleetmanagement.dao.impl.CarDaoImpl;
import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.exception.ServiceDataAccesException;
import cz.muni.fi.pa165.fleetmanagement.service.CarService;
import cz.muni.fi.pa165.fleetmanagement.utils.EntityToDTOConvertor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Peter Pavlik
 */
@Service
public class CarServiceImpl implements CarService {

    @Inject
    private CarDao carDao;

    public CarDao getCarDao() {
        return carDao;
    }

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    @Transactional
    @Override
    public void createCar(CarDTO carDTO) {
        try {
            Car car = EntityToDTOConvertor.toEntity(carDTO);
            carDao.createCar(car);
            carDTO.setId(car.getId());
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public void deleteCar(CarDTO car) {

        try {
            carDao.deleteCar(EntityToDTOConvertor.toEntity(car));
        } catch (Exception ex) {
            Logger.getLogger(CarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public void updateCar(CarDTO car) {
        try {
            carDao.updateCar(EntityToDTOConvertor.toEntity(car));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public CarDTO getCarById(long id) {
        try {
            return EntityToDTOConvertor.toDto(carDao.getCarById(id));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public List<CarDTO> getAllCarForEmployee(EmployeeDTO employee) {
        try {
            return carDao.getAllCarForEmployee(EntityToDTOConvertor.toEntity(employee));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public List<CarDTO> getAllCar() {
        try {
            return carDao.getAllCar();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }
}
