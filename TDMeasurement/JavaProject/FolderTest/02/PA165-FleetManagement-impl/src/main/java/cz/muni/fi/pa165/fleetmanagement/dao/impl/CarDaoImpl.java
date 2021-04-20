package cz.muni.fi.pa165.fleetmanagement.dao.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.CarDao;
import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import cz.muni.fi.pa165.fleetmanagement.entity.ServiceInterval;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Peter Pavlik
 * @version 1.0
 */
@Repository
public class CarDaoImpl implements CarDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Create new car in Database
     *
     * @param car New Car which we want to save into Database
     */
    @Override
    public void createCar(Car car) throws DataAccessException {
        validate(car);

        if (car.getId() != null) {
            throw new IllegalArgumentException("car id is already set");
        }
        entityManager.persist(car);
    }

    /**
     * Delete car from Database
     *
     * @param car Car which we want delete from Database
     */
    @Override
    public void deleteCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("car is null");
        }

        if (car.getId() == null) {
            throw new IllegalArgumentException("car id is null");
        }

        Car deleteCar = entityManager.find(Car.class, car.getId());
        entityManager.remove(deleteCar);
    }

    /**
     * Update car in Database
     *
     * @param car Car which we want update
     */
    @Override
    public void updateCar(Car car) {
        validate(car);

        if (car.getId() == null) {
            throw new IllegalArgumentException("car id is null");
        }
        entityManager.merge(car);
        entityManager.flush();
    }

    /**
     * Return Car by his id from Database
     *
     * @param id Car's id who we want return
     */
    @Override
    public Car getCarById(long id) {

        if (id < 1) {
            throw new IllegalArgumentException("car id id less as 1");
        }

        Car car = entityManager.find(Car.class, id);

        return car;
    }

    /**
     * Return List of Cars that are avaible for Employee
     *
     * @param employee Employee whom return cars
     */
    @Override
    public List<CarDTO> getAllCarForEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("employee is null");
        }

        List<CarDTO> cars = null;

        TypedQuery<CarDTO> query = null;
        // niekto, kto bude vydiet vsetky auta a nastavovat servisne intervaly
        if (employee.getUserClass().equals(UserClassEnum.MANAGER)) {
            query = entityManager.createQuery(
                    "SELECT NEW cz.muni.fi.pa165.fleetmanagement.dto.CarDTO("
                    + "c.id, c.brand, c.model, c.color, c.licensePlate, c.engine, c.mileage, c.userClass, c.photo, (SELECT COUNT(*) FROM ServiceInterval AS si WHERE CAR_ID = c.id AND ( ((si.mileageControl IS NOT NULL AND c.mileage >= si.mileageControl AND si.completionDate IS NULL) OR (si.dateControl IS NOT NULL AND CURRENT_DATE >= si.dateControl)) AND si.completionDate IS NULL))"
                    + ") "
                    + "FROM Car AS c", CarDTO.class);
        } else {
            query = entityManager.createQuery(
                    "SELECT NEW cz.muni.fi.pa165.fleetmanagement.dto.CarDTO(c.id, c.brand, c.model, c.color, c.licensePlate, c.engine, c.mileage, c.userClass, c.photo) FROM Car AS c WHERE c.userClass = :userClass AND (SELECT COUNT(*) FROM ServiceInterval si WHERE CAR_ID = c.id AND (((si.mileageControl IS NOT NULL AND c.mileage >= si.mileageControl AND si.completionDate IS NULL) OR (si.dateControl IS NOT NULL AND CURRENT_DATE >= si.dateControl)) AND si.completionDate IS NULL)) = 0", CarDTO.class);
            query.setParameter("userClass", employee.getUserClass());
        }
        cars = query.getResultList();

        return cars;
    }

    /**
     * @return List of Cars
     */
    @Override
    public List<CarDTO> getAllCar() {
        List<CarDTO> cars = null;

        TypedQuery<CarDTO> query = null;

        query = entityManager.createQuery(
                "SELECT NEW cz.muni.fi.pa165.fleetmanagement.dto.CarDTO("
                + "c.id, c.brand, c.model, c.color, c.licensePlate, c.engine, c.mileage, c.userClass, c.photo, (SELECT COUNT(*) FROM ServiceInterval AS si WHERE CAR_ID = c.id AND ( ((si.mileageControl IS NOT NULL AND c.mileage >= si.mileageControl AND si.completionDate IS NULL) OR (si.dateControl IS NOT NULL AND CURRENT_DATE >= si.dateControl)) AND si.completionDate IS NULL))"
                + ") "
                + "FROM Car AS c", CarDTO.class);

        cars = query.getResultList();

        return cars;
    }

    /*
     * This method is abble to validate Car.
     */
    private void validate(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("car is null");
        }
        if (car.getBrand() == null) {
            throw new IllegalArgumentException("brand is null");
        }
        if (car.getModel() == null) {
            throw new IllegalArgumentException("model is null");
        }
        if (car.getColor() == null) {
            throw new IllegalArgumentException("color is null");
        }
        if (car.getMileage() < 0) {
            throw new IllegalArgumentException("mileage is less then 0");
        }
        if (car.getEngine() == null) {
            throw new IllegalArgumentException("typeOfEngine is null");
        }
        if (car.getUserClass() == null) {
            throw new IllegalArgumentException("userClass is null");
        }
        if (car.getLicensePlate() == null) {
            throw new IllegalArgumentException("vin is null");
        }
        if (car.getServiceInterval() != null) {
            for (ServiceInterval serviceInterval : car.getServiceInterval()) {
                ServiceIntervalDaoImpl.validate(serviceInterval);
            }
        }
    }
}