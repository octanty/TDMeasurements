package cz.muni.fi.pa165.fleetmanagement.dao.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.EmployeeDao;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import cz.muni.fi.pa165.fleetmanagement.enums.GenderEnum;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lubomir Lacika
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class EmployeeDaoImplTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of createEmployee method, of class EmployeeDaoImpl.
     */
    @Test
    public void testCreateEmployee() {
        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);

        employeeDao.createEmployee(employee);

        Long employeeId = employee.getId();
        assertNotNull(employeeId);
        Employee result = employeeDao.getEmployeeById(employeeId);

        System.err.println(result.getId() + " - " + employee.getId());
        System.err.println(result.getFirstName() + " - " + employee.getFirstName());
        System.err.println(result.getLastName() + " - " + employee.getLastName());
        System.err.println(result.getEmail() + " - " + employee.getEmail());
        System.err.println(result.getGender() + " - " + employee.getGender());
        System.err.println(result.getPhoneNumber() + " - " + employee.getPhoneNumber());
        System.err.println(result.getUserClass() + " - " + employee.getUserClass());

        assertEquals(employee, result);
        //  assertNotSame(employee, result);
        assertDeepEquals(employee, result);
    }

    @Test
    public void testCreateEmployeeWithWrongAttributes() {

        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);
        try {
            employeeDao.createEmployee(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //ok
        }

        try {
            employee.setId(1L);
            employeeDao.createEmployee(employee);
            fail();
        } catch (IllegalArgumentException ex) {
            //ok
        }
    }

    /**
     * Test of deleteEmployee method, of class EmployeeDaoImpl.
     */
    @Test
    public void testDeleteEmployee() {
        Employee employee = newEmployee("Martin", "Petovsky", "email@google.com", GenderEnum.MALE, "4569877", UserClassEnum.CEO);
        Employee employee1 = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);

        employeeDao.createEmployee(employee);
        employeeDao.createEmployee(employee1);

        assertNotNull(employeeDao.getEmployeeById(employee.getId()));
        assertNotNull(employeeDao.getEmployeeById(employee1.getId()));
        employeeDao.deleteEmployee(employee);

        assertNull(employeeDao.getEmployeeById(employee.getId()));
        assertNotNull(employeeDao.getEmployeeById(employee1.getId()));
    }

    @Test
    public void testDeleteEmployeeWithWrongAttributes() {
        Employee employee = newEmployee("Lubomira", "Lacikova", "email@email.com", GenderEnum.FEMALE, "09090909", UserClassEnum.CEO);
        employeeDao.createEmployee(employee);
        Long employeeID = employee.getId();

        try {
            employeeDao.deleteEmployee(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            employee = employeeDao.getEmployeeById(employeeID);
            employee.setId(null);
            employeeDao.deleteEmployee(employee);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of updateEmployee method, of class EmployeeDaoImpl.
     */
    @Test
    public void testUpdateEmployee() {
        Employee employee = newEmployee("Lubomira", "Lacikova", "email@email.com", GenderEnum.FEMALE, "09090909", UserClassEnum.CEO);
        Employee employee1 = newEmployee("Mariana", "Mazanoca", "gx@email.com", GenderEnum.FEMALE, "020202", UserClassEnum.SALES_MANAGER);

        employeeDao.createEmployee(employee);
        employeeDao.createEmployee(employee1);

        Long employeeID = employee.getId();

        employee = employeeDao.getEmployeeById(employeeID);
        employee.setFirstName("Lubomir");
        employee.setLastName("Lacika");
        employee.setEmail("email@email.com");
        employee.setGender(GenderEnum.FEMALE);
        employee.setPhoneNumber("09090909");
        employee.setUserClass(UserClassEnum.MANAGER);

        employeeDao.updateEmployee(employee);

        employee = employeeDao.getEmployeeById(employeeID);
        assertEquals("Lubomir", employee.getFirstName());
        assertEquals("Lacika", employee.getLastName());
        assertEquals("email@email.com", employee.getEmail());
        assertEquals(GenderEnum.FEMALE, employee.getGender());
        assertEquals("09090909", employee.getPhoneNumber());
        assertEquals(UserClassEnum.MANAGER, employee.getUserClass());

        assertDeepEquals(employee1, employeeDao.getEmployeeById(employee1.getId()));
    }

    @Test
    public void testUpdateEmployeeWithWrongAttributes() {
        Employee employee = newEmployee("Lubomira", "Lacikova", "email@email.com", GenderEnum.FEMALE, "09090909", UserClassEnum.CEO);
        employeeDao.createEmployee(employee);
        Long employeeID = employee.getId();
        try {
            employeeDao.updateEmployee(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            employee = employeeDao.getEmployeeById(employeeID);
            employee.setId(null);
            employeeDao.updateEmployee(employee);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    /**
     * Test of getAllEmployee method, of class EmployeeDaoImpl.
     */
    @Test
    public void testGetAllEmployee() {
        assertTrue(employeeDao.getAllEmployee().isEmpty());
        Employee employee = newEmployee("Martin", "Petovsky", "email@google.com", GenderEnum.MALE, "4569877", UserClassEnum.CEO);
        Employee employee1 = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);

        employeeDao.createEmployee(employee);
        employeeDao.createEmployee(employee1);

        List<Employee> expected = Arrays.asList(employee, employee1);
        List<Employee> actual = employeeDao.getAllEmployee();

        Collections.sort(actual, idComparator);
        Collections.sort(expected, idComparator);

        assertEquals(expected, actual);
        assertDeepEquals(expected, actual);
    }

    /**
     * Test of getEmployeeById method, of class EmployeeDaoImpl.
     */
    @Test
    public void testGetEmployeeById() {
        assertNull(employeeDao.getEmployeeById(1l));
        Employee employee = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);

        employeeDao.createEmployee(employee);
        long employeeId = employee.getId();

        Employee result = employeeDao.getEmployeeById(employeeId);
        assertEquals(employee, result);
        assertDeepEquals(employee, result);
    }

    public static Employee newEmployee(String firstName, String lastName, String email, GenderEnum gender, String phoneNumber, UserClassEnum userClass) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setGender(gender);
        employee.setPhoneNumber(phoneNumber);
        employee.setUserClass(userClass);
        return employee;
    }

    private void assertDeepEquals(List<Employee> expectedList, List<Employee> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            Employee expected = expectedList.get(i);
            Employee actual = actualList.get(i);
            assertDeepEquals(expected, actual);
        }
    }

    private void assertDeepEquals(Employee expected, Employee actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getGender(), actual.getGender());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        assertEquals(expected.getUserClass(), actual.getUserClass());
    }
    private static Comparator<Employee> idComparator = new Comparator<Employee>() {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Long.valueOf(o1.getId()).compareTo(Long.valueOf(o2.getId()));
        }
    };
}
