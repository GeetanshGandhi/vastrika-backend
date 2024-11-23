package com.vastrika.backend.orders.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.vastrika.backend.customer.model.Customer;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private double grandTotal;
    private String paymentMethod;

    private LocalDateTime orderDateTime;

    @ManyToOne
    @JoinColumn(name = "customerEmail")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;


    public Orders(){}

    public Orders(Customer customer, double grandTotal, LocalDateTime orderDateTime, String paymentMethod) {
        this.grandTotal = grandTotal;
        this.paymentMethod = paymentMethod;
        this.orderDateTime = orderDateTime;
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
