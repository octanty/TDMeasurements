package cz.muni.fi.pa165.fleetmanagement.dao;

import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.entity.Journey;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import java.util.List;

/**
 *
 * @author Ľubomír Lacika (418110)
 * @version 1.0
 */
public interface JourneyDao {

    /**
     * Create new Journey in Database
     *
     * @param journey New Journey which we want to save into Database
     */
    void createJourney(Journey journey);

    /**
     * Delete Journey from Database
     *
     * @param journey Journey which we want delete from Database
     */
    void deleteJourney(Journey journey);

    /**
     * Update Journey in Database
     *
     * @param journey Journey which we want update
     */
    void updateJourney(Journey journey);

    /**
     * List of Journeys which are done by Employee
     *
     * @param employee Employee who has done Journeys
     */
    List<Journey> getJourneyForEmployee(Employee employee);

    /**
     * @return List of Journeys which are using Car
     *
     * @param car Car which is used on Journeys
     */
    List<Journey> getJourneyForCar(Car car);

    /**
     * @return Journey by it's id
     *
     * @param id Journey's id which we want to return
     */
    Journey getJourneyById(long id);

    /**
     * @return List of all Journeys
     */
    List<Journey> getAllJourneys();
}