package cz.muni.fi.pa165.fleetmanagement.service.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.JourneyDao;
import cz.muni.fi.pa165.fleetmanagement.dto.JourneyDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.Journey;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.exception.ServiceDataAccesException;
import cz.muni.fi.pa165.fleetmanagement.utils.EntityToDTOConvertor;

import java.util.Arrays;
import java.util.Date;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ján Švec
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class JourneyServiceImplTest {

    private JourneyServiceImpl journeyService;
    private JourneyDao mockedJourneyDao;
    private Journey journey1;
    private Journey journey2;
    private JourneyDTO journeyDTO1;
    private JourneyDTO journeyDTO2;
    private Car car;
    private CarDTO carDTO;
    private Employee employee;
    private EmployeeDTO employeeDTO;

    @Before
    public void setUp() throws Exception {
        journeyService = new JourneyServiceImpl();
        mockedJourneyDao = mock(JourneyDao.class);
        journeyService.setJourneyDao(mockedJourneyDao);

        car = new Car();
        carDTO = EntityToDTOConvertor.toDto(car);

        employee = new Employee();
        employeeDTO = EntityToDTOConvertor.toDTO(employee);

        Date pickUpDate = new java.util.Date(2013 - 1900, 4, 23);
        Date returnUpDate = new java.util.Date(2013 - 1900, 4, 26);

        journey1 = newJourney(car, employee, 200, pickUpDate, returnUpDate);
        journeyDTO1 = EntityToDTOConvertor.toDTO(journey1);

        journey2 = newJourney(car, employee, 200, pickUpDate, returnUpDate);
        journeyDTO2 = EntityToDTOConvertor.toDTO(journey2);

        journey1.setId(1L);
        journey2.setId(2L);

        journeyDTO1.setId(1L);
        journeyDTO2.setId(2L);

        // testGetJourneyForEmployee
        when(mockedJourneyDao.getJourneyForEmployee(employee)).thenReturn(Arrays.asList(journey1, journey2));
        doThrow(ServiceDataAccesException.class).when(mockedJourneyDao).getJourneyForEmployee(null);

        // testGetJourneyForCar
        when(mockedJourneyDao.getJourneyForCar(car)).thenReturn(Arrays.asList(journey1, journey2));
        doThrow(ServiceDataAccesException.class).when(mockedJourneyDao).getJourneyForCar(null);

        // testgetAllJourneys
        when(mockedJourneyDao.getAllJourneys()).thenReturn(Arrays.asList(journey1, journey2));
        doThrow(ServiceDataAccesException.class).when(mockedJourneyDao).getAllJourneys();

        // testGetJourneyById
        when(mockedJourneyDao.getJourneyById(1L)).thenReturn(journey1);
        when(mockedJourneyDao.getJourneyById(2L)).thenReturn(journey2);
        when(mockedJourneyDao.getJourneyById(3L)).thenReturn(null);

        // testUpdateJourney
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Journey update = (Journey) args[0];
                journey2.setId(update.getId());
                journey2.setCar(update.getCar());
                journey2.setEmployee(update.getEmployee());
                journey2.setMileage(update.getMileage());
                journey2.setPickUpDate(update.getPickUpDate());
                journey2.setReturnDate(update.getReturnDate());
                return null;
            }
        }).when(mockedJourneyDao).updateJourney(journey1);
        doThrow(ServiceDataAccesException.class).when(mockedJourneyDao).updateJourney(null);

        //   testCreateJourney
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Journey journey = (Journey) args[0];
                journey.setId(1L);
                return null;
            }
        }).when(mockedJourneyDao).createJourney(journey1);
        doThrow(ServiceDataAccesException.class).when(mockedJourneyDao).createJourney(null);

        // testDeleteJourney
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Journey journey = (Journey) args[0];
                journey.setId(null);
                return null;
            }
        }).when(mockedJourneyDao).deleteJourney(journey1);
        doThrow(ServiceDataAccesException.class).when(mockedJourneyDao).deleteJourney(null);
    }

    /**
     * Test of createJourney method, of class JourneyServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testCreateJourney() {
        journeyService.createJourney(journeyDTO1);
        assertNotNull(journeyDTO1.getId());

        journeyService.createJourney(null);
    }

    /**
     * Test of deleteJourney method, of class JourneyServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testDeleteJourney() {
        journeyService.deleteJourney(journeyDTO1);
        assertNotNull(journey1.getId());

        journeyService.deleteJourney(null);
    }

    /**
     * Test of updateJourney method, of class JourneyServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testUpdateJourney() {
        journeyService.updateJourney(journeyDTO1);
        assertDeepEquals(journey1, journey2);

        journeyService.updateJourney(null);

    }

    /**
     * Test of getJourneyById method, of class JourneyServiceImpl.
     */
    @Test
    public void testGetJourneyById() {
        assertNotNull(journeyService.getJourneyById(1L));
        assertEquals(journeyService.getJourneyById(1L), journeyDTO1);

        assertNotNull(journeyService.getJourneyById(2L));
        assertEquals(journeyService.getJourneyById(2L), journeyDTO2);

        assertNull(journeyService.getJourneyById(3L));
    }

    /**
     * Test of getJourneyForEmployee method, of class JourneyServiceImpl.
     */
    @Test
    public void testGetJourneyForEmployee() {
        assertNotNull(journeyService.getJourneyForEmployee(employeeDTO));
        assertEquals(journeyService.getJourneyForEmployee(employeeDTO), Arrays.asList(journeyDTO1, journeyDTO2));

    }

    /**
     * Test of getJourneyForCar method, of class JourneyServiceImpl.
     */
    @Test
    public void testGetJourneyForCar() {
        assertNotNull(journeyService.getJourneyForCar(carDTO));
        assertEquals(journeyService.getJourneyForCar(carDTO), Arrays.asList(journeyDTO1, journeyDTO2));

    }

    /**
     * Test of getAllJourneys method, of class JourneyServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testGetAllJourneys() {
        assertNotNull(journeyService.getAllJourneys());
        assertEquals(journeyService.getAllJourneys(), Arrays.asList(journeyDTO1, journeyDTO2));
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

    private void assertDeepEquals(Journey expected, Journey actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCar(), actual.getCar());
        assertEquals(expected.getEmployee(), actual.getEmployee());
        assertEquals(expected.getMileage(), actual.getMileage());
        assertEquals(expected.getPickUpDate(), actual.getPickUpDate());
        assertEquals(expected.getReturnDate(), actual.getReturnDate());
    }
}
