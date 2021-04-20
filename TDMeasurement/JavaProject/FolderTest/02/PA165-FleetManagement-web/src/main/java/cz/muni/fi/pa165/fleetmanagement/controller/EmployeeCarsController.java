/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.controller;

import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.JourneyDTO;
import cz.muni.fi.pa165.fleetmanagement.editors.DateEditor;
import cz.muni.fi.pa165.fleetmanagement.service.CarService;
import cz.muni.fi.pa165.fleetmanagement.service.EmployeeService;
import cz.muni.fi.pa165.fleetmanagement.service.JourneyService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Ján Švec
 */
@Controller
public class EmployeeCarsController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CarService carService;
    @Autowired
    private JourneyService journeyService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET, params = {"!from", "!to"})
    public String userCars(ModelMap model, HttpSession session) {
        model.addAttribute("cars", new ArrayList<CarDTO>());
        return "cars";
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET, params = {"from", "to"})
    public String userCarsFiltred(@RequestParam("from") String from,
            @RequestParam("to") String to,
            Principal principal,
            ModelMap model, HttpSession session) {

        if (from == null && to == null) {
            model.addAttribute("cars", new ArrayList<CarDTO>());
        } else if ((from == null && to != null) || (from != null && to == null)) {
            session.setAttribute("error", "employee.cars.err.bothDates");
            model.addAttribute("cars", new ArrayList<CarDTO>());
            model.addAttribute("dateFrom", from);
            model.addAttribute("dateTo", to);
        } else {
            DateTime dateFrom = new DateTime(from);
            DateTime dateTo = new DateTime(to);
            model.addAttribute("dateFrom", from);
            model.addAttribute("dateTo", to);

            if (dateFrom.isAfter(dateTo)) {
                session.setAttribute("error", "employee.cars.err.changedDates");
                return "cars";
            }

            EmployeeDTO employee = this.employeeService.findEmployeeByEmail(principal.getName());
            List<CarDTO> cars = carService.getAllCar();
            model.addAttribute("cars", cars);

        }
        return "cars";
    }

    @RequestMapping(value = "/reserveForm", method = RequestMethod.GET)
    public String reserveCar(@RequestParam("id") Long carId, @RequestParam("from") DateTime from, @RequestParam("to") DateTime to, Principal principal, ModelMap model, HttpSession session) {

        if (from == null || to == null || carId == null) {
            session.setAttribute("error", "employee.cars.err.wrongParams");
            return "redirect:/cars";
        }

        EmployeeDTO employee = employeeService.findEmployeeByEmail(principal.getName());
        CarDTO car = carService.getCarById(carId);

        if (car == null || employee == null
                || !car.getUserClass().equals(employee.getUserClass())) {
            session.setAttribute("error", "employee.cars.err.wrongParams");
            return "redirect:/cars";
        }

        List<CarDTO> availableCars = carService.getAllCarForEmployee(employee);
        if (!availableCars.contains(car)) {
            session.setAttribute("error", "employee.cars.err.wrongParams");
            return "redirect:/cars";
        }

        JourneyDTO journey = new JourneyDTO();
        journey.setEmployee(employee);
        journey.setCar(car);
        journey.setPickUpDate(from.toDate());
        journey.setReturnDate(to.toDate());

        journeyService.createJourney(journey);

        return "redirect:/cars";
    }

    @RequestMapping(value = "/reserveForm", method = RequestMethod.POST)
    public String reserveCarOperation(@ModelAttribute("journeyDTO") JourneyDTO journey) {

        if (journey != null) {
            this.journeyService.createJourney(journey);
        } else {
            System.out.println("Journey not found!");
        }

        return "redirect:/journey";
    }
}
