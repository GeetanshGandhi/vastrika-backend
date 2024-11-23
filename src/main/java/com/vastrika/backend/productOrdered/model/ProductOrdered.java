package com.vastrika.backend.productOrdered.model;

import com.vastrika.backend.DeliveryEmployee.model.DeliveryEmployee;
import com.vastrika.backend.orders.model.Orders;
import com.vastrika.backend.product.model.Product;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@IdClass(ProductOrderedPK.class)
public class ProductOrdered {

    @Id
    @ManyToOne
    @JoinColumn(name = "orderId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "productId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    private int rate;
    private int quantity;
    private String status;
    private String remark;
    private int otpForCustomer;

    @ManyToOne
    @JoinColumn(name = "employeeEmail")
    private DeliveryEmployee deliveryEmployee;

    public ProductOrdered(){}

    public ProductOrdered(Product product, Orders orders, int quantity){
        this.product = product;
        this.orders = orders;
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public DeliveryEmployee getDeliveryEmployee() {
        return deliveryEmployee;
    }

    public void setDeliveryEmployee(DeliveryEmployee deliveryEmployee) {
        this.deliveryEmployee = deliveryEmployee;
    }

    public int getOtpForCustomer() {
        return otpForCustomer;
    }

    public void setOtpForCustomer(int otpForCustomer) {
        this.otpForCustomer = otpForCustomer;
    }
}
