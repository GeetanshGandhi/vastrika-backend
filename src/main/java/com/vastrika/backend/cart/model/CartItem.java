package com.vastrika.backend.cart.model;

import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.product.model.Product;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@IdClass(CartId.class)
public class CartItem {

    @Id
    @ManyToOne
    @JoinColumn(name = "customerEmail")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "productId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    private int quantity;

    public CartItem(){}
    public CartItem(Customer customer, Product product, int quantity){
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                "\"customer\":" + customer.toString() +
                ", \"product\":" + product.toString() +
                ", \"quantity\":" + quantity +
                '}';
    }
}
