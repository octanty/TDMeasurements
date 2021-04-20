package cz.muni.fi.pa165.fleetmanagement.service.impl;

import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.dao.EmployeeDao;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import cz.muni.fi.pa165.fleetmanagement.enums.GenderEnum;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import cz.muni.fi.pa165.fleetmanagement.exception.ServiceDataAccesException;
import cz.muni.fi.pa165.fleetmanagement.utils.EntityToDTOConvertor;

import java.util.Arrays;
import java.util.List;

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
 * @author Lubomir Lacika
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;
    private EmployeeDao mockedEmployeeDao;
    private Employee employee1;
    private EmployeeDTO employeeDTO1;
    private Employee employee2;
    private EmployeeDTO employeeDTO2;

    @Before
    public void setUp() throws Exception {

        employeeService = new EmployeeServiceImpl();
        mockedEmployeeDao = mock(EmployeeDao.class);
        employeeService.setEmployeeDao(mockedEmployeeDao);


        employee1 = newEmployee("Lubomir", "Lacika", "email@email.com", GenderEnum.MALE, "09090909", UserClassEnum.CEO);
        employeeDTO1 = EntityToDTOConvertor.toDTO(employee1);

        employee2 = newEmployee("Martin", "Petovsky", "email@google.com", GenderEnum.MALE, "4569877", UserClassEnum.CEO);
        employeeDTO2 = EntityToDTOConvertor.toDTO(employee2);

        employee1.setId(1L);
        employee2.setId(2L);

        employeeDTO1.setId(1L);
        employeeDTO2.setId(2L);

        // testGetAllEmployee
        when(mockedEmployeeDao.getAllEmployee()).thenReturn(Arrays.asList(employee1, employee2));
        doThrow(ServiceDataAccesException.class).when(mockedEmployeeDao).getAllEmployee();

        // testGetEmployeeById
        when(mockedEmployeeDao.getEmployeeById(1L)).thenReturn(employee1);
        when(mockedEmployeeDao.getEmployeeById(2L)).thenReturn(employee2);
        when(mockedEmployeeDao.getEmployeeById(3L)).thenReturn(null);


        // testUpdateEmployee
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Employee update = (Employee) args[0];
                employee2.setId(update.getId());
                employee2.setFirstName(update.getFirstName());
                employee2.setLastName(update.getLastName());
                employee2.setEmail(update.getEmail());
                employee2.setGender(update.getGender());
                employee2.setPhoneNumber(update.getPhoneNumber());
                employee2.setUserClass(update.getUserClass());
                return null;
            }
        }).when(mockedEmployeeDao).updateEmployee(employee1);
        doThrow(ServiceDataAccesException.class).when(mockedEmployeeDao).updateEmployee(null);

        // testCreateEmployee
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Employee employee = (Employee) args[0];
                employee.setId(1L);
                return null;
            }
        }).when(mockedEmployeeDao).createEmployee(employee1);

        doThrow(ServiceDataAccesException.class).when(mockedEmployeeDao).createEmployee(null);

        //testDeleteEmployee
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Employee employee = (Employee) args[0];
                employee.setId(null);
                return null;
            }
        }).when(mockedEmployeeDao).deleteEmployee(employee1);
        doThrow(ServiceDataAccesException.class).when(mockedEmployeeDao).deleteEmployee(null);

    }

    /**
     * Test of createEmployee method, of class EmployeeServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testCreateEmployee() {
        employeeService.createEmployee(employeeDTO1);
        assertNotNull(employeeDTO1.getId());

        employeeService.createEmployee(null);

    }

    /**
     * Test of deleteEmployee method, of class EmployeeServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testDeleteEmployee() {
        employeeService.deleteEmployee(employeeDTO1);
        assertNotNull(employee1.getId());

        employeeService.deleteEmployee(null);
    }

    /**
     * Test of updateEmployee method, of class EmployeeServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testUpdateEmployee() {
        employeeService.updateEmployee(employeeDTO1);
        assertDeepEquals(employee1, employee2);

        employeeService.updateEmployee(null);
    }

    /**
     * Test of getAllEmployee method, of class EmployeeServiceImpl.
     */
    @Test(expected = ServiceDataAccesException.class)
    public void testGetAllEmployee() {
        assertNotNull(employeeService.getAllEmployee());
        assertEquals(employeeService.getAllEmployee(), Arrays.asList(employeeDTO1, employeeDTO2));

    }

    /**
     * Test of getEmployeeById method, of class EmployeeServiceImpl.
     */
    @Test
    public void testGetEmployeeById() {
        assertNotNull(employeeService.getEmployeeById(1L));
        assertEquals(employeeService.getEmployeeById(1L), employeeDTO1);

        assertNotNull(employeeService.getEmployeeById(2L));
        assertEquals(employeeService.getEmployeeById(2L), employeeDTO2);
        assertNull(employeeService.getEmployeeById(3L));

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
}
