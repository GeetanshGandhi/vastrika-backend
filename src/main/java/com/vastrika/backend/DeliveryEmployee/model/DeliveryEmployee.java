package com.vastrika.backend.DeliveryEmployee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DeliveryEmployee {
     @Id
    private String employeeEmail;
    private String EmployeeName;
    private String password;
    

    public DeliveryEmployee(){}
    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName){
        this.EmployeeName = EmployeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
