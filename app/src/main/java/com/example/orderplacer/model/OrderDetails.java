package com.example.orderplacer.model;

import java.io.Serializable;

public class OrderDetails extends Order implements Serializable {
    private String id;

    public OrderDetails(String customerName, String orderDetails, String id) {
        super(customerName, orderDetails);
        this.id = id;
    }

    public OrderDetails(String customerName, String orderDetails) {
        super(customerName, orderDetails);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OrderDetails:  \n" +
                "Customer Name: " + customerName + " \n" +
                "Order Details: " + orderDetails;
    }
}
