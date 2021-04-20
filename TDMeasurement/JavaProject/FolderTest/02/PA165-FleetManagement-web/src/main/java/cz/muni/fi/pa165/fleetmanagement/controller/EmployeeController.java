package cz.muni.fi.pa165.fleetmanagement.controller;

import javax.servlet.http.HttpSession;

import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.enums.GenderEnum;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import cz.muni.fi.pa165.fleetmanagement.service.EmployeeService;
import cz.muni.fi.pa165.fleetmanagement.validators.EmployeeFormValidator;
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
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new EmployeeFormValidator());
    }

    @RequestMapping(value = "/employee/list", method = RequestMethod.GET)
    public String viewAll(ModelMap model, HttpSession session) {
        model.put("employees", employeeService.getAllEmployee());
        return "/employee/employeeList";
    }

    @RequestMapping(value = "/employee/new", method = RequestMethod.GET)
    public String showNewForm(ModelMap model, HttpSession session) {
        model.put("employeeDTO", new EmployeeDTO());
        model.put("userClass", UserClassEnum.values());
        model.put("gender", GenderEnum.values());
        return "/employee/employeeForm";
    }

    @RequestMapping(value = "/employee/new", method = RequestMethod.POST)
    public String createNew(@Validated EmployeeDTO newEmployee, BindingResult result, ModelMap model, HttpSession session) {
        if (result.hasErrors()) {
            model.put("userClass", UserClassEnum.values());
            model.put("gender", GenderEnum.values());
            return "/employee/employeeForm";
        }
        employeeService.createEmployee(newEmployee);
        session.setAttribute("message", "employee.msg.create.successful");
        return "redirect:/employee/list";
    }

    @RequestMapping(value = "/employee/view/{id}", method = RequestMethod.GET)
    public String viewEmployee(@PathVariable("id") Long id, ModelMap model, HttpSession session) {

        EmployeeDTO employee = employeeService.getEmployeeById(id);
        model.put("employee", employee);

        return "/employeeView";
    }
    //nove   
    /*
    @RequestMapping(value = "/employee/guest", method = RequestMethod.GET) 
    public String viewGuest(ModelMap model, HttpSession session) {   
        return "/employee/employeeViewGuest"; 
    }
    */

    @RequestMapping(value = "/employee/edit/{id}", method = RequestMethod.GET)
    public String showEditForm(@PathVariable("id") Long id, ModelMap model, HttpSession session) {
        EmployeeDTO employeeToEdit = employeeService.getEmployeeById(id);
        if (employeeToEdit == null) {
            session.setAttribute("error", "employee.msg.edit.unexists");
            return "redirect:/employee/list";
        }
        model.put("employeeDTO", employeeToEdit);
        model.put("userClass", UserClassEnum.values());
        model.put("gender", GenderEnum.values());
        return "/employee/employeeForm";
    }

    @RequestMapping(value = "/employee/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, @Validated @ModelAttribute("employee") EmployeeDTO employeeForm, BindingResult result,
            ModelMap model, HttpSession session) {
        if (result.hasErrors()) {
            model.put("userClass", UserClassEnum.values());
            model.put("gender", GenderEnum.values());
            return "/employee/employeeForm";
        }
        if (id == null || employeeForm.getId() == null || !id.equals(employeeForm.getId())) {
              session.setAttribute("error", "employee.msg.edit.wrongId");
            model.put("userClass", UserClassEnum.values());
            model.put("gender", GenderEnum.values());
            return "/employee/employeeForm";
        }
        employeeService.updateEmployee(employeeForm);
         session.setAttribute("message", "employee.msg.edit.successful");
        return "redirect:/employee/list";
    }

    @RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, ModelMap model, HttpSession session) {
        EmployeeDTO employeeToDelete = employeeService.getEmployeeById(id);

        if (employeeToDelete == null) {
             session.setAttribute("error", "employee.msg.delete.unexists");
        } else {
            employeeService.deleteEmployee(employeeToDelete);
            session.setAttribute("message", "employee.msg.delete.successful");
        }
        return "redirect:/employee/list";
    }
}