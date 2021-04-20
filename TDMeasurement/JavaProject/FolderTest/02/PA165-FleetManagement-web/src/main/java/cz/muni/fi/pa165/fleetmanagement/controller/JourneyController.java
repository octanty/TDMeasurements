package cz.muni.fi.pa165.fleetmanagement.controller;

import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.JourneyDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.editors.CarEditor;
import cz.muni.fi.pa165.fleetmanagement.editors.DateEditor;
import cz.muni.fi.pa165.fleetmanagement.editors.EmployeeEditor;
import cz.muni.fi.pa165.fleetmanagement.service.CarService;
import cz.muni.fi.pa165.fleetmanagement.service.EmployeeService;
import cz.muni.fi.pa165.fleetmanagement.service.JourneyService;
import cz.muni.fi.pa165.fleetmanagement.validators.JourneyFormValidator;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
public class JourneyController {

    @Autowired
    private JourneyService journeyService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CarService carService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new JourneyFormValidator());
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(EmployeeDTO.class, new EmployeeEditor(employeeService));
        binder.registerCustomEditor(CarDTO.class, new CarEditor(carService));
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @RequestMapping(value = "/journey/list", method = RequestMethod.GET)
    public String viewAll(ModelMap model, HttpSession session) {
        model.put("journeys", journeyService.getAllJourneys());
        return "/journey/journeyList";
    }

    @RequestMapping(value = "/journey/new", method = RequestMethod.GET)
    public String showNewForm(ModelMap model, HttpSession session) {
        model.put("journeyDTO", new JourneyDTO());
        model.put("employees", employeeService.getAllEmployee());       
        model.put("cars", carService.getAllCar());
        return "/journey/journeyForm";
    }

    @RequestMapping(value = "/journey/new", method = RequestMethod.POST)
    public String createNew(@Validated JourneyDTO newJourney, BindingResult result, ModelMap model, HttpSession session) {
        if (result.hasErrors()) {
            model.put("employees", employeeService.getAllEmployee());
            model.put("cars", carService.getAllCar());
            return "/journey/journeyForm";
        }
        journeyService.createJourney(newJourney);
        session.setAttribute("message", "journey.msg.create.successful");
        return "redirect:/journey/list";
    }

    @RequestMapping(value = "/journey/edit/{id}", method = RequestMethod.GET)
    public String showEditForm(@PathVariable("id") Long id, ModelMap model, HttpSession session) {
        JourneyDTO journeyToEdit = journeyService.getJourneyById(id);
        if (journeyToEdit == null) {
            session.setAttribute("error", "journey.msg.edit.unexists");
            return "redirect:/journey/list";
        }
        model.put("journeyDTO", journeyToEdit);
        model.put("employees", employeeService.getAllEmployee());
        model.put("cars", carService.getAllCar());
        return "/journey/journeyForm";
    }

    @RequestMapping(value = "/journey/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, @Validated @ModelAttribute("journey") JourneyDTO journeyForm, BindingResult result,
            ModelMap model, HttpSession session) {
        if (result.hasErrors()) {
            model.put("employees", employeeService.getAllEmployee()); 
            model.put("cars", carService.getAllCar());
            return "/journey/journeyForm";
        }
        if (id == null || journeyForm.getId() == null || !id.equals(journeyForm.getId())) {
            session.setAttribute("error", "journey.msg.edit.wrongId");
            model.put("employees", employeeService.getAllEmployee());
            model.put("cars", carService.getAllCar());
            return "/journey/journeyForm";
        }
        journeyService.updateJourney(journeyForm);
        session.setAttribute("message", "journey.msg.edit.successful");
        return "redirect:/journey/list";
    }

    @RequestMapping(value = "/journey/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, ModelMap model, HttpSession session) {
        JourneyDTO journeyToDelete = journeyService.getJourneyById(id);

        if (journeyToDelete == null) {
            session.setAttribute("error", "journey.msg.delete.unexists");
        } else {
            journeyService.deleteJourney(journeyToDelete);
            session.setAttribute("message", "journey.msg.delete.successful");
        }
        return "redirect:/journey/list";
    }
}