package cz.muni.fi.pa165.fleetmanagement.service.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.EmployeeDao;
import cz.muni.fi.pa165.fleetmanagement.dao.impl.EmployeeDaoImpl;
import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import cz.muni.fi.pa165.fleetmanagement.exception.ServiceDataAccesException;
import cz.muni.fi.pa165.fleetmanagement.service.EmployeeService;
import cz.muni.fi.pa165.fleetmanagement.utils.EntityToDTOConvertor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lubomír Lacika
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional
    @Override
    public void createEmployee(EmployeeDTO employee) {
        try {
            employeeDao.createEmployee(EntityToDTOConvertor.toEntity(employee));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public void deleteEmployee(EmployeeDTO employee) {
        try {
            employeeDao.deleteEmployee(EntityToDTOConvertor.toEntity(employee));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public void updateEmployee(EmployeeDTO employee) {
        try {
            employeeDao.updateEmployee(EntityToDTOConvertor.toEntity(employee));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public List<EmployeeDTO> getAllEmployee() {
        try {
            List<Employee> employees = employeeDao.getAllEmployee();
            List<EmployeeDTO> dtos = new ArrayList<EmployeeDTO>();

            for (Employee employee : employees) {
                dtos.add((EntityToDTOConvertor.toDTO(employee)));
            }
            return dtos;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public EmployeeDTO getEmployeeById(long id) {
        try {
            Employee employee = employeeDao.getEmployeeById(id);
            return EntityToDTOConvertor.toDTO(employee);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }
      @Override
    public EmployeeDTO findEmployeeByName(String employeeName) {
        try {
            Employee employee = employeeDao.findEmployeeByEmail(employeeName);
            return EntityToDTOConvertor.toDTO(employee);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        } 
    }

    @Override
    public EmployeeDTO findEmployeeByEmail(String employeeEmail) {
    try {
            Employee employee = employeeDao.findEmployeeByName(employeeEmail);
            return EntityToDTOConvertor.toDTO(employee);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }
}
