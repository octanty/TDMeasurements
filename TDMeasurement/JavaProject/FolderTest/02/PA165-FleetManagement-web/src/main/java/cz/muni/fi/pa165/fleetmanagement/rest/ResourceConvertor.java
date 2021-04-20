/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.rest;

import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.ServiceIntervalDTO;

/**
 *
 * @author Peter Pavlik
 */
public class ResourceConvertor {

    public static EmployeeDTO fromEmployeeResource(EmployeeResource resource) {
        if (resource == null) {
            return null;
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmail(resource.getEmail());
        employeeDTO.setFirstName(resource.getFirstName());
        employeeDTO.setGender(resource.getGender());
        employeeDTO.setId(resource.getId());
        employeeDTO.setLastName(resource.getLastName());
        employeeDTO.setPassword(resource.getPassword());
        employeeDTO.setPhoneNumber(resource.getPhoneNumber());
        employeeDTO.setUserClass(resource.getUserClass());
        return employeeDTO;
    }

    public static EmployeeResource fromEmployeeTo(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }
        EmployeeResource employeeResource = new EmployeeResource();
        employeeResource.setEmail(employeeDTO.getEmail());
        employeeResource.setFirstName(employeeDTO.getFirstName());
        employeeResource.setGender(employeeDTO.getGender());
        employeeResource.setId(employeeDTO.getId());
        employeeResource.setLastName(employeeDTO.getLastName());
        employeeResource.setPassword(employeeDTO.getPassword());
        employeeResource.setPhoneNumber(employeeDTO.getPhoneNumber());
        employeeResource.setUserClass(employeeDTO.getUserClass());

        return employeeResource;
    }

    public static ServiceIntervalDTO fromServiceIntervalResource(ServiceIntervalResource resource) {
        if (resource == null) {
            return null;
        }
        ServiceIntervalDTO serviceIntervalDTO = new ServiceIntervalDTO();
        serviceIntervalDTO.setCompletionDate(resource.getCompletionDate());
        serviceIntervalDTO.setDateControl(resource.getDateControl());
        serviceIntervalDTO.setId(resource.getId());
        serviceIntervalDTO.setMileageControl(resource.getMileageControl());
        serviceIntervalDTO.setService(resource.getService());

        return serviceIntervalDTO;
    }

    public static ServiceIntervalResource fromServiceIntervalTo(ServiceIntervalDTO serviceIntervalDTO) {
        if (serviceIntervalDTO == null) {
            return null;
        }
        ServiceIntervalResource serviceIntervalResource = new ServiceIntervalResource();
        serviceIntervalResource.setCompletionDate(serviceIntervalDTO.getCompletionDate());
        serviceIntervalResource.setDateControl(serviceIntervalDTO.getDateControl());
        serviceIntervalResource.setId(serviceIntervalDTO.getId());
        serviceIntervalResource.setMileageControl(serviceIntervalDTO.getMileageControl());
        serviceIntervalResource.setService(serviceIntervalDTO.getService());

        return serviceIntervalResource;
    }
}
