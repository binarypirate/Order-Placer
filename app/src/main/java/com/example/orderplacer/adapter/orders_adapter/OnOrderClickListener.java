package com.example.orderplacer.adapter.orders_adapter;

import com.example.orderplacer.model.OrderDetail;

public interface OnOrderClickListener {
    void onOrderClicked(OrderDetail orderDetails);
    void onOrderCompletionChangeBtnClick(OrderDetail orderDetail);
}
