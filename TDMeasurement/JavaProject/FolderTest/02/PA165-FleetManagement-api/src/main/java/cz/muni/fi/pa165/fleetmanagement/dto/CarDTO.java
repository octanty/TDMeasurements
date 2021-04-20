package cz.muni.fi.pa165.fleetmanagement.dto;

import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Peter Pavlík
 */
public class CarDTO implements Serializable {

    private Long id;
    private String brand;
    private String model;
    private String color;
    private String licensePlate;
    private String engine;
    private int mileage;
    private List<ServiceIntervalDTO> serviceInterval;
    private UserClassEnum userClass;
    private Byte[] photo;
    private long countExposureServiceInterval;

    public CarDTO() {
    }

    public CarDTO(Long id, String brand, String model, String color, String licensePlate, String engine, int mileage, UserClassEnum userClass, Byte[] photo, long countExposureServiceInterval) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
        this.engine = engine;
        this.mileage = mileage;
        this.userClass = userClass;
        this.photo = photo;
        this.countExposureServiceInterval = countExposureServiceInterval;
    }

    public CarDTO(Long id, String brand, String model, String color, String licensePlate, String engine, int mileage, UserClassEnum userClass, Byte[] photo) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
        this.engine = engine;
        this.mileage = mileage;
        this.userClass = userClass;
        this.photo = photo;
    }

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
        this.licensePlate = licensePlate ;
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

    public List<ServiceIntervalDTO> getServiceInterval() {
        return serviceInterval;
    }

    public void setServiceInterval(List<ServiceIntervalDTO> serviceInterval) {
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

    public long getCountExposureServiceInterval() {
        return countExposureServiceInterval;
    }

    public void setCountExposureServiceInterval(long countExposureServiceInterval) {
        this.countExposureServiceInterval = countExposureServiceInterval;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final CarDTO other = (CarDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "#" + id + ": " +"["+licensePlate+"]"+brand+" "+model;
    }
}
