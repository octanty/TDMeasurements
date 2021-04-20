package cz.muni.fi.pa165.fleetmanagement.service.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.CarDao;
import static cz.muni.fi.pa165.fleetmanagement.dao.impl.CarDaoImplTest.newCar;
import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import cz.muni.fi.pa165.fleetmanagement.exception.ServiceDataAccesException;
import cz.muni.fi.pa165.fleetmanagement.utils.EntityToDTOConvertor;
import java.util.Arrays;
import static junit.framework.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Peter Pavlik
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class CarServiceImplTest {

    private CarServiceImpl carService;
    private CarDao mockedCarDao;
    private Car car1;
    private Car car2;
    private CarDTO carDTO2;
    private CarDTO carDTO1;
    private Employee employee;
    private EmployeeDTO employeeDTO;

    @Before
    public void setUp() throws Exception {
        carService = new CarServiceImpl();
        mockedCarDao = mock(CarDao.class);
        carService.setCarDao(mockedCarDao);

        employee = new Employee();
        employeeDTO = EntityToDTOConvertor.toDTO(employee);

        car1 = newCar("Znacka", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx12fdsfsd3", null);
        carDTO1 = EntityToDTOConvertor.toDto(car1);

        car2 = newCar("Znacka", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx12fdsfsd3", null);
        carDTO2 = EntityToDTOConvertor.toDto(car2);

        car1.setId(1L);
        car2.setId(2L);

        carDTO1.setId(1L);
        carDTO2.setId(2L);

        // testGetAllCarForEmployee
        when(mockedCarDao.getAllCarForEmployee(employee)).thenReturn(Arrays.asList(carDTO1, carDTO2));
        doThrow(ServiceDataAccesException.class).when(mockedCarDao).getAllCarForEmployee(null);

        // testGetAllCar
        when(mockedCarDao.getAllCar()).thenReturn(Arrays.asList(carDTO1, carDTO2));

        // testGetCarById
        when(mockedCarDao.getCarById(1L)).thenReturn(car1);
        when(mockedCarDao.getCarById(2L)).thenReturn(car2);
        when(mockedCarDao.getCarById(3L)).thenReturn(null);

        // testUpdateCar
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Car update = (Car) args[0];
                car2.setId(update.getId());
                car2.setBrand(update.getBrand());
                car2.setColor(update.getColor());
                car2.setMileage(update.getMileage());
                car2.setModel(update.getModel());
                car2.setPhoto(update.getPhoto());
                car2.setServiceInterval(update.getServiceInterval());
                car2.setEngine(update.getEngine());
                car2.setUserClass(update.getUserClass());
                car2.setLicensePlate(update.getLicensePlate());
                return null;
            }
        }).when(mockedCarDao).updateCar(car1);

        doThrow(ServiceDataAccesException.class).when(mockedCarDao).updateCar(null);

        // testCreateCar
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Car car = (Car) args[0];
                car.setId(1L);
                return null;
            }
        }).when(mockedCarDao).createCar(car1);

        doThrow(ServiceDataAccesException.class).when(mockedCarDao).createCar(null);

        // testDeleteCar
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Car car = (Car) args[0];
                car1.setId(null);
                return null;
            }
        }).when(mockedCarDao).deleteCar(car1);

        doThrow(ServiceDataAccesException.class).when(mockedCarDao).deleteCar(null);

    }

    /**
     * Test of createCar method, of class CarServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testCreateCar() {
        carService.createCar(carDTO1);
        assertNotNull(carDTO1.getId());

        carService.createCar(null);
    }

    /**
     * Test of deleteCar method, of class CarServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testDeleteCar() {
        carService.deleteCar(carDTO1);
        assertNull(car1.getId());

        carService.deleteCar(null);
    }

    /**
     * Test of updateCar method, of class CarServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testUpdateCar() {
        carService.updateCar(carDTO1);
        assertDeepEquals(car1, car2);

        carService.updateCar(null);

    }

    /**
     * Test of getCarById method, of class CarServiceImpl.
     */
    @Test
    public void testGetCarById() {
        assertNotNull(carService.getCarById(1L));
        assertEquals(carService.getCarById(1L), carDTO1);

        assertNotNull(carService.getCarById(2L));
        assertEquals(carService.getCarById(2L), carDTO2);

        assertNull(carService.getCarById(3L));
    }

    /**
     * Test of getAllCarForEmployee method, of class CarServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testGetAllCarForEmployee() {
        assertNotNull(carService.getAllCarForEmployee(employeeDTO));
        assertEquals(carService.getAllCarForEmployee(employeeDTO), Arrays.asList(carDTO1, carDTO2));

        carService.deleteCar(null);
    }

    /**
     * Test of getAllCar method, of class CarServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testGetAllCar() {
        assertNotNull(carService.getAllCar());
        assertEquals(carService.getAllCar(), Arrays.asList(carDTO1, carDTO2));

        carService.deleteCar(null);
    }

    private void assertDeepEquals(Car expected, Car actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getBrand(), actual.getBrand());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getColor(), actual.getColor());
        assertEquals(expected.getMileage(), actual.getMileage());
        assertEquals(expected.getEngine(), actual.getEngine());
        assertEquals(expected.getUserClass(), actual.getUserClass());
        assertEquals(expected.getLicensePlate(), actual.getLicensePlate());
        assertEquals(Arrays.toString(expected.getPhoto()), Arrays.toString(actual.getPhoto()));
        assertTrue(Arrays.equals(expected.getPhoto(), actual.getPhoto()));
    }
}
