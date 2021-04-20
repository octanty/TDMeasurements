package cz.muni.fi.pa165.fleetmanagement.dto;

import cz.muni.fi.pa165.fleetmanagement.enums.GenderEnum;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;

/**
 *
 * @author Ľubomír Lacika
 */
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private UserClassEnum userClass;
    private String email;
    private String phoneNumber;
    private GenderEnum gender;
    private String password;
    private boolean isActive;
    private boolean isAdmin;
    public boolean isIsActive() {
        return isActive;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }
   

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

     

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserClassEnum getUserClass() {
        return userClass;
    }

    public void setUserClass(UserClassEnum userClass) {
        this.userClass = userClass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final EmployeeDTO other = (EmployeeDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "#" + id + ": " + firstName + " " + lastName;
    }
}
