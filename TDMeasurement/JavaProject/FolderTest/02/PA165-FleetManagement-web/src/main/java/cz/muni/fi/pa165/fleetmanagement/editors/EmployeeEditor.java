package cz.muni.fi.pa165.fleetmanagement.editors;

import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.service.EmployeeService;
import java.beans.PropertyEditorSupport;

/**
 * @author Ján Švec
 */
public class EmployeeEditor extends PropertyEditorSupport {

    private EmployeeService employeeService;

    public EmployeeEditor(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        EmployeeDTO employee = employeeService.getEmployeeById((long) Integer.parseInt(text));
        setValue(employee);
    }

    @Override
    public String getAsText() {
        EmployeeDTO employee = (EmployeeDTO) getValue();
        if (employee == null) {
            return null;
        } else {
            return employee.getId().toString();
        }
    }
}
