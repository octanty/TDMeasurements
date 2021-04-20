package cz.muni.fi.pa165.fleetmanagement.dao.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.CarDao;
import cz.muni.fi.pa165.fleetmanagement.dao.ServiceIntervalDao;
import static cz.muni.fi.pa165.fleetmanagement.dao.impl.ServiceIntervalDaoImpTest.assertDeepEquals;
import static cz.muni.fi.pa165.fleetmanagement.dao.impl.ServiceIntervalDaoImpTest.idComparator;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.entity.ServiceInterval;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import org.junit.After;
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
 * @author Peter Pavlik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ServiceIntervalDaoImpTest {

    @Autowired
    private CarDao carDao;
    @Autowired
    private ServiceIntervalDao serviceIntervalDao;

    public ServiceIntervalDaoImpTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of deleteServiceInterval method, of class
     * ServiceIntervalManagerDaoImp.
     */
    public void testDeleteServiceInterval() {
        System.out.println("deleteServiceInterval");

        ServiceInterval serviceInterval1 = new ServiceInterval();
        serviceInterval1.setMileageControl(new Integer(2000));
        serviceInterval1.setService("Popredajna prehliadka - test DeleteServiceInterval 1");

        ServiceInterval serviceInterval2 = new ServiceInterval();
        serviceInterval2.setMileageControl(new Integer(3000));
        serviceInterval2.setService("Popredajna prehliadka - test DeleteServiceInterval 2");

        ServiceInterval serviceInterval3 = new ServiceInterval();
        serviceInterval3.setMileageControl(new Integer(4000));
        serviceInterval3.setService("Popredajna prehliadka - test DeleteServiceInterval 3");

        List<ServiceInterval> serviceIntervals = new LinkedList<ServiceInterval>();
        serviceIntervals.add(serviceInterval1);
        serviceIntervals.add(serviceInterval2);
        serviceIntervals.add(serviceInterval3);

        Car car1 = CarDaoImplTest.newCar("Znacka", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx12fdsfsd3", serviceIntervals);

        carDao.createCar(car1);

        List<ServiceInterval> actual = null;

        List<ServiceInterval> expected1 = Arrays.asList(serviceInterval1, serviceInterval2, serviceInterval3);
        List<ServiceInterval> expected2 = Arrays.asList(serviceInterval1, serviceInterval2);
        List<ServiceInterval> expected3 = Arrays.asList(serviceInterval1);

        Collections.sort(expected1, idComparator);
        Collections.sort(expected2, idComparator);
        Collections.sort(expected3, idComparator);

        // serviceInterval1, serviceInterval2, serviceInterval3 are in Database
        actual = serviceIntervalDao.getServiceIntervalForCar(car1);
        Collections.sort(actual, idComparator);
        assertEquals(expected1, actual);
        assertDeepEquals(expected1, actual);

        // We will delete serviceInterval3 from Database
        serviceIntervalDao.deleteServiceInterval(serviceInterval3);
        actual = serviceIntervalDao.getServiceIntervalForCar(car1);
        Collections.sort(actual, idComparator);
        assertEquals(expected2, actual);
        assertDeepEquals(expected2, actual);

        // We will delete serviceInterval2 from Database
        serviceIntervalDao.deleteServiceInterval(serviceInterval2);
        actual = serviceIntervalDao.getServiceIntervalForCar(car1);
        Collections.sort(actual, idComparator);
        assertEquals(expected3, actual);
        assertDeepEquals(expected3, actual);

        // We will delete serviceInterval1 from Database
        // Every service interval will be delete
        serviceIntervalDao.deleteServiceInterval(serviceInterval1);
        actual = serviceIntervalDao.getServiceIntervalForCar(car1);
        Collections.sort(actual, idComparator);
        assertTrue(actual.isEmpty());
    }

    /**
     * Test of deleteServiceIntervalWithWrongAttributes method, of class
     * ServiceIntervalManagerDaoImp.
     */
    @Test
    public void testDeleteServiceIntervalWithWrongAttributes() {
        System.out.println("deleteServiceIntervalWithWrongAttributes");

        ServiceInterval serviceInterval1 = new ServiceInterval();
        serviceInterval1.setMileageControl(new Integer(2000));
        serviceInterval1.setService("Popredajna prehliadka - test DeleteServiceIntervalWithWrongAttributes 1");

        List<ServiceInterval> serviceIntervals = new LinkedList<ServiceInterval>();
        serviceIntervals.add(serviceInterval1);

        Car car = CarDaoImplTest.newCar("Znacka", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx12fdsfsd3", serviceIntervals);

        carDao.createCar(car);

        Long serviceIntervalId = serviceInterval1.getId();

        try {
            serviceIntervalDao.deleteServiceInterval(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }

        try {
            serviceInterval1 = serviceIntervalDao.getServiceIntervalById(serviceIntervalId);
            serviceInterval1.setId(null);
            serviceIntervalDao.deleteServiceInterval(serviceInterval1);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
    }

    /**
     * Test of updateServiceInterval method, of class
     * ServiceIntervalManagerDaoImp.
     */
    @Test
    public void testUpdateServiceInterval() {
        System.out.println("updateServiceInterval");

        ServiceInterval serviceInterval1 = new ServiceInterval();
        serviceInterval1.setMileageControl(new Integer(2000));
        serviceInterval1.setService("Popredajna prehliadka - test UpdateServiceInterval 1");

        ServiceInterval serviceInterval2 = new ServiceInterval();
        serviceInterval2.setMileageControl(new Integer(2000));
        serviceInterval2.setService("Popredajna prehliadka - test UpdateServiceInterval 2");

        List<ServiceInterval> serviceIntervals1 = new LinkedList<ServiceInterval>();
        serviceIntervals1.add(serviceInterval1);
        serviceIntervals1.add(serviceInterval2);

        Car car1 = CarDaoImplTest.newCar("Znacka", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx12fdsfsd3", serviceIntervals1);

        carDao.createCar(car1);

        Long serviceIntervalId = serviceInterval1.getId();

        // We try change CompletionDate of serviceInterval1
        // We test that this operation do not change other parameters
        serviceInterval1 = serviceIntervalDao.getServiceIntervalById(serviceIntervalId);
        serviceInterval1.setCompletionDate(getDate(2013, 12, 30));
        serviceIntervalDao.updateServiceInterval(serviceInterval1);
        assertEquals(getDate(2013, 12, 30), serviceInterval1.getCompletionDate());
        assertEquals(new Integer(2000), serviceInterval1.getMileageControl());
        assertEquals("Popredajna prehliadka - test UpdateServiceInterval 1", serviceInterval1.getService());
        assertNull(serviceInterval1.getDateControl());

        // We try change DateControl of serviceInterval1
        // We test that this operation do not change other parameters
        serviceInterval1 = serviceIntervalDao.getServiceIntervalById(serviceIntervalId);
        serviceInterval1.setDateControl(getDate(2013, 12, 25));
        serviceIntervalDao.updateServiceInterval(serviceInterval1);
        assertEquals(getDate(2013, 12, 30), serviceInterval1.getCompletionDate());
        assertEquals(new Integer(2000), serviceInterval1.getMileageControl());
        assertEquals("Popredajna prehliadka - test UpdateServiceInterval 1", serviceInterval1.getService());
        assertEquals(getDate(2013, 12, 25), serviceInterval1.getDateControl());

        // We try change MileageControl of serviceInterval1
        // We test that this operation do not change other parameters
        serviceInterval1 = serviceIntervalDao.getServiceIntervalById(serviceIntervalId);
        serviceInterval1.setMileageControl(new Integer(3000));
        serviceIntervalDao.updateServiceInterval(serviceInterval1);
        assertEquals(getDate(2013, 12, 30), serviceInterval1.getCompletionDate());
        assertEquals(new Integer(3000), serviceInterval1.getMileageControl());
        assertEquals("Popredajna prehliadka - test UpdateServiceInterval 1", serviceInterval1.getService());
        assertEquals(getDate(2013, 12, 25), serviceInterval1.getDateControl());

        // We try change Service of serviceInterval1
        // We test that this operation do not change other parameters
        serviceInterval1 = serviceIntervalDao.getServiceIntervalById(serviceIntervalId);
        serviceInterval1.setService("Zmena");
        serviceIntervalDao.updateServiceInterval(serviceInterval1);
        assertEquals(getDate(2013, 12, 30), serviceInterval1.getCompletionDate());
        assertEquals(new Integer(3000), serviceInterval1.getMileageControl());
        assertEquals("Zmena", serviceInterval1.getService());
        assertEquals(getDate(2013, 12, 25), serviceInterval1.getDateControl());

        // We test that previous changes of serviceInterval1 do not change serviceInterval2
        assertDeepEquals(serviceInterval1, serviceIntervalDao.getServiceIntervalById(serviceInterval1.getId()));
    }

    /**
     * Test of getServiceIntervalWithWrongAttributes method, of class
     * ServiceIntervalManagerDaoImp.
     */
    @Test
    public void testUpdateServiceIntervalWithWrongAttributes() {

        ServiceInterval serviceInterval1 = new ServiceInterval();
        serviceInterval1.setMileageControl(new Integer(2000));
        serviceInterval1.setService("Popredajna prehliadka - test UpdateServiceIntervalWithWrongAttributes 1");

        List<ServiceInterval> serviceIntervals1 = new LinkedList<ServiceInterval>();
        serviceIntervals1.add(serviceInterval1);

        Car car1 = CarDaoImplTest.newCar("Znacka", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx12fdsfsd3", serviceIntervals1);

        carDao.createCar(car1);

        Long serviceIntervalId = serviceInterval1.getId();

        try {
            serviceIntervalDao.updateServiceInterval(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }

        try {
            serviceInterval1 = serviceIntervalDao.getServiceIntervalById(serviceIntervalId);
            serviceInterval1.setId(null);
            serviceIntervalDao.updateServiceInterval(serviceInterval1);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
    }

    /**
     * Test of getServiceIntervalById method, of class
     * ServiceIntervalManagerDaoImp.
     */
    @Test
    public void testGetServiceIntervalById() {
        System.out.println("getServiceIntervalById");

        ServiceInterval serviceInterval1 = new ServiceInterval();
        serviceInterval1.setMileageControl(new Integer(2000));
        serviceInterval1.setService("Popredajna prehliadka - test ServiceIntervalById 1");

        List<ServiceInterval> serviceIntervals1 = new LinkedList<ServiceInterval>();
        serviceIntervals1.add(serviceInterval1);

        Car car1 = CarDaoImplTest.newCar("Znacka", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx12fdsfsd3", serviceIntervals1);

        carDao.createCar(car1);

        Long serviceIntervalId = serviceInterval1.getId();

        ServiceInterval result = serviceIntervalDao.getServiceIntervalById(serviceIntervalId);
        assertEquals(serviceInterval1, result);
//        assertNotSame(serviceInterval1, result);
        assertDeepEquals(serviceInterval1, result);
    }

    /**
     * Test of getServiceIntervalForCar method, of class
     * ServiceIntervalManagerDaoImp.
     */
    @Test
    public void testGetServiceIntervalForCar() {
        System.out.println("getServiceIntervalForCar");

        ServiceInterval serviceInterval1 = new ServiceInterval();
        serviceInterval1.setMileageControl(new Integer(2000));
        serviceInterval1.setService("Popredajna prehliadka - ServiceIntervalTest 1");

        ServiceInterval serviceInterval2 = new ServiceInterval();
        serviceInterval2.setMileageControl(new Integer(2000));
        serviceInterval2.setService("Popredajna prehliadka - ServiceIntervalTest 2");

        List<ServiceInterval> serviceIntervals1 = new LinkedList<ServiceInterval>();
        serviceIntervals1.add(serviceInterval1);

        List<ServiceInterval> serviceIntervals2 = new LinkedList<ServiceInterval>();
        serviceIntervals2.add(serviceInterval2);

        Car car1 = CarDaoImplTest.newCar("Znacka", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx12fdsfsd3", serviceIntervals1);
        Car car2 = CarDaoImplTest.newCar("Znacka", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "hghg5df6d6d6", serviceIntervals2);

        carDao.createCar(car1);
        carDao.createCar(car2);

        List<ServiceInterval> actual = serviceIntervalDao.getServiceIntervalForCar(car1);

        Collections.sort(serviceIntervals1, idComparator);
        Collections.sort(actual, idComparator);

        assertNotNull(actual);
        assertEquals(actual, serviceIntervals1);
        assertDeepEquals(actual, serviceIntervals1);
    }

    /**
     * Test of testGetAllServiceInterval method, of class
     * ServiceIntervalManagerDaoImp.
     */
    @Test
    public void testGetAllServiceInterval() {
        System.out.println("getServiceIntervalForCar");

        ServiceInterval serviceInterval1 = new ServiceInterval();
        serviceInterval1.setMileageControl(new Integer(2000));
        serviceInterval1.setService("Popredajna prehliadka - ServiceIntervalTest 1");

        ServiceInterval serviceInterval2 = new ServiceInterval();
        serviceInterval2.setMileageControl(new Integer(2000));
        serviceInterval2.setService("Popredajna prehliadka - ServiceIntervalTest 2");

        List<ServiceInterval> serviceIntervals1 = new LinkedList<ServiceInterval>();
        serviceIntervals1.add(serviceInterval1);

        List<ServiceInterval> serviceIntervals2 = new LinkedList<ServiceInterval>();
        serviceIntervals2.add(serviceInterval2);

        Car car1 = CarDaoImplTest.newCar("Znacka", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx12fdsfsd3", serviceIntervals1);
        Car car2 = CarDaoImplTest.newCar("Znacka", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "hghg5df6d6d6", serviceIntervals2);

        carDao.createCar(car1);
        carDao.createCar(car2);

        List<ServiceInterval> actual = serviceIntervalDao.getAllServiceInterval();
        serviceIntervals1.addAll(serviceIntervals2);
        Collections.sort(serviceIntervals1, idComparator);
        Collections.sort(actual, idComparator);
//
        assertNotNull(actual);
        assertEquals(actual, serviceIntervals1);
        assertDeepEquals(actual, serviceIntervals1);
    }
    public static Comparator<ServiceInterval> idComparator = new Comparator<ServiceInterval>() {
        @Override
        public int compare(ServiceInterval o1, ServiceInterval o2) {
            return Long.valueOf(o1.getId()).compareTo(Long.valueOf(o2.getId()));
        }
    };

    public static void assertDeepEquals(ServiceInterval expected, ServiceInterval actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCompletionDate(), actual.getCompletionDate());
        assertEquals(expected.getDateControl(), actual.getDateControl());
        assertEquals(expected.getMileageControl(), actual.getMileageControl());
        assertEquals(expected.getService(), actual.getService());
    }

    public static void assertDeepEquals(List<ServiceInterval> expectedList, List<ServiceInterval> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            ServiceInterval expected = expectedList.get(i);
            ServiceInterval actual = actualList.get(i);
            assertDeepEquals(expected, actual);
        }
    }

    private Date getDate(int year, int month, int day) {
        Date date = null;
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(year + "-" + month + "-" + day);
        } catch (Exception ex) {
            fail();
        }
        return date;
    }
}
