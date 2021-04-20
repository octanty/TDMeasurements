package cz.muni.fi.pa165.fleetmanagement.service;

import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import java.util.List;

/**
 *
 * @author Peter Pavlik
 */
public interface CarService {

    /**
     * Create new car in Database
     *
     * @param car New Car which we want save into Database
     */
    void createCar(CarDTO car);

    /**
     * Delete car from Database
     *
     * @param car Car which we want to delete from Database
     */
    void deleteCar(CarDTO car);

    /**
     * Update car in Database
     *
     * @param car Car which we want to update
     */
    void updateCar(CarDTO car);

    /**
     * @return Car by his id from Database
     * @param id Car's id which we want to return
     */
    CarDTO getCarById(long id);

    /**
     * @return List of Cars that are avilable for Employee
     * @param employee Employee whom return cars
     */
    List<CarDTO> getAllCarForEmployee(EmployeeDTO employee);
    
    /**
     * @return List of Cars 
     */
    List<CarDTO> getAllCar();
}
