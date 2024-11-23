package com.vastrika.backend.cart.model;

import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.product.model.Product;

import java.io.Serializable;
import java.util.Objects;

public class CartId implements Serializable {

    private Product product;
    private Customer customer;

    public CartId(){}

    public CartId(Product product, Customer customer){
        this.customer = customer;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartId that = (CartId) o;
        return Objects.equals(customer,that.customer) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, product);
    }
}
