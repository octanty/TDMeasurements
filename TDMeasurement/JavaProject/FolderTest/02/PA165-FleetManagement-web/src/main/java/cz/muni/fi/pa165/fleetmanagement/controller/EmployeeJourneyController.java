/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.controller;

import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.JourneyDTO;
import cz.muni.fi.pa165.fleetmanagement.service.EmployeeService;
import cz.muni.fi.pa165.fleetmanagement.service.JourneyService;
import cz.muni.fi.pa165.fleetmanagement.validators.JourneyFinishedValidator;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ján Švec
 */
@Controller
public class EmployeeJourneyController {
    
    @Autowired
    JourneyService journeyService;
    @Autowired
    EmployeeService employeeService;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new JourneyFinishedValidator());
    }
    
    @RequestMapping(value = "/journeys", method = RequestMethod.GET)
    public String viewCarsList(ModelMap model, Principal principal) {
        //get current Employee
//        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
//        if (principalName == null) {
//            return "redirect:login";
//        }
//        EmployeeDTO employee = employeeService.findEmployeeByEmail(principalName);
//        if (employee == null) {
//            //throw new PageNotFoundException("Current employee doesn't exist.");
//        }
//        
//        List<JourneyDTO> journeys = journeyService.getJourneyForEmployee(employee);
////        model.put("journeys", journeys);
//        
        return "/journeys/index";
    }

    // Cancel
    @RequestMapping(value = "/journeys/cancel", params = {"id"})
    public String actionCancelDrive(@RequestParam("id") Long id, ModelMap model, HttpSession session) throws Exception {
        if (id == null) {
            //throw new PageNotFoundException("Missing argument id.");
        }
        // get journey
        JourneyDTO journey = journeyService.getJourneyById(id);
        if (journey == null) {
            // throw new PageNotFoundException("Journey not found.");
        }

        // check employee's permission
        EmployeeDTO currentEmployee = getCurrentEmployee();
        if (currentEmployee == null) {
            //throw new PageNotFoundException("Current employee didn't found.");
        }
        if (journey.getEmployee().getId() != currentEmployee.getId()) {
            // throw new PageNotFoundException("Employee can't change state of foreign drive.");
        }

        
        journeyService.updateJourney(journey);

        session.setAttribute("message", "drives.msg.cancel.successful");

        return "redirect:/drives";
    }

    // Start journey
    @RequestMapping(value = "/journeys/start", params = "id")
    public String actionChangeJourneyState(@RequestParam("id") Long id, ModelMap model, HttpSession session) throws Exception {
        if (id == null) {
            //throw new PageNotFoundException("Missing argument id.");
        }

        // get journey
        JourneyDTO journey = journeyService.getJourneyById(id);
        if (journey == null) {
            //  throw new PageNotFoundException("Journey not found.");
        }

        //  check employee's permission
       EmployeeDTO currentEmployee = getCurrentEmployee();
        if (currentEmployee == null) {
            return "redirect:login";
        }
        if (journey.getEmployee().getId() != currentEmployee.getId()) {
            // throw new PageNotFoundException("Employee can't change state of foreign journey.");
        }

        // is time to pick car?
        Date now = new Date();
        if (now.compareTo(journey.getPickUpDate()) < 0) {
            // it's to soon to pick a car
            session.setAttribute("error", "journeys.msg.start.tooSoon");
            return "redirect:/journeys";
        }
        if (now.compareTo(journey.getReturnDate()) > 0) {
            // it's to soon to pick a car
            session.setAttribute("error", "journeys.msg.start.tooLate");
            return "redirect:/journeys";
        }       

        // change
        journeyService.updateJourney(journey);
        session.setAttribute("message", "drives.msg.start.successful");

        return "redirect:/journeys";
    }

    // Finish journey Form
    @RequestMapping(value = "/journeys/finish", params = "id", method = RequestMethod.GET)
    public String viewFinishForm(@RequestParam("id") Long id, ModelMap model, HttpSession session) throws Exception {
        
        if (id == null) {
            // throw new PageNotFoundException("Missing argument ID.");
        }

        // get journey
        JourneyDTO journeyDTOToFinish = journeyService.getJourneyById(id);
        if (journeyDTOToFinish == null) {
            //  throw new PageNotFoundException("Journey with id " + id + "doesn't exist.");
        }

        // check employee's permission
        EmployeeDTO currentEmployee = getCurrentEmployee();
        if (currentEmployee == null) {
            //  throw new PageNotFoundException("Current employee didn't found.");
        }
        if (journeyDTOToFinish.getEmployee().getId() != currentEmployee.getId()) {
            // throw new PageNotFoundException("Employee can't change state of foreign journey.");
        }


        model.put("journey", journeyDTOToFinish);
        
        return "drives/finish";
    }


    @RequestMapping(value = "/journeys/finish", method = RequestMethod.POST)
    public String submittedFinishForm(@ModelAttribute("journey") JourneyDTO journey, BindingResult result, ModelMap model, HttpSession session) throws Exception {
        if (journey == null || journey.getId() == null) {
            // throw new PageNotFoundException("Missing journey.");
        }
        if (result.hasErrors()) {
            return "journeys/finish";
        }
        JourneyDTO journeyDTO = journeyService.getJourneyById(journey.getId());
        if (journeyDTO == null) {
            //  throw new PageNotFoundException("Journey didn't found.");
        }
        //check employee's permission
        EmployeeDTO currentEmployee = getCurrentEmployee();
        if (currentEmployee == null) {
            //  throw new PageNotFoundException("Current employee didn't found.");
        }

        if (journeyDTO.getEmployee().getId() != currentEmployee.getId()) {
            //  throw new PageNotFoundException("User can't change state of foreign drive.");
        }
        
        // change drive
        journeyDTO.setMileage(journey.getMileage()); 

        journeyService.updateJourney(journeyDTO);
        session.setAttribute("message", "drives.msg.finish.successful");

        return "redirect:/journeys";
    }

    /**
     * Returns current employee
     *
     * @return Current employee or null if not found
     */
    private EmployeeDTO getCurrentEmployee() {
        // check user permission to do that
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (principalName == null) {
            return null;
        }
        EmployeeDTO currentEmployee = employeeService.findEmployeeByEmail(principalName);
        if (currentEmployee == null) {
            return null;
        }
        return currentEmployee;
    }
}
