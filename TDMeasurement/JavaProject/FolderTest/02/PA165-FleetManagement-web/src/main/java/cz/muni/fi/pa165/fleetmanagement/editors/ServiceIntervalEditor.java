package cz.muni.fi.pa165.fleetmanagement.editors;

import cz.muni.fi.pa165.fleetmanagement.dto.ServiceIntervalDTO;
import cz.muni.fi.pa165.fleetmanagement.service.ServiceIntervalService;
import java.beans.PropertyEditorSupport;

/**
 * @author Ján Švec
 */
public class ServiceIntervalEditor extends PropertyEditorSupport {

    private ServiceIntervalService serviceIntervalService;

    public ServiceIntervalEditor(ServiceIntervalService serviceIntervalService) {
        super();
        this.serviceIntervalService = serviceIntervalService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        ServiceIntervalDTO serviceInterval = serviceIntervalService.getServiceIntervalById((long) Integer.parseInt(text));
        setValue(serviceInterval);
    }

    @Override
    public String getAsText() {
        ServiceIntervalDTO serviceInterval = (ServiceIntervalDTO) getValue();
        if (serviceInterval == null) {
            return null;
        } else {
            return serviceInterval.getId().toString();
        }
    }
}
