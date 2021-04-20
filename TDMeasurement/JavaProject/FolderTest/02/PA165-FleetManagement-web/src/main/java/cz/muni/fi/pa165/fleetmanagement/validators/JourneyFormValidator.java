/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.validators;

import cz.muni.fi.pa165.fleetmanagement.dto.JourneyDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Ján Švec
 */
public class JourneyFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return JourneyDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mileage", "error.journey.mileage");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pickUpDate", "error.journey.pickUpDate");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "returnDate", "error.journey.returnDate");
        JourneyDTO journey = (JourneyDTO) target;
        
        if (journey.getEmployee() == null) {
            errors.reject("error.journey.employee");
        }

        if (journey.getCar() == null) {
            errors.reject("error.journey.car");
        }

//        dorobit porovnavanie datumov
//        if (journey.getDateFrom() != null && journey.getDateTo() != null && journey.getDateFrom().isAfter(journey.getDateTo())) {
//            errors.rejectValue("dateTo", "error.journey.dateTo.soon");
//        }
//
//   
//        if (journey.getDistance() != null) {
//            Integer dis = journey.getDistance();
//            if (dis.intValue() < 0) {
//                errors.rejectValue("distance", "error.journey.distance.low");
//            }
//        }

    }
}
