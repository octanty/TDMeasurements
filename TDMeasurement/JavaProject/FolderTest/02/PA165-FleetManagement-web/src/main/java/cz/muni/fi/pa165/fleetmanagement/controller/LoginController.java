package cz.muni.fi.pa165.fleetmanagement.controller;

import cz.muni.fi.pa165.fleetmanagement.service.SampleDataGenerator;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
 
    //@Autowired
    SampleDataGenerator SampleDataGenerator;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal, HttpServletRequest request) {
        if (principal == null) {
            return "redirect:login";
        }
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "/employee/employeeList";
        } else {
            return "redirect:/cars";
        }
    }
    

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "login";

    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

        model.addAttribute("error", "true");
        return "login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {

        return "login";

    }
     @RequestMapping(value = "/login/generate", method = RequestMethod.GET)
    public String generateLogins(ModelMap model) {
    
    	SampleDataGenerator.generateSampleData();
        return "redirect:/login";
    }
}