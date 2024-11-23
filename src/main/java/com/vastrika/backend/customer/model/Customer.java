package com.vastrika.backend.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    private String customerEmail;
    private String firstName;
    private String lastName;
    private String mobile;
    private String password;
    private String houseNumber;
    private String streetBuildingName;
    private String landmark;
    private String state;
    private String city;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetBuildingName() {
        return streetBuildingName;
    }

    public void setStreetBuildingName(String streetBuildingName) {
        this.streetBuildingName = streetBuildingName;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "{" +
                "\"customerEmail\":\"" + customerEmail + '\"' +
                ", \"firstName\":\"" + firstName + '\"' +
                ", \"lastName\":\"" + lastName + '\"' +
                ", \"mobile\":\"" + mobile + '\"' +
                ", \"password\":\"" + password + '\"' +
                ", \"city\":\"" + city + '\"' +
                ", \"houseNumber\":\"" + houseNumber + '\"' +
                ", \"streetBuildingName\":\"" + streetBuildingName + '\"' +
                ", \"landmark\":\"" + landmark + '\"' +
                ", \"state\":\"" + state + '\"' +
                '}';
    }
}
