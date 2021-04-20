package cz.muni.fi.pa165.fleetmanagement.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Peter Pavlik
 * @version 1.0
 */
@Entity
public class ServiceInterval implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String service;
    @Column(nullable = true, length = 10)
    private Integer mileageControl;
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date dateControl;
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
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

    public ServiceInterval() {
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
        final ServiceInterval other = (ServiceInterval) obj;
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