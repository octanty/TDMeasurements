package cz.muni.fi.pa165.fleetmanagement.dao.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.ServiceIntervalDao;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.entity.ServiceInterval;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduard Dobai
 * @version 1.0
 */
@Repository
public class ServiceIntervalDaoImpl implements ServiceIntervalDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Delete service interval from Database
     *
     * @param serviceInterval Service interval who we want delete.
     */
    @Override
    public void deleteServiceInterval(ServiceInterval serviceInterval) {
        if (serviceInterval == null) {
            throw new IllegalArgumentException("serviceInterval is null");
        }

        if (serviceInterval.getId() == null) {
            throw new IllegalArgumentException("serviceInterval id is null");
        }

        ServiceInterval deleteServiceInterval = entityManager.find(ServiceInterval.class, serviceInterval.getId());
        entityManager.remove(deleteServiceInterval);
    }

    /**
     * Update service interval in Database
     *
     * @param serviceInterval Service interval who we want update
     */
    @Override
    public void updateServiceInterval(ServiceInterval serviceInterval) {
        validate(serviceInterval);

        if (serviceInterval.getId() == null) {
            throw new IllegalArgumentException("serviceInterval id is null");
        }

        entityManager.merge(serviceInterval);
        entityManager.flush();
    }

    /**
     * Return Service interval by id from Database
     *
     * @param id Service interval's id who we want return
     */
    @Override
    public ServiceInterval getServiceIntervalById(long id) {

        ServiceInterval serviceInterval = null;
        serviceInterval = entityManager.find(ServiceInterval.class, id);

        return serviceInterval;
    }

    /**
     * Return List of Service intervals for Car
     *
     * @param car Car for we want return Service interval
     * @return List of Service intervals
     */
    @Override
    public List<ServiceInterval> getServiceIntervalForCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("car is null");
        }
        if (car.getId() == null) {
            throw new IllegalArgumentException("car id is null");
        }

        List<ServiceInterval> serviceIntervals = null;

        TypedQuery<ServiceInterval> query = entityManager.createQuery("SELECT si FROM ServiceInterval si WHERE car_id = :car_id", ServiceInterval.class);
        query.setParameter("car_id", car.getId());

        serviceIntervals = query.getResultList();

        return serviceIntervals;
    }

    /**
     * Return List of Service intervals
     *
     * @return List of Service intervals
     */
    @Override
    public List<ServiceInterval> getAllServiceInterval() {
        List<ServiceInterval> serviceIntervals = null;

        TypedQuery<ServiceInterval> query = entityManager.createQuery("SELECT si FROM ServiceInterval si", ServiceInterval.class);

        serviceIntervals = query.getResultList();

        return serviceIntervals;
    }

    /*
     * This method is abble to validate Service interval.
     */
    public static void validate(ServiceInterval serviceInterval) {
        if (serviceInterval == null) {
            throw new IllegalArgumentException("serviceInterval is null");
        }
        if (serviceInterval.getDateControl() == null && serviceInterval.getMileageControl() == null) {
            throw new IllegalArgumentException("dateControl and also mileageControl are null");
        }
        if (serviceInterval.getDateControl() != null && serviceInterval.getCompletionDate() != null) {
            if (serviceInterval.getDateControl().after(serviceInterval.getCompletionDate())) {
                throw new IllegalArgumentException("dateControl is after completionDate");
            }
        }
        if (serviceInterval.getMileageControl() != null) {
            if (serviceInterval.getMileageControl() < 1) {
                throw new IllegalArgumentException("mileageControl is less then 1");
            }
        }
        if (serviceInterval.getService() == null) {
            throw new IllegalArgumentException("service is null");
        }
    }
}