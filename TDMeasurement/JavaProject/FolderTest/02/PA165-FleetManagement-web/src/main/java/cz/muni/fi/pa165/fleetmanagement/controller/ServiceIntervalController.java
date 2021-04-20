package cz.muni.fi.pa165.fleetmanagement.controller;

import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.ServiceIntervalDTO;
import cz.muni.fi.pa165.fleetmanagement.editors.DateEditor;
import cz.muni.fi.pa165.fleetmanagement.service.CarService;
import cz.muni.fi.pa165.fleetmanagement.service.ServiceIntervalService;
import cz.muni.fi.pa165.fleetmanagement.validators.ServiceIntervalValidator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("serviceIntervalDTO")
public class ServiceIntervalController {

    @Autowired
    ServiceIntervalService serviceIntervalService;
    
    @Autowired
    CarService carService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ServiceIntervalValidator());
        binder.registerCustomEditor(Date.class, new DateEditor());
    }
    // controler for Guest
    @RequestMapping(value = "/service/guest", method = RequestMethod.GET) 
    public String viewGuest(ModelMap model, HttpSession session) {   
        return "/service/serviceGuest"; 
    }

    @RequestMapping(value = "/service/list", method = RequestMethod.GET)
    public String printWelcome(HttpServletRequest request, ModelMap model) {
        model.put("intervals", serviceIntervalService.getAllServiceInterval());
        return "/service/serviceList";
    }

    // Add
    @RequestMapping(value = "/service/new", method = RequestMethod.GET)
    public String viewEmptyForm(HttpServletRequest request, ModelMap model) {
//        String carID = request.getParameter("carId");
//        if (carID == null) {
//            return "service/serviceList";
//        }

//        CarDTO car = this.carService.getCarById(Long.parseLong(carID));
        ServiceIntervalDTO interval = new ServiceIntervalDTO();
//        interval.setCar(car);

        model.put("serviceIntervalDTO", interval);
        return "service/serviceForm";
    }
//
//    // Edit
//    @RequestMapping(value = "/service/edit", params = "id", method = RequestMethod.GET)
//    public String viewFilledForm(@RequestParam("id") Long id, @RequestParam(value = "carId", required = false) Long carID,
//            HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
//
//        ServiceIntervalDTO interval = this.serviceIntervalService.getServiceIntervalById(id);
//        if (carID != null) {
//            CarDTO carDTO = this.carService.getCarById(carID);
//            interval.setCar(carDTO);
//        }
//
//        if (interval == null) {
//            session.setAttribute("error", "serviceInterval.msg.edit.unexists");
//            return "redirect:/service/serviceList";
//        }
//        model.put("serviceIntervalDTO", interval);
//        return "service/serviceForm";
//    }
//
//    @RequestMapping(value = { "/service/new", "/service/edit" }, method = RequestMethod.POST)
//    public String submitEditForm(@Validated @ModelAttribute("serviceIntervalDTO") ServiceIntervalDTO interval, BindingResult result,
//            ModelMap model, HttpSession session) {
//
//        if (result.hasErrors()) {
//            model.put("serviceIntervalDTO", interval);
//            return "service/serviceForm";
//        }
//
//        if (interval.getId() == null) {
//            this.serviceIntervalService.create(interval);
//            session.setAttribute("message", "serviceInterval.msg.create.successful");
//        } else {
//            this.serviceIntervalService.update(interval);
//            session.setAttribute("message", "serviceInterval.msg.update.successful");
//        }
//
//        return "redirect:/service/show?id=" + interval.getId();
//    }
//
//    // Car select
//    @RequestMapping(value = "/service/carSelect", method = RequestMethod.GET)
//    public String showCars(ModelMap model) throws Exception {
//        model.put("cars", this.carService.getAllCar());
//        return "/service/carSelect";
//    }
//
//    // Car select
//    @RequestMapping(value = "/service/carSelect2", method = RequestMethod.GET)
//    public String showCarsForEdit(HttpServletRequest request, ModelMap model) throws Exception {
//
//        model.put("cars", this.carService.getAllCar());
//        return "/service/carSelect2";
//    }
//
//    // Show
//    @RequestMapping(value = "/service/show", params = "id")
//    public String viewInterval(@RequestParam("id") Long id, ModelMap model, HttpSession session) throws Exception {
//        ServiceIntervalDTO interval = this.serviceIntervalService.getServiceIntervalById(id);
//        if (interval == null) {
//            session.setAttribute("error", "serviceInterval.msg.show.unexists");
//            return "redirect:/service/serviceList";
//        }
//        model.put("serviceInterval", interval);
//        return "service/show";
//    }
//
//    // Delete
//    @RequestMapping(value = "/service/delete", params = "id")
//    public String deleteById(@RequestParam("id") Long id, HttpSession session) throws Exception {
//
//        ServiceIntervalDTO interval = this.serviceIntervalService.getServiceIntervalById(id);
//
//        if (interval == null) {
//            session.setAttribute("error", "serviceInterval.msg.delete.unexists");
//            return "redirect:/service/serviceList";
//        }
//
//        this.serviceIntervalService.deleteServiceInterval(interval);
//        session.setAttribute("message", "serviceInterval.msg.delete.successful");
//        return "redirect:/service/serviceList";
//    }
//
//    // Inspect car
//    @RequestMapping(value = "/service/inspect", params = "id")
//    public String actionInspectInterval(@RequestParam("id") Long id, ModelMap model, HttpSession session) throws Exception {
//        ServiceIntervalDTO interval = this.serviceIntervalService.getServiceIntervalById(id);
//        if (interval == null) {
//            session.setAttribute("error", "serviceInterval.msg.show.unexists");
//            return "redirect:/service/serviceList";
//        }
//        List<Date> dates = interval.getDated();
//        dates.add(new Date());
//        serviceIntervalService.updateServiceInterval(interval);
//        session.setAttribute("message", "serviceInterval.msg.inspect.successful");
//        return "redirect:/service/show?id=" + id;
//    }
}
