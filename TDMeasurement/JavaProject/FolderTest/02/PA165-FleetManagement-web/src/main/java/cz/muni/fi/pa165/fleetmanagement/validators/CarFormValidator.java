/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.validators;

import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Ján Švec
 */
public class CarFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CarDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brand", "error.car.brand");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "error.car.model");
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "licensePlate", "error.car.licensePlate");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "engine", "error.car.engine");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "error.car.color");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userClass", "error.car.userClass");

        CarDTO car = (CarDTO) target;
        if (car.getMileage() < 0) {
            errors.rejectValue("mileage", "error.car.mileage");
        }

    }
}
