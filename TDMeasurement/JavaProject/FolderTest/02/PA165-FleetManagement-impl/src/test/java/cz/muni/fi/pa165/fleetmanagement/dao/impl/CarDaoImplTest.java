package cz.muni.fi.pa165.fleetmanagement.dao.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.CarDao;
import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import cz.muni.fi.pa165.fleetmanagement.entity.ServiceInterval;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import cz.muni.fi.pa165.fleetmanagement.enums.GenderEnum;
import cz.muni.fi.pa165.fleetmanagement.utils.PhotoConverter;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import static junit.framework.Assert.assertEquals;
import org.springframework.test.context.ContextConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eduard Dobai
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class CarDaoImplTest {

    @Inject
    private CarDao carDao;
    private String pathToPhotos;

    public CarDaoImplTest() {
        String separator = File.separator;
        pathToPhotos = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "java" + separator + "cz" + separator + "muni" + separator + "fi" + separator + "pa165" + separator + "fleetmanagement" + separator + "dao" + separator + "impl" + separator + "testphoto" + separator;
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of createCar method, of class CarManagerDaoImpl.
     */
    @Test
    public void testCreateCar() {
        System.out.println("createCar");

        PhotoConverter photoConverter = new PhotoConverter();
        Byte[] photo = photoConverter.convertToByte(pathToPhotos + "audiA8.jpg");

        ServiceInterval serviceInterval1 = new ServiceInterval();
        serviceInterval1.setMileageControl(new Integer(2000));
        serviceInterval1.setService("Popredajna prehliadka - test CreateCar 1");

        List<ServiceInterval> serviceIntervals = new LinkedList<ServiceInterval>();
        serviceIntervals.add(serviceInterval1);

        Car car = newCar("Znacka", "730d", "red", 1500, photo, "2.0 TDI", UserClassEnum.CEO, "xxx12fdsfsd3", serviceIntervals);

        carDao.createCar(car);

        Long carId = car.getId();
        Long serviceIntervalId = serviceInterval1.getId();

        assertNotNull(carId);
        assertNotNull(serviceIntervalId);

        Car carResult = carDao.getCarById(carId);

        assertEquals(car, carResult);
//        assertNotSame(car, carResult);
        assertDeepEquals(car, carResult);
    }

    /**
     * Test of deleteCarWithWrongAttributes method, of class CarManagerDaoImpl.
     */
    @Test
    public void testCreateCarWithWrongAttributes() {
        System.out.println("createCarWithWrongAttributes");

        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx123", null);

        try {
            carDao.createCar(null);
            fail();
        } catch (IllegalArgumentException ex) {
            // OK
        }

        try {
            car.setId(1L);
            carDao.createCar(car);
            fail();
        } catch (IllegalArgumentException ex) {
            // OK
        }
    }

    /**
     * Test of deleteCar method, of class CarManagerDaoImpl.
     */
    @Test
    public void testDeleteCar() {
        System.out.println("deleteCar");

        Car car1 = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx123", null);
        Car car2 = newCar("AUDI", "A8", "black", 2600, null, "4.2", UserClassEnum.CEO, "xxx456", null);

        carDao.createCar(car1);
        carDao.createCar(car2);

        // Car 1
        assertNotNull(carDao.getCarById(car1.getId()));

        // Car 2
        assertNotNull(carDao.getCarById(car2.getId()));

        carDao.deleteCar(car1);

        // Car 1
        assertNull(carDao.getCarById(car1.getId()));

        // Car 1
        assertNotNull(carDao.getCarById(car2.getId()));
    }

    /**
     * Test of deleteCarWithWrongAttributes method, of class CarManagerDaoImpl.
     */
    @Test
    public void testDeleteCarWithWrongAttributes() {
        System.out.println("deleteCarWithWrongAttributes");

        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "xxx123", null);
        carDao.createCar(car);
        Long carId = car.getId();

        try {
            carDao.deleteCar(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }

        try {
            car = carDao.getCarById(carId);
            car.setId(null);
            carDao.deleteCar(car);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
    }

    /**
     * Test of updateCar method, of class CarManagerDaoImpl.
     */
    @Test
    public void testUpdateCar() {
        System.out.println("updateCar");

        PhotoConverter photoConverter = new PhotoConverter();
        Byte[] photo1 = photoConverter.convertToByte(pathToPhotos + "bmw7.jpg");
        Byte[] photo2 = photoConverter.convertToByte(pathToPhotos + "audiA8.jpg");

        Car car1 = newCar("BMW", "730d", "red", 1500, photo1, "2.0 TDI", UserClassEnum.CEO, "xxx123", null);
        Car car2 = newCar("AUDI", "A8", "black", 2600, photo2, "4.2", UserClassEnum.CEO, "xxx456", null);

        carDao.createCar(car1);
        carDao.createCar(car2);

        Long car1Id = car1.getId();

        // We try change brand of car1
        // We test that this operation do not change other parameters
        car1 = carDao.getCarById(car1Id);
        car1.setBrand("audi x");
        carDao.updateCar(car1);
        car1 = carDao.getCarById(car1Id);
        assertEquals("audi x", car1.getBrand());
        assertEquals("730d", car1.getModel());
        assertEquals("red", car1.getColor());
        assertEquals(1500, car1.getMileage());
        assertEquals(Arrays.toString(photo1), Arrays.toString(car1.getPhoto()));
        assertEquals("2.0 TDI", car1.getEngine());
        assertEquals(UserClassEnum.CEO, car1.getUserClass());
        assertEquals("xxx123", car1.getLicensePlate());

        // We try change model of car1
        // We test that this operation do not change other parameters
        car1 = carDao.getCarById(car1Id);
        car1.setModel("A8 x");
        carDao.updateCar(car1);
        car1 = carDao.getCarById(car1Id);
        assertEquals("audi x", car1.getBrand());
        assertEquals("A8 x", car1.getModel());
        assertEquals("red", car1.getColor());
        assertEquals(1500, car1.getMileage());
        assertEquals(Arrays.toString(photo1), Arrays.toString(car1.getPhoto()));
        assertEquals("2.0 TDI", car1.getEngine());
        assertEquals(UserClassEnum.CEO, car1.getUserClass());
        assertEquals("xxx123", car1.getLicensePlate());

        // We try change color of car1
        // We test that this operation do not change other parameters
        car1 = carDao.getCarById(car1Id);
        car1.setColor("black x");
        carDao.updateCar(car1);
        car1 = carDao.getCarById(car1Id);
        assertEquals("audi x", car1.getBrand());
        assertEquals("A8 x", car1.getModel());
        assertEquals("black x", car1.getColor());
        assertEquals(1500, car1.getMileage());
        assertEquals(Arrays.toString(photo1), Arrays.toString(car1.getPhoto()));
        assertEquals("2.0 TDI", car1.getEngine());
        assertEquals(UserClassEnum.CEO, car1.getUserClass());
        assertEquals("xxx123", car1.getLicensePlate());

        // We try change mileage of car1
        // We test that this operation do not change other parameters
        car1 = carDao.getCarById(car1Id);
        car1.setMileage(3000);
        carDao.updateCar(car1);
        car1 = carDao.getCarById(car1Id);
        assertEquals("audi x", car1.getBrand());
        assertEquals("A8 x", car1.getModel());
        assertEquals("black x", car1.getColor());
        assertEquals(3000, car1.getMileage());
        assertEquals(Arrays.toString(photo1), Arrays.toString(car1.getPhoto()));
        assertEquals("2.0 TDI", car1.getEngine());
        assertEquals(UserClassEnum.CEO, car1.getUserClass());
        assertEquals("xxx123", car1.getLicensePlate());

        // We try change photo of car1
        // We test that this operation do not change other parameters
        car1 = carDao.getCarById(car1Id);
        car1.setPhoto(photo2);
        carDao.updateCar(car1);
        car1 = carDao.getCarById(car1Id);
        assertEquals("audi x", car1.getBrand());
        assertEquals("A8 x", car1.getModel());
        assertEquals("black x", car1.getColor());
        assertEquals(3000, car1.getMileage());
        assertEquals(Arrays.toString(photo2), Arrays.toString(car1.getPhoto()));
        assertEquals("2.0 TDI", car1.getEngine());
        assertEquals(UserClassEnum.CEO, car1.getUserClass());
        assertEquals("xxx123", car1.getLicensePlate());

        // We try change typeOfEngine of car1
        // We test that this operation do not change other parameters
        car1 = carDao.getCarById(car1Id);
        car1.setEngine("5.2");
        carDao.updateCar(car1);
        car1 = carDao.getCarById(car1Id);
        assertEquals("audi x", car1.getBrand());
        assertEquals("A8 x", car1.getModel());
        assertEquals("black x", car1.getColor());
        assertEquals(3000, car1.getMileage());
        assertEquals(Arrays.toString(photo2), Arrays.toString(car1.getPhoto()));
        assertEquals("5.2", car1.getEngine());
        assertEquals(UserClassEnum.CEO, car1.getUserClass());
        assertEquals("xxx123", car1.getLicensePlate());

        // We try change userClass of car1
        // We test that this operation do not change other parameters
        car1 = carDao.getCarById(car1Id);
        car1.setUserClass(UserClassEnum.PRODUCT_MANAGER);
        carDao.updateCar(car1);
        car1 = carDao.getCarById(car1Id);
        assertEquals("audi x", car1.getBrand());
        assertEquals("A8 x", car1.getModel());
        assertEquals("black x", car1.getColor());
        assertEquals(3000, car1.getMileage());
        assertEquals(Arrays.toString(photo2), Arrays.toString(car1.getPhoto()));
        assertEquals("5.2", car1.getEngine());
        assertEquals(UserClassEnum.PRODUCT_MANAGER, car1.getUserClass());
        assertEquals("xxx123", car1.getLicensePlate());

        // We try change vin of car1
        // We test that this operation do not change other parameters
        car1 = carDao.getCarById(car1Id);
        car1.setLicensePlate("xxx789");
        carDao.updateCar(car1);
        car1 = carDao.getCarById(car1Id);
        assertEquals("audi x", car1.getBrand());
        assertEquals("A8 x", car1.getModel());
        assertEquals("black x", car1.getColor());
        assertEquals(3000, car1.getMileage());
        assertEquals(Arrays.toString(photo2), Arrays.toString(car1.getPhoto()));
        assertEquals("5.2", car1.getEngine());
        assertEquals(UserClassEnum.PRODUCT_MANAGER, car1.getUserClass());
        assertEquals("xxx789", car1.getLicensePlate());

        // We try change serviceInterval of car1
        // We test that this operation do not change other parameters
        car1 = carDao.getCarById(car1Id);
        carDao.updateCar(car1);
        car1 = carDao.getCarById(car1Id);
        assertEquals("audi x", car1.getBrand());
        assertEquals("A8 x", car1.getModel());
        assertEquals("black x", car1.getColor());
        assertEquals(3000, car1.getMileage());
        assertEquals(Arrays.toString(photo2), Arrays.toString(car1.getPhoto()));
        assertEquals("5.2", car1.getEngine());
        assertEquals(UserClassEnum.PRODUCT_MANAGER, car1.getUserClass());
        assertEquals("xxx789", car1.getLicensePlate());

        // We test that previous changes of car1 do not change car2
        assertDeepEquals(car2, carDao.getCarById(car2.getId()));
    }

    /**
     * Test of updateCarWithWrongAttributes method, of class CarManagerDaoImpl.
     */
    @Test
    public void testUpdateCarWithWrongAttributes() {
        System.out.println("updateCarWithWrongAttributes");

        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx96456", null);
        carDao.createCar(car);
        Long carId = car.getId();

        try {
            carDao.updateCar(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }

        try {
            car = carDao.getCarById(carId);
            car.setId(null);
            carDao.updateCar(car);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
    }

    /**
     * Test of getCarById method, of class CarManagerDaoImpl.
     */
    @Test
    public void testGetCarById() {
        System.out.println("getCarById");

        Car car = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.CEO, "x8xx4gf56", null);

        carDao.createCar(car);

        Long carId = car.getId();

        Car result = carDao.getCarById(carId);

        assertEquals(car, result);
//        assertNotSame(car, result);
        assertDeepEquals(car, result);
    }

    /**
     * Test of getAllCarForEmployee method, of class CarManagerDaoImpl.
     */
    @Test
    public void testGetAllCarForEmployee() {
        System.out.println("getAllCarForEmployee");

        PhotoConverter photoConverter = new PhotoConverter();
        Byte[] photo1 = photoConverter.convertToByte(pathToPhotos + "bmw7.jpg");
        Byte[] photo2 = photoConverter.convertToByte(pathToPhotos + "audiA8.jpg");

        Employee admin = new Employee();
        admin.setFirstName("Eduard");
        admin.setLastName("Dobai");
        admin.setGender(GenderEnum.MALE);
        admin.setEmail("mail@mail.sk");
        admin.setPhoneNumber("908 068 759");
        admin.setUserClass(UserClassEnum.MANAGER);

        Employee employee = new Employee();
        employee.setFirstName("Michal");
        employee.setLastName("Priezvisko");
        employee.setGender(GenderEnum.MALE);
        employee.setEmail("mail@mail.sk");
        employee.setPhoneNumber("908 068 759");
        employee.setUserClass(UserClassEnum.PRODUCT_MANAGER);

        assertTrue(carDao.getAllCarForEmployee(employee).isEmpty());

        // Car for Manager
        Car car1 = newCar("BMW", "730d", "red", 1500, photo1, "2.0 TDI", UserClassEnum.PRODUCT_MANAGER, "x8xx4gf56", null);
        // Car for Manager
        Car car2 = newCar("AUDI", "A8", "black", 2600, photo2, "4.2", UserClassEnum.PRODUCT_MANAGER, "xdxxa4866", null);
        // Car for Programmer
        Car car3 = newCar("SKODA", "Superb", "white", 5600, null, "2.0 TDI", UserClassEnum.SALES_MANAGER, "hghgf8hff6", null);

        carDao.createCar(car1);
        carDao.createCar(car2);
        carDao.createCar(car3);

        List<CarDTO> actual = carDao.getAllCarForEmployee(employee);
        // This List will not in actual select for Manager
        List<CarDTO> expected2 = Arrays.asList(carConvertDTO(car1), carConvertDTO(car2), carConvertDTO(car3));
        // This List will in actual select only for Admin

        Collections.sort(actual, idComparatorCarTo);

        assertFalse(expected2.equals(actual));
    }

    /**
     * Test of testGetAllCar method, of class CarManagerDaoImpl.
     */
    @Test
    public void testGetAllCar() {
        Car car1 = newCar("BMW", "730d", "red", 1500, null, "2.0 TDI", UserClassEnum.PRODUCT_MANAGER, "x8xx4gf56", null);
        Car car2 = newCar("AUDI", "A8", "black", 2600, null, "4.2", UserClassEnum.PRODUCT_MANAGER, "xdxxa4866", null);
        Car car3 = newCar("SKODA", "Superb", "white", 5600, null, "2.0 TDI", UserClassEnum.SALES_MANAGER, "hghgf8hff6", null);

        carDao.createCar(car1);
        carDao.createCar(car2);
        carDao.createCar(car3);

        List<CarDTO> actual = carDao.getAllCar();
        List<CarDTO> expected2 = Arrays.asList(carConvertDTO(car1), carConvertDTO(car2), carConvertDTO(car3));

        assertNotNull(actual);
        assertTrue(actual.size() == 3);
    }

    public static Car newCar(String brand, String model, String color, int mileage, Byte[] photo, String typeOfEngine, UserClassEnum userClass, String vin, List<ServiceInterval> serviceIntervals) {
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setColor(color);
        car.setMileage(mileage);
        car.setPhoto(photo);
        car.setEngine(typeOfEngine);
        car.setUserClass(userClass);
        car.setLicensePlate(vin);
        car.setServiceInterval(serviceIntervals);

        return car;
    }

    private static CarDTO carConvertDTO(Car car) {
        CarDTO carDTO = new CarDTO(car.getId(), car.getBrand(), car.getModel(), car.getColor(), car.getLicensePlate(), car.getEngine(), car.getMileage(), car.getUserClass(), car.getPhoto(), 0);
        return carDTO;
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

    private void assertDeepEquals(CarDTO expected, CarDTO actual) {
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

    private void assertDeepEquals(List<Car> expectedList, List<Car> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            Car expected = expectedList.get(i);
            Car actual = actualList.get(i);
            assertDeepEquals(expected, actual);
        }
    }

    private void assertDeepEqualsCarTO(List<CarDTO> expectedList, List<CarDTO> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            CarDTO expected = expectedList.get(i);
            CarDTO actual = actualList.get(i);
            assertDeepEquals(expected, actual);
        }
    }
    private static Comparator<Car> idComparator = new Comparator<Car>() {
        @Override
        public int compare(Car o1, Car o2) {
            return Long.valueOf(o1.getId()).compareTo(Long.valueOf(o2.getId()));
        }
    };
    private static Comparator<CarDTO> idComparatorCarTo = new Comparator<CarDTO>() {
        @Override
        public int compare(CarDTO o1, CarDTO o2) {
            return Long.valueOf(o1.getId()).compareTo(Long.valueOf(o2.getId()));
        }
    };

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
