package cz.muni.fi.pa165.fleetmanagement.service;

import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.JourneyDTO;
import java.util.List;

/**
 *
 * @author Ján Švec (420072)
 */
public interface JourneyService {
     /**
     * Create new Journey in Database
     *
     * @param journey New Journey which we want to save into Database
     */
    public void createJourney(JourneyDTO journey);

    /**
     * Delete Journey from Database
     *
     * @param journey Journey which we want delete from Database
     */
    public void deleteJourney(JourneyDTO journey);

    /**
     * Update Journey in Database
     *
     * @param journey Journey which we want update
     */
    public void updateJourney(JourneyDTO journey);

    /**
     * List of Journeys which are done by Employee
     *
     * @param employee Employee who has done Journeys
     */
    public List<JourneyDTO> getJourneyForEmployee(EmployeeDTO employee);

    /**
     * @return List of Journeys which are using Car
     *
     * @param car Car which is used on Journeys
     */
    List<JourneyDTO> getJourneyForCar(CarDTO car);

    /**
     * @return Journey by it's id
     *
     * @param id Journey's id which we want to return
     */
    public JourneyDTO getJourneyById(long id);

    /**
     * @return List of all Journeys
     */
    public List<JourneyDTO> getAllJourneys();
}
