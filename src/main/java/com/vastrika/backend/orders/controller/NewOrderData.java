package com.vastrika.backend.orders.controller;

import com.vastrika.backend.cart.model.CartItem;
import com.vastrika.backend.customer.model.Customer;

import java.util.List;

public class NewOrderData {

    private List<CartItem> cartItems;
    private double grandTotal;
    private String paymentMethod;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
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
}
