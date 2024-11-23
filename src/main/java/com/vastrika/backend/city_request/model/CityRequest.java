package com.vastrika.backend.city_request.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CityRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    private String pinCode;
    private boolean cityRequestStatus;
    private String cityName;
    private String state;

    public CityRequest() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isCityRequestStatus() {
        return cityRequestStatus;
    }

    public void setCityRequestStatus(boolean cityRequestStatus) {
        this.cityRequestStatus = cityRequestStatus;
    }

    @Override
    public String toString() {
        return "CityRequest{" +
                "pinCode='" + pinCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
