package cz.muni.fi.pa165.fleetmanagement.service;

import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.ServiceIntervalDTO;
import java.util.List;

/**
 *
 * @author Eduard Dobai (420065)
 */
public interface ServiceIntervalService {

    /**
     * Delete service interval from Database
     *
     * @param serviceInterval Service interval who we want delete.
     */
    public void deleteServiceInterval(ServiceIntervalDTO serviceInterval);

    /**
     * Update service interval in Database
     *
     * @param serviceInterval Service interval who we want update
     */
    public void updateServiceInterval(ServiceIntervalDTO serviceInterval);

    /**
     * Return Service interval by id from Database
     *
     * @param id Service interval's id who we want return
     */
    public ServiceIntervalDTO getServiceIntervalById(long id);

    /**
     * Return List of Service intervals for Car
     *
     * @param car Car for we want return Service interval
     * @return List of Service intervals
     */
    public List<ServiceIntervalDTO> getServiceIntervalForCar(CarDTO car);

    /**
     * Return List of Service intervals
     *
     * @return List of Service intervals
     */
    public List<ServiceIntervalDTO> getAllServiceInterval();
}
