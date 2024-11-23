package com.vastrika.backend.categoryrequest.model;

import jakarta.persistence.*;

@Entity
public class CategoryRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryRequestId;
    private String categoryName;
    private boolean categoryStatus;

    public CategoryRequest(){}
    public CategoryRequest(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getcategoryRequestId() {
        return categoryRequestId;
    }

    public boolean getCategoryStatus() {
        return categoryStatus;
    }

    public void setcategoryRequestId(int categoryRequestId) {
        this.categoryRequestId = categoryRequestId;
    }

    public void setCategoryStaus(boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "{" +
                "\"categoryName\":\"" + categoryName + '\"' +
                '}';
    }
}
