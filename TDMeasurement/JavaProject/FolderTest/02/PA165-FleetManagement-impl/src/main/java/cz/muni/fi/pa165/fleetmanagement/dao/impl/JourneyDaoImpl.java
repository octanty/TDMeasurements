package cz.muni.fi.pa165.fleetmanagement.dao.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.JourneyDao;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.entity.Journey;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ľubomír Lacika
 * @version 1.0
 */
@Repository
public class JourneyDaoImpl implements JourneyDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Add Journey to Database
     *
     * @param journey
     */
    @Override
    public void createJourney(Journey journey) throws DataAccessException {
        validate(journey);
        if (journey.getId() != null) {
            throw new IllegalArgumentException("journey id is already set");
        }
        entityManager.persist(journey);
    }

    /**
     * Delete Journey from Database
     *
     * @param journey
     */
    @Override
    public void deleteJourney(Journey journey) {
        if (journey == null) {
            throw new IllegalArgumentException("Journey is null");
        }
        if (journey.getId() == null) {
            throw new IllegalArgumentException("Journey id is null");
        }
        Journey deleteJourney = entityManager.find(Journey.class, journey.getId());
        entityManager.remove(deleteJourney);
    }

    /**
     * Update Journey in Database
     *
     * @param journey
     */
    @Override
    public void updateJourney(Journey journey) {
        validate(journey);
        if (journey.getId() == null) {
            throw new IllegalArgumentException("journey id is null");
        }

        entityManager.merge(journey);
        entityManager.flush();
    }

    /**
     * @return List of Journeys for Employee
     * @param employee
     */
    @Override
    public List<Journey> getJourneyForEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("employee is null");
        }
        List<Journey> journeys = null;
        TypedQuery query = null;
        query = entityManager.createQuery("SELECT j FROM Journey j WHERE j.employee = " + employee.getId(), Journey.class);
        journeys = query.getResultList();

        return journeys;
    }

    /**
     * @return List of Journeys for Car
     * @param car
     */
    @Override
    public List<Journey> getJourneyForCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("car is null");
        }
        List<Journey> journeys = null;
        TypedQuery query = null;
        query = entityManager.createQuery("SELECT j FROM Journey j WHERE j.car = " + car.getId(), Journey.class);
        journeys = query.getResultList();

        return journeys;
    }

    /**
     * @return Journey by id
     * @param id
     */
    @Override
    public Journey getJourneyById(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Id of journey is less than 1");
        }
        Journey journey = entityManager.find(Journey.class, id);
        return journey;
    }

    /**
     *
     * @return List of all Journeys
     */
    @Override
    public List<Journey> getAllJourneys() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Journey> cq = cb.createQuery(Journey.class);
        Root<Journey> employee = cq.from(Journey.class);
        cq.select(employee);
        TypedQuery<Journey> q = entityManager.createQuery(cq);
        List<Journey> allJourneys = q.getResultList();
        return allJourneys;
    }

    /**
     * Validates journey
     *
     * @param journey
     */
    private void validate(Journey journey) {
        if (journey == null) {
            throw new IllegalArgumentException("journey is null");
        }
        if (journey.getCar() == null) {
            throw new IllegalArgumentException("car is null");
        }
        if (journey.getEmployee() == null) {
            throw new IllegalArgumentException("employee is null");
        }
        if (journey.getMileage() < 0) {
            throw new IllegalArgumentException("mileage is less then 0");
        }
        if (journey.getPickUpDate() == null) {
            throw new IllegalArgumentException("pickUpDate is null");
        }
        if (journey.getReturnDate() == null) {
            throw new IllegalArgumentException("returnDate is null");
        }
    }
}