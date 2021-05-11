package com.example.orderplacer.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class OrderDetail extends Order implements Serializable {
    private String id;
    private boolean isCompleted = false;

    public OrderDetail(String id, String customerName, String orderDetails, boolean isCompleted) {
        super(customerName, orderDetails);
        this.id = id;
        this.isCompleted = isCompleted;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public OrderDetail(String customerName, String orderDetails) {
        super(customerName, orderDetails);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return "OrderDetails:  \n" +
                "Customer Name: " + customerName + " \n" +
                "Order Details: " + orderDetails + " \n" +
                "Status: " + (isCompleted() ? "Completed" : "InComplete");
    }
}
