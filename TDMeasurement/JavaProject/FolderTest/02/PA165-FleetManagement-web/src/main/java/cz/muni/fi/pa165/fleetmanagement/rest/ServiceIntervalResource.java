/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.rest;

import java.util.Date;
import java.util.Objects;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Peter Pavlik
 */
@XmlRootElement
public class ServiceIntervalResource {

    private Long id;
    private String service;
    private Integer mileageControl;
    private Date dateControl;
    private Date completionDate;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPlain() {
        return this.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Integer getMileageControl() {
        return mileageControl;
    }

    public void setMileageControl(Integer mileageControl) {
        this.mileageControl = mileageControl;
    }

    public Date getDateControl() {
        return dateControl;
    }

    public void setDateControl(Date dateControl) {
        this.dateControl = dateControl;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServiceIntervalResource other = (ServiceIntervalResource) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ServiceInterval{" + "id=" + id + '}';
    }
}
