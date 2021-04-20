/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.dao.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.CarDao;
import cz.muni.fi.pa165.fleetmanagement.dao.EmployeeDao;
import cz.muni.fi.pa165.fleetmanagement.dao.JourneyDao;
import static cz.muni.fi.pa165.fleetmanagement.dao.impl.CarDaoImplTest.newCar;
import static cz.muni.fi.pa165.fleetmanagement.dao.impl.EmployeeDaoImplTest.newEmployee;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import cz.muni.fi.pa165.fleetmanagement.enums.GenderEnum;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import cz.muni.fi.pa165.fleetmanagement.entity.Journey;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.junit.After;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ján Švec
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class JourneyDaoImplTest {

    @Autowired
    JourneyDao journeyDao;
    @Autowired
    CarDao carDao;
    @Autowired
    EmployeeDao employeeDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of createJourney method, of class JourneyDaoImpl.
     */
    @Test
    public void testCreateJourney() {
        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx4gf56", null);
        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);
        Date pickUpDate = new java.util.Date(2013 - 1900, 4, 23);
        Date returnUpDate = new java.util.Date(2013 - 1900, 4, 26);
        Journey journey = newJourney(car, employee, 200, pickUpDate, returnUpDate);

        initializeJourney(car, employee, journey);

        long journeyId = journey.getId();
        assertNotNull(journeyId);
        Journey result = journeyDao.getJourneyById(journeyId);

        System.err.println(result.getId() + " - " + journey.getId());
        System.err.println(result.getCar() + " - " + journey.getCar());
        System.err.println(result.getEmployee() + " - " + journey.getEmployee());
        System.err.println(result.getMileage() + " - " + journey.getMileage());
        System.err.println(result.getPickUpDate() + " - " + journey.getPickUpDate());
        System.err.println(result.getReturnDate() + " - " + journey.getReturnDate());

        assertEquals(journey, result);
        //assertNotSame(journey, result);
        assertDeepEquals(journey, result);
    }

    @Test
    public void testCreateJourneyWithWrongAttributes() {
        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx4gf56", null);
        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);
        Date pickUpDate = new java.util.Date(2013 - 1900, 4, 23);
        Date returnUpDate = new java.util.Date(2013 - 1900, 4, 26);
        Journey journey = newJourney(car, employee, 200, pickUpDate, returnUpDate);
        initializeJourney(car, employee, journey);
        try {
            journeyDao.createJourney(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //ok
        }

        try {
            journey.setId(1L);
            journeyDao.createJourney(journey);
            fail();
        } catch (IllegalArgumentException ex) {
            //ok
        }
    }

    /**
     * Test of deleteJourney method, of class JourneyDaoImpl.
     */
    @Test
    public void testDeleteJourney() {
        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx4gf56", null);
        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);
        Date pickUpDate = new java.util.Date(2013 - 1900, 4, 23);
        Date returnUpDate = new java.util.Date(2013 - 1900, 4, 26);
        Journey journey = newJourney(car, employee, 200, pickUpDate, returnUpDate);

        Car car2 = newCar("SKODA", "Superb", "white", 5600, null, "2.0 TDI", UserClassEnum.CEO, "hghgf8hff6", null);
        Employee employee2 = newEmployee("Martin", "Petovsky", "email@google.com", GenderEnum.MALE, "4569877", UserClassEnum.CEO);
        Date pickUpDate2 = new java.util.Date(2014 - 1900, 5, 24);
        Date returnUpDate2 = new java.util.Date(2014 - 1900, 5, 28);
        Journey journey1 = newJourney(car2, employee2, 300, pickUpDate2, returnUpDate2);

        initializeJourney(car, car2, employee, employee2, journey, journey1);
        journeyDao.deleteJourney(journey);

        assertNull(journeyDao.getJourneyById(journey.getId()));
        assertNotNull(journeyDao.getJourneyById(journey1.getId()));
    }

    @Test
    public void testDeleteJourneyWithWrongAttributes() {
        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx4gf56", null);
        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);
        Date pickUpDate = new java.util.Date(2013 - 1900, 4, 23);
        Date returnUpDate = new java.util.Date(2013 - 1900, 4, 26);
        Journey journey = newJourney(car, employee, 200, pickUpDate, returnUpDate);
        initializeJourney(car, employee, journey);
        Long journeyID = journey.getId();

        try {
            journeyDao.deleteJourney(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            journey = journeyDao.getJourneyById(journeyID);
            journey.setId(null);
            journeyDao.deleteJourney(journey);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of updateJourney method, of class JourneyDaoImpl.
     */
    @Test
    public void testUpdateJourney() {
        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx4gf56", null);
        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);
        Date pickUpDate = new java.util.Date(2013 - 1900, 4, 23);
        Date returnUpDate = new java.util.Date(2013 - 1900, 4, 26);
        Journey journey = newJourney(car, employee, 200, pickUpDate, returnUpDate);

        Car car2 = newCar("SKODA", "Superb", "white", 5600, null, "2.0 TDI", UserClassEnum.CEO, "hghgf8hff6", null);
        Employee employee2 = newEmployee("Martin", "Petovsky", "email@google.com", GenderEnum.MALE, "4569877", UserClassEnum.CEO);
        Date pickUpDate2 = new java.util.Date(2014 - 1900, 5, 24);
        Date returnUpDate2 = new java.util.Date(2014 - 1900, 5, 28);
        Journey journey1 = newJourney(car2, employee2, 300, pickUpDate2, returnUpDate2);

        initializeJourney(car, car2, employee, employee2, journey, journey1);

        Long journeyId = journey.getId();

        journey = journeyDao.getJourneyById(journeyId);
        journeyDao.updateJourney(journey);
        assertEquals(car, journey.getCar());
        assertEquals(employee, journey.getEmployee());
        assertEquals(200, journey.getMileage());
        assertEquals(pickUpDate, journey.getPickUpDate());
        assertEquals(returnUpDate, journey.getReturnDate());

        journey = journeyDao.getJourneyById(journeyId);
        journey.setCar(car2);
        journey.setEmployee(employee2);
        journey.setMileage(300);
        journey.setPickUpDate(pickUpDate2);
        journey.setReturnDate(returnUpDate2);
        journeyDao.updateJourney(journey);
        assertEquals(car2, journey.getCar());
        assertEquals(employee2, journey.getEmployee());
        assertEquals(300, journey.getMileage());
        assertEquals(pickUpDate2, journey.getPickUpDate());
        assertEquals(returnUpDate2, journey.getReturnDate());

        assertDeepEquals(journey1, journeyDao.getJourneyById(journey1.getId()));
    }

    @Test
    public void testUpdateJourneyWithWrongAttributes() {
        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx4gf56", null);
        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);
        Date pickUpDate = new java.util.Date(2013 - 1900, 4, 23);
        Date returnUpDate = new java.util.Date(2013 - 1900, 4, 26);
        Journey journey = newJourney(car, employee, 200, pickUpDate, returnUpDate);
        initializeJourney(car, employee, journey);
        Long journeyID = journey.getId();

        try {
            journeyDao.updateJourney(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            journey = journeyDao.getJourneyById(journeyID);
            journey.setId(null);
            journeyDao.updateJourney(journey);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of getJourneyForEmployee method, of class JourneyDaoImpl.
     */
    @Test
    public void testGetJourneyForEmployee() {
        Employee manager = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.MANAGER);
        Employee employee = newEmployee("Martin", "Petovsky", "email@google.com", GenderEnum.MALE, "4569877", UserClassEnum.PRODUCT_MANAGER);

        assertTrue(journeyDao.getJourneyForEmployee(employee).isEmpty());

        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx4gf56", null);
        Car car2 = newCar("SKODA", "Superb", "white", 5600, null, "2.0 TDI", UserClassEnum.CEO, "hghgf8hff6", null);

        Date pickUpDate = new java.util.Date(2013 - 1900, 4, 23);
        Date returnUpDate = new java.util.Date(2013 - 1900, 4, 26);
        Journey journey = newJourney(car, employee, 200, pickUpDate, returnUpDate);
        Date pickUpDate2 = new java.util.Date(2014 - 1900, 5, 24);
        Date returnUpDate2 = new java.util.Date(2014 - 1900, 5, 28);
        Journey journey1 = newJourney(car2, manager, 300, pickUpDate2, returnUpDate2);
        initializeJourney(car, car2, manager, employee, journey, journey1);
        List<Journey> actual = journeyDao.getJourneyForEmployee(employee);
        List<Journey> expected = Arrays.asList(journey);
        List<Journey> expected1 = Arrays.asList(journey1);

        Collections.sort(actual, idComparator);
        Collections.sort(expected, idComparator);

        assertEquals(expected, actual);
        //compares two lists
        assertFalse(expected1.equals(actual));

        actual = journeyDao.getJourneyForEmployee(manager);
        assertEquals(expected1, actual);
    }

    /**
     * Test of getJourneyForCar method, of class JourneyDaoImpl.
     */
    @Test
    public void testGetJourneyForCar() {
        Car newCar = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx4gf56", null);
        Car oldCar = newCar("SKODA", "Superb", "white", 5600, null, "2.0 TDI", UserClassEnum.CEO, "hghgf8hff6", null);

        assertTrue(journeyDao.getJourneyForCar(oldCar).isEmpty());

        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.MANAGER);
        Employee employee1 = newEmployee("Martin", "Petovsky", "email@google.com", GenderEnum.MALE, "4569877", UserClassEnum.PRODUCT_MANAGER);

        Date pickUpDate = new java.util.Date(2013 - 1900, 4, 23);
        Date returnUpDate = new java.util.Date(2013 - 1900, 4, 26);
        Journey journey = newJourney(oldCar, employee1, 200, pickUpDate, returnUpDate);
        Date pickUpDate2 = new java.util.Date(2014 - 1900, 5, 24);
        Date returnUpDate2 = new java.util.Date(2014 - 1900, 5, 28);
        Journey journey1 = newJourney(newCar, employee, 300, pickUpDate2, returnUpDate2);
        initializeJourney(oldCar, newCar, employee, employee1, journey, journey1);

        List<Journey> actual = journeyDao.getJourneyForCar(oldCar);
        List<Journey> expected = Arrays.asList(journey);
        List<Journey> expected1 = Arrays.asList(journey1);

        Collections.sort(actual, idComparator);
        Collections.sort(expected, idComparator);

        assertEquals(expected, actual);
        //compares two lists
        assertFalse(expected1.equals(actual));

        actual = journeyDao.getJourneyForCar(newCar);
        assertEquals(expected1, actual);
    }

    /**
     * Test of getJourneyById method, of class JourneyDaoImpl.
     */
    @Test
    public void testGetJourneyById() {
        assertNull(journeyDao.getJourneyById(1l));

        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx4gf56", null);
        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);
        Date pickUpDate = new java.util.Date(2013 - 1900, 4, 23);
        Date returnUpDate = new java.util.Date(2013 - 1900, 4, 26);
        Journey journey = newJourney(car, employee, 200, pickUpDate, returnUpDate);
        initializeJourney(car, employee, journey);
        long journeyId = journey.getId();
        Journey result = journeyDao.getJourneyById(journeyId);
        assertEquals(journey, result);
        assertDeepEquals(journey, result);
    }

    /**
     * Test of getAllJourneys method, of class JourneyDaoImpl.
     */
    @Test
    public void testGetAllJourneys() {
        assertTrue(journeyDao.getAllJourneys().isEmpty());

        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx4gf56", null);
        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);
        Date pickUpDate = new java.util.Date(2013 - 1900, 4, 23);
        Date returnUpDate = new java.util.Date(2013 - 1900, 4, 26);
        Journey journey = newJourney(car, employee, 200, pickUpDate, returnUpDate);

        Car car2 = newCar("SKODA", "Superb", "white", 5600, null, "2.0 TDI", UserClassEnum.CEO, "hghgf8hff6", null);
        Employee employee2 = newEmployee("Martin", "Petovsky", "email@google.com", GenderEnum.MALE, "4569877", UserClassEnum.CEO);
        Date pickUpDate2 = new java.util.Date(2014 - 1900, 5, 24);
        Date returnUpDate2 = new java.util.Date(2014 - 1900, 5, 28);
        Journey journey1 = newJourney(car2, employee2, 300, pickUpDate2, returnUpDate2);
        initializeJourney(car, car2, employee, employee2, journey, journey1);

        List<Journey> expected = Arrays.asList(journey, journey1);
        List<Journey> actual = journeyDao.getAllJourneys();

        Collections.sort(actual, idComparator);
        Collections.sort(expected, idComparator);

        assertEquals(expected, actual);
        assertDeepEquals(expected, actual);
    }

    public static Journey newJourney(Car car, Employee employee, int mileage, Date pickUpDate, Date returnDate) {
        Journey journey = new Journey();
        journey.setCar(car);
        journey.setEmployee(employee);
        journey.setMileage(mileage);
        journey.setPickUpDate(pickUpDate);
        journey.setReturnDate(returnDate);
        return journey;
    }

    public void initializeJourney(Car car, Employee employee, Journey journey) {
        carDao.createCar(car);
        employeeDao.createEmployee(employee);
        journeyDao.createJourney(journey);
    }

    public void initializeJourney(Car car, Car car1, Employee employee, Employee employee1, Journey journey, Journey journey1) {
        carDao.createCar(car);
        carDao.createCar(car1);
        employeeDao.createEmployee(employee);
        employeeDao.createEmployee(employee1);
        journeyDao.createJourney(journey);
        journeyDao.createJourney(journey1);
    }

    private void assertDeepEquals(List<Journey> expectedList, List<Journey> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            Journey expected = expectedList.get(i);
            Journey actual = actualList.get(i);
            assertDeepEquals(expected, actual);
        }
    }

    private void assertDeepEquals(Journey expected, Journey actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCar(), actual.getCar());
        assertEquals(expected.getEmployee(), actual.getEmployee());
        assertEquals(expected.getMileage(), actual.getMileage());
        assertEquals(expected.getPickUpDate(), actual.getPickUpDate());
        assertEquals(expected.getReturnDate(), actual.getReturnDate());
    }
    private static Comparator<Journey> idComparator = new Comparator<Journey>() {
        @Override
        public int compare(Journey o1, Journey o2) {
            return Long.valueOf(o1.getId()).compareTo(Long.valueOf(o2.getId()));
        }
    };
}
