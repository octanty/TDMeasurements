/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.rest;

import cz.muni.fi.pa165.fleetmanagement.enums.GenderEnum;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SOAD
 */
@XmlRootElement
public class EmployeeResource {

    private Long id;
    private String firstName;
    private String lastName;
    private UserClassEnum userClass;
    private String email;
    private String phoneNumber;
    private GenderEnum gender;
    private String password;

    public EmployeeResource(Long id, String firstName, String lastName, UserClassEnum userClass, String email, String phoneNumber, GenderEnum gender, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userClass = userClass;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.password = password;
    }

    public EmployeeResource() {
    }

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
    public String toString() {
        return "#" + id + ": " + firstName + " " + lastName;
    }
}
