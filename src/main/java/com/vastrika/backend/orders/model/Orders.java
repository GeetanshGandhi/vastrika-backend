package com.vastrika.backend.orders.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.vastrika.backend.customer.model.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {

    @Id
    private int orderId;
    private double grandTotal;
    private int subTotal;
    private double tax;
    private String paymentMethod;

    private LocalDateTime orderDateTime;

    @ManyToOne
    @JoinColumn(name = "customerEmail")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;


    public Orders(){}

    public Orders(Customer customer, int subTotal, double tax, double grandTotal, LocalDateTime orderDateTime, String paymentMethod) {
        this.grandTotal = grandTotal;
        this.subTotal = subTotal;
        this.tax = tax;
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

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
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
