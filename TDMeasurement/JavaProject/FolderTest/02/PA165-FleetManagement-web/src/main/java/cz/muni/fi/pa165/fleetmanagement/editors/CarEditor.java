package cz.muni.fi.pa165.fleetmanagement.editors;

import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.service.CarService;
import java.beans.PropertyEditorSupport;

/**
 * @author Ján Švec
 */
public class CarEditor extends PropertyEditorSupport {

    private CarService carService;

    public CarEditor(CarService carService) {
        super();
        this.carService = carService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        CarDTO carDTO = carService.getCarById((long) Integer.parseInt(text));
        setValue(carDTO);
    }

    @Override
    public String getAsText() {
        CarDTO car = (CarDTO) getValue();
        if (car == null) {
            return null;
        } else {
            return car.getId().toString();
        }
    }
}
