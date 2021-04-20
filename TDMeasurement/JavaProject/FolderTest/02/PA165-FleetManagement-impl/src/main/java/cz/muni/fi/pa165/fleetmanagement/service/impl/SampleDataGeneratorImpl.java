/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.service.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.EmployeeDao;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import cz.muni.fi.pa165.fleetmanagement.enums.GenderEnum;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import cz.muni.fi.pa165.fleetmanagement.service.SampleDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generator of sample data, will not be used in production
 * @author Ján Švec
 */
@Service
public class SampleDataGeneratorImpl implements SampleDataGenerator {

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    @Override
    public void generateSampleData() {
        if (employeeDao.getAllEmployee().isEmpty()) {
            Employee newAdmin = new Employee();
            newAdmin.setFirstName("jan");
            newAdmin.setLastName("svec");
            newAdmin.setPassword("admin");
            newAdmin.setEmail("svec.jano@gmail.com");
            newAdmin.setGender(GenderEnum.MALE);
            newAdmin.setUserClass(UserClassEnum.CEO);
            newAdmin.setPhoneNumber("0946504299");
            employeeDao.createEmployee(newAdmin);
        }
    }
}
