package com.vastrika.backend.orders.controller;

import com.vastrika.backend.orders.model.Orders;
import com.vastrika.backend.productOrdered.model.ProductOrdered;

import java.util.List;

public class PlacedOrderData {

    private Orders orders;
    private List<ProductOrdered> productOrderedList;

    public PlacedOrderData(Orders orders, List<ProductOrdered> productOrderedList){
        this.orders = orders;
        this.productOrderedList = productOrderedList;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public List<ProductOrdered> getProductOrderedList() {
        return productOrderedList;
    }

    public void setProductOrderedList(List<ProductOrdered> productOrderedList) {
        this.productOrderedList = productOrderedList;
    }
}
