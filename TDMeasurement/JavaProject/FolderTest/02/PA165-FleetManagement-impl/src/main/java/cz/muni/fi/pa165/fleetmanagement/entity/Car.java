package cz.muni.fi.pa165.fleetmanagement.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;

/**
 *
 * @author Peter Pavlik
 * @version 1.0
 */
@Entity
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 30)
    private String brand;
    @Column(nullable = false, length = 30)
    private String model;
    @Column(nullable = false, length = 10)
    private String color;
    @Column(nullable = false, length = 50, unique = true)
    private String licensePlate;
    @Column(nullable = true, length = 10)
    private String engine;
    @Column(nullable = false, length = 10)
    private int mileage;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CAR_ID")
    private List<ServiceInterval> serviceInterval;
    @Column(nullable = true, columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private UserClassEnum userClass;
    @Column(nullable = true)
    @Lob
    private Byte[] photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public List<ServiceInterval> getServiceInterval() {
        return serviceInterval;
    }

    public void setServiceInterval(List<ServiceInterval> serviceInterval) {
        this.serviceInterval = serviceInterval;
    }

    public UserClassEnum getUserClass() {
        return userClass;
    }

    public void setUserClass(UserClassEnum userClass) {
        this.userClass = userClass;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public Car() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Car other = (Car) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + '}';
    }
}