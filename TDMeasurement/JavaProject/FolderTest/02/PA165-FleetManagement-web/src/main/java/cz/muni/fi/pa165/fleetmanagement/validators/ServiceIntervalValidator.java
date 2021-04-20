package cz.muni.fi.pa165.fleetmanagement.validators;

import cz.muni.fi.pa165.fleetmanagement.dto.ServiceIntervalDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Ján Švec
 */
public class ServiceIntervalValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ServiceIntervalDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "service", "error.serviceInterval.service");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mileageControl", "error.serviceInterval.mileageControl");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateControl", "error.serviceInterval.vehicle.dateControl");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "completionDate", "error.serviceInterval.vehicle.completionDate");

//        ServiceIntervalDTO serviceInterval = (ServiceIntervalDTO) target;
//        if (serviceInterval.getInspectionInterval() <= 0) {
//            errors.rejectValue("inspectionInterval", "error.serviceInterval.inspectionInterval.notPositive");
//        }

    }
}
