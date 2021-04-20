package cz.muni.fi.pa165.fleetmanagement.dao;

import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import java.util.List;

/**
 *
 * @author Ján Švec (420072)
 * @version 1.0
 */
public interface CarDao {

    /**
     * Create new car in Database
     *
     * @param car New Car which we want save into Database
     */
    void createCar(Car car);

    /**
     * Delete car from Database
     *
     * @param car Car which we want to delete from Database
     */
    void deleteCar(Car car);

    /**
     * Update car in Database
     *
     * @param car Car which we want to update
     */
    void updateCar(Car car);

    /**
     * @return Car by his id from Database
     * @param id Car's id which we want to return
     */
    Car getCarById(long id);

    /**
     * @return List of Cars that are avilable for Employee
     * @param employee Employee whom return cars
     */
    List<CarDTO> getAllCarForEmployee(Employee employee);

    /**
     * @return List of Cars
     */
    List<CarDTO> getAllCar();
}