package cz.muni.fi.pa165.fleetmanagement.dto;

import java.util.Date;

/**
 *
 * @author Eduard Dobai
 */
public class ServiceIntervalDTO {

    private Long id;
    private String service;
    private Integer mileageControl;
    private Date dateControl;
    private Date completionDate;

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
        final ServiceIntervalDTO other = (ServiceIntervalDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ServiceInterval{" + "id=" + id + '}';
    }
}
