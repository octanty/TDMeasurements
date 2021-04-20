package cz.muni.fi.pa165.fleetmanagement.dao;

import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.entity.ServiceInterval;
import java.util.List;

/**
 *
 * @author Peter Pavlík (418125)
 * @version 1.0
 */
public interface ServiceIntervalDao {

    /**
     * Delete service interval from Database
     *
     * @param serviceInterval Service interval who we want delete.
     */
    void deleteServiceInterval(ServiceInterval serviceInterval);

    /**
     * Update service interval in Database
     *
     * @param serviceInterval Service interval who we want update
     */
    void updateServiceInterval(ServiceInterval serviceInterval);

    /**
     * Return Service interval by id from Database
     *
     * @param id Service interval's id who we want return
     */
    ServiceInterval getServiceIntervalById(long id);

    /**
     * Return List of Service intervals for Car
     *
     * @param car Car for we want return Service interval
     * @return List of Service intervals
     */
    List<ServiceInterval> getServiceIntervalForCar(Car car);

    /**
     * Return List of Service intervals
     *
     * @return List of Service intervals
     */
    public List<ServiceInterval> getAllServiceInterval();
}