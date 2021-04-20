package cz.muni.fi.pa165.fleetmanagement.controller;

import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import cz.muni.fi.pa165.fleetmanagement.service.CarService;
import cz.muni.fi.pa165.fleetmanagement.service.ServiceIntervalService;
import cz.muni.fi.pa165.fleetmanagement.validators.CarFormValidator;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CarController {

    @Autowired
    private CarService carService;
 
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CarFormValidator());
    }

    @RequestMapping(value = "/car/list", method = RequestMethod.GET)
    public String viewAll(ModelMap model, HttpSession session) {
        model.put("cars", carService.getAllCar());
        return "/car/carList";
    }

    @RequestMapping(value = "/car/new", method = RequestMethod.GET)
    public String showNewForm(ModelMap model, HttpSession session) {
        model.put("carDTO", new CarDTO());
        model.put("userClass", UserClassEnum.values());
        return "/car/carForm";
    }

    @RequestMapping(value = "/car/new", method = RequestMethod.POST)
    public String createNew(@Validated CarDTO newCar, BindingResult result, ModelMap model, HttpSession session) {
        if (result.hasErrors()) {
            model.put("userClass", UserClassEnum.values());
            return "/car/carForm";
        }
        carService.createCar(newCar);
        session.setAttribute("message", "car.msg.create.successful");
        return "redirect:/car/list";
    }

    @RequestMapping(value = "/car/view/{id}", method = RequestMethod.GET)
    public String viewCar(@PathVariable("id") Long id, ModelMap model, HttpSession session) {

        CarDTO car = carService.getCarById(id);
        model.put("car", car);

        return "/car/carView";
    }

    @RequestMapping(value = "/car/edit/{id}", method = RequestMethod.GET)
    public String showEditForm(@PathVariable("id") Long id, ModelMap model, HttpSession session) {
        CarDTO carToEdit = carService.getCarById(id);
        if (carToEdit == null) {
            session.setAttribute("error", "car.msg.edit.unexists");
            return "redirect:/car/list";
        }
        model.put("carDTO", carToEdit);
        model.put("userClass", UserClassEnum.values());
        return "/car/carForm";
    }

    @RequestMapping(value = "/car/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, @Validated @ModelAttribute("car") CarDTO carForm, BindingResult result,
            ModelMap model, HttpSession session) {
        if (result.hasErrors()) {
            model.put("userClass", UserClassEnum.values());
            return "/car/carForm";
        }
        if (id == null || carForm.getId() == null || !id.equals(carForm.getId())) {
            session.setAttribute("error", "car.msg.edit.wrongId");
            model.put("userClass", UserClassEnum.values());
            return "/car/carForm";
        }
        carService.updateCar(carForm);
        session.setAttribute("message", "car.msg.edit.successful");
        return "redirect:/car/list";
    }

    @RequestMapping(value = "/car/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, ModelMap model, HttpSession session) {
        CarDTO carToDelete = carService.getCarById(id);

        if (carToDelete == null) {
            session.setAttribute("error", "car.msg.delete.unexists");
        } else {
            carService.deleteCar(carToDelete);
            session.setAttribute("message", "car.msg.delete.successful");
        }
        return "redirect:/car/list";
    }
}