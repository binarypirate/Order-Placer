package com.example.orderplacer.model;

import java.io.Serializable;

public class Order implements Serializable {

    public final String customerName;
    public final String orderDetails;

    public Order(String customerName, String orderDetails) {
        this.customerName = customerName;
        this.orderDetails = orderDetails;
    }
}
