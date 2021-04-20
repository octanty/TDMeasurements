package cz.muni.fi.pa165.fleetmanagement.dao;

import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import java.util.List;

/**
 *
 * @author Eduard Dobai (420065)
 * @version 1.0
 */
public interface EmployeeDao {

    /**
     * Saves given employee into database.
     *
     * @param employee Employee to save
     * @throws IllegalArgumentException Throws if employee is null.
     * @throws DataAccessException Throws if a data access exception occurred.
     */
    void createEmployee(Employee employee);

    /**
     * Removes employee from database.
     *
     * @param employee Employee to remove
     * @throws IllegalArgumentException Throws if employee is null.
     * @throws DataAccessException Throws if a data access exception occurred.
     */
    void deleteEmployee(Employee employee);

    /**
     * Updates given employee in database.
     *
     * @param employee Employee to update
     * @throws IllegalArgumentException Throws if employee is null.
     * @throws DataAccessException Throws if a data access exception occurred.
     */
    void updateEmployee(Employee employee);

    /**
     * Find and return all employee in database.
     *
     * @return List of all employees.
     * @throws DataAccessException Throws if a data access exception occurred.
     */
    List<Employee> getAllEmployee();

    /**
     * Returns employee with id. Returns null if employee does not exists.
     *
     * @param id ID of employee
     * @return EmployeeDTO if Employee with id exists, null otherwise.
     * @throws IllegalArgumentException Throws if id is null.
     * @throws DataAccessException Throws if a data access exception occurred.
     */
    Employee getEmployeeById(long id);
    Employee findEmployeeByName(String employeeName);
    Employee findEmployeeByEmail(String employeeEmail);
}