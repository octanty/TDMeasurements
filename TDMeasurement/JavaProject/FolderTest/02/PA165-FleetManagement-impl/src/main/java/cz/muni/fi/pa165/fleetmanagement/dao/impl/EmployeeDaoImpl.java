package cz.muni.fi.pa165.fleetmanagement.dao.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.EmployeeDao;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ján Švec
 * @version 1.0
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Add Employee to Database
     *
     * @param employee
     */
    @Override
    public void createEmployee(Employee employee) throws DataAccessException {
        validate(employee);
        if (employee.getId() != null) {
            throw new IllegalArgumentException("Employee id is already set");
        }
        entityManager.persist(employee);
    }

    /**
     * Delete Employee from Database
     *
     * @param employee
     */
    @Override
    public void deleteEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee is null");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee id is null");
        }
        Employee deleteEmp = entityManager.find(Employee.class, employee.getId());
        entityManager.remove(deleteEmp);
    }

    /**
     * Update Employee in Database
     *
     * @param employee
     */
    @Override
    public void updateEmployee(Employee employee) {
        validate(employee);
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee id is null");
        }
        entityManager.merge(employee);
        entityManager.flush();
    }

    /**
     * @return List of Service intervals
     */
    @Override
    public List<Employee> getAllEmployee() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> employee = cq.from(Employee.class);
        cq.select(employee);
        TypedQuery<Employee> q = entityManager.createQuery(cq);
        List<Employee> allEmployee = q.getResultList();
        return allEmployee;
    }

    /**
     *
     * @return Employee by id from database
     * @param id id of employee
     */
    @Override
    public Employee getEmployeeById(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Id employee is a less than 1");
        }
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    /**
     * Validates employee
     *
     * @param employee
     */
    private void validate(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee is null");
        }
        if (employee.getFirstName() == null) {
            throw new IllegalArgumentException("First name is null");
        }
        if (employee.getLastName() == null) {
            throw new IllegalArgumentException("employee is null");
        }
        if (employee.getEmail() == null) {
            throw new IllegalArgumentException("Email is null");
        }
        if (employee.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Phone number is null");
        }
        if (employee.getGender() == null) {
            throw new IllegalArgumentException("Gender is not selected");
        }
        if (employee.getUserClass() == null) {
            throw new IllegalArgumentException("User class is not selected");
        }
    }
      @Override
    public Employee findEmployeeByName(String firstName) {
    Employee employee = entityManager.find(Employee.class, firstName);
        return employee;    
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        Employee employee = entityManager.find(Employee.class, email);
        return employee;
    }
}