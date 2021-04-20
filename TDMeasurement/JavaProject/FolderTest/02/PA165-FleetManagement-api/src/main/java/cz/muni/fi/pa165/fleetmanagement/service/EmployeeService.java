package cz.muni.fi.pa165.fleetmanagement.service;

import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import java.util.List;

/**
 *
 * @author Lubomir Lacika (418110)
 */
public interface EmployeeService {

    /**
     * Saves given employee into database.
     *
     * @param employee Employee to save
     * @throws IllegalArgumentException Throws if employee is null.
     * @throws DataAccessException Throws if a data access exception occurred.
     */
    void createEmployee(EmployeeDTO employee);

    /**
     * Removes employee from database.
     *
     * @param employee Employee to remove
     * @throws IllegalArgumentException Throws if employee is null.
     * @throws DataAccessException Throws if a data access exception occurred.
     */
    public void deleteEmployee(EmployeeDTO employee);

    /**
     * Updates given employee in database.
     *
     * @param employee Employee to update
     * @throws IllegalArgumentException Throws if employee is null.
     * @throws DataAccessException Throws if a data access exception occurred.
     */
    public void updateEmployee(EmployeeDTO employee);

    /**
     * Find and return all employee in database.
     *
     * @return List of all employees.
     * @throws DataAccessException Throws if a data access exception occurred.
     */
    public List<EmployeeDTO> getAllEmployee();

    /**
     * Returns employee with id. Returns null if employee does not exists.
     *
     * @param id ID of employee
     * @return EmployeeDTO if Employee with id exists, null otherwise.
     * @throws IllegalArgumentException Throws if id is null.
     * @throws DataAccessException Throws if a data access exception occurred.
     */
    public EmployeeDTO getEmployeeById(long id);
    
    public EmployeeDTO findEmployeeByName(String employeeName);
    /**
     *
     * @param employeeEmail
     * @return EMployee email.
     */
    public EmployeeDTO findEmployeeByEmail(String employeeEmail);
}
