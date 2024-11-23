package com.vastrika.backend.productOrdered.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;


public class ProductOrderedPK implements Serializable {

    private int orders;
    private int product;

    public ProductOrderedPK(){}

    public ProductOrderedPK(int orders, int product){
        this.orders = orders;
        this.product = product;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, orders);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrderedPK that = (ProductOrderedPK) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(orders, that.orders);
    }
}
