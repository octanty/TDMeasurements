package cz.muni.fi.pa165.fleetmanagement.validators;

import cz.muni.fi.pa165.fleetmanagement.dto.JourneyDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.Journey;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Ján Švec
 */
public class JourneyFinishedValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return JourneyDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "mileage", "error.journey.mileage");
        Journey jorurney = (Journey) obj;
        
        // distance
        if (jorurney.getMileage() < 0) {
            errors.rejectValue("mileage", "error.journey.mileage.ltZero");
        }


    }

}
