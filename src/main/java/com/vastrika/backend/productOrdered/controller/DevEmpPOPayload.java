package com.vastrika.backend.productOrdered.controller;

public class DevEmpPOPayload {
    private String cName;
    private String address;
    private int orderId;
    private int productId;
    private String phone;
    private String payMethod;
    private int amount;

    public DevEmpPOPayload(String cName, String address, int orderId, int productId, String phone, String payMethod, int amount) {
        this.cName = cName;
        this.address = address;
        this.orderId = orderId;
        this.phone = phone;
        this.productId = productId;
        this.payMethod = payMethod;
        this.amount = amount;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DevEmpPOPayload{" +
                "cName='" + cName + '\'' +
                ", address='" + address + '\'' +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", phone='" + phone + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", amount=" + amount +
                '}';
    }
}
