package cz.muni.fi.pa165.fleetmanagement.service.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.ServiceIntervalDao;
import cz.muni.fi.pa165.fleetmanagement.dao.CarDao;
import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.ServiceIntervalDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.ServiceInterval;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.exception.ServiceDataAccesException;
import cz.muni.fi.pa165.fleetmanagement.utils.EntityToDTOConvertor;

import java.util.Arrays;
import java.util.Date;
import static junit.framework.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eduard Dobai
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ServiceIntervalServiceImplTest {

    private ServiceIntervalServiceImpl serviceIntervalService;
    private ServiceIntervalDao mockedServiceIntervalDao;
    private ServiceInterval serviceInterval1;
    private ServiceInterval serviceInterval2;
    private ServiceIntervalDTO serviceIntervalDTO1;
    private ServiceIntervalDTO serviceIntervalDTO2;
    private Car car;
    private CarDTO carDTO;

    @Before
    public void setUp() throws Exception {

        serviceIntervalService = new ServiceIntervalServiceImpl();
        mockedServiceIntervalDao = mock(ServiceIntervalDao.class);
        serviceIntervalService.setServiceDao(mockedServiceIntervalDao);

        car = new Car();
        carDTO = EntityToDTOConvertor.toDto(car);


        serviceInterval1 = new ServiceInterval();
        serviceInterval1.setMileageControl(new Integer(2000));
        serviceInterval1.setService("Popredajna prehliadka - ServiceIntervalTest 1");
        Date completionDate = new java.util.Date(2013 - 1900, 4, 23);
        Date dateControl = new java.util.Date(2013 - 1900, 4, 26);
        serviceInterval1.setCompletionDate(completionDate);
        serviceInterval1.setDateControl(dateControl);
        serviceIntervalDTO1 = EntityToDTOConvertor.toDTO(serviceInterval1);

        serviceInterval2 = new ServiceInterval();
        serviceInterval2.setMileageControl(new Integer(2000));
        serviceInterval2.setService("Popredajna prehliadka - ServiceIntervalTest 1");
        serviceInterval2.setCompletionDate(completionDate);
        serviceInterval2.setDateControl(dateControl);
        serviceIntervalDTO2 = EntityToDTOConvertor.toDTO(serviceInterval2);


        serviceInterval1.setId(1L);
        serviceInterval2.setId(2L);

        serviceIntervalDTO1.setId(1L);
        serviceIntervalDTO2.setId(2L);

        // testGetServiceIntervalForCar
        when(mockedServiceIntervalDao.getServiceIntervalForCar(car)).thenReturn(Arrays.asList(serviceInterval1, serviceInterval2));
        doThrow(ServiceDataAccesException.class).when(mockedServiceIntervalDao).getServiceIntervalForCar(null);
        
        // testGetAllServiceInterval
        when(mockedServiceIntervalDao.getAllServiceInterval()).thenReturn(Arrays.asList(serviceInterval1, serviceInterval2));
        
        // testGetServiceIntervalById
        when(mockedServiceIntervalDao.getServiceIntervalById(1L)).thenReturn(serviceInterval1);
        when(mockedServiceIntervalDao.getServiceIntervalById(2L)).thenReturn(serviceInterval2);
        when(mockedServiceIntervalDao.getServiceIntervalById(3L)).thenReturn(null);

        //testUpdateServiceInterval
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                ServiceInterval update = (ServiceInterval) args[0];
                serviceInterval2.setId(update.getId());
                serviceInterval2.setCompletionDate(update.getCompletionDate());
                serviceInterval2.setDateControl(update.getDateControl());
                serviceInterval2.setMileageControl(update.getMileageControl());
                serviceInterval2.setService(update.getService());
                return null;
            }
        }).when(mockedServiceIntervalDao).updateServiceInterval(serviceInterval1);
        doThrow(ServiceDataAccesException.class).when(mockedServiceIntervalDao).updateServiceInterval(null);

        //testDeleteServiceInterval
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                ServiceInterval serviceInterval = (ServiceInterval) args[0];
                serviceInterval.setId(null);
                return null;
            }
        }).when(mockedServiceIntervalDao).deleteServiceInterval(serviceInterval1);
        doThrow(ServiceDataAccesException.class).when(mockedServiceIntervalDao).deleteServiceInterval(null);

    }

    /**
     * Test of deleteServiceInterval method, of class
     * ServiceIntervalServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testDeleteServiceInterval() {
        serviceIntervalService.deleteServiceInterval(serviceIntervalDTO1);
        assertNotNull(serviceInterval1.getId());

        serviceIntervalService.deleteServiceInterval(null);
    }

    /**
     * Test of updateServiceInterval method, of class
     * ServiceIntervalServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testUpdateServiceInterval() {
        serviceIntervalService.updateServiceInterval(serviceIntervalDTO1);
        assertDeepEquals(serviceInterval1, serviceInterval2);

        serviceIntervalService.updateServiceInterval(null);
    }

    /**
     * Test of getServiceIntervalById method, of class
     * ServiceIntervalServiceImpl.
     */
    @Test
    public void testGetServiceIntervalById() {
        assertNotNull(serviceIntervalService.getServiceIntervalById(1L));
        assertEquals(serviceIntervalService.getServiceIntervalById(1L), serviceIntervalDTO1);

        assertNotNull(serviceIntervalService.getServiceIntervalById(2L));
        assertEquals(serviceIntervalService.getServiceIntervalById(2L), serviceIntervalDTO2);
        assertNull(serviceIntervalService.getServiceIntervalById(3L));
    }

    /**
     * Test of getServiceIntervalForCar method, of class
     * ServiceIntervalServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testGetServiceIntervalForCar() {
        assertNotNull(serviceIntervalService.getServiceIntervalForCar(carDTO));
        assertEquals(serviceIntervalService.getServiceIntervalForCar(carDTO), Arrays.asList(serviceIntervalDTO1, serviceIntervalDTO2));

        serviceIntervalService.deleteServiceInterval(null);
    }

    /**
     * Test of testGetAllServiceInterval method, of class
     * ServiceIntervalServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testGetAllServiceInterval() {
        assertNotNull(serviceIntervalService.getAllServiceInterval());
        assertEquals(serviceIntervalService.getAllServiceInterval(), Arrays.asList(serviceIntervalDTO1, serviceIntervalDTO2));

        serviceIntervalService.deleteServiceInterval(null);
    }

    public static void assertDeepEquals(ServiceInterval expected, ServiceInterval actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCompletionDate(), actual.getCompletionDate());
        assertEquals(expected.getDateControl(), actual.getDateControl());
        assertEquals(expected.getMileageControl(), actual.getMileageControl());
        assertEquals(expected.getService(), actual.getService());
    }
}
