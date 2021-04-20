/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.validators;

import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Ján Švec
 */
public class EmployeeFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.employee.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.employee.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.employee.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "error.employee.phoneNumber");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.employee.password");
    }
}
