package com.example.orderplacer.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.orderplacer.Utils.OrderManager;
import com.example.orderplacer.databinding.ActivityCreateOrUpdateOrderBinding;
import com.example.orderplacer.model.Order;
import com.example.orderplacer.model.OrderDetails;


public class Create_or_Update_Order extends AppCompatActivity {
    ActivityCreateOrUpdateOrderBinding mBinding;
    OrderManager mOrderManager;
    OrderDetails details = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCreateOrUpdateOrderBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mOrderManager = OrderManager.buildWith(openOrCreateDatabase(OrderManager.ORDER_DATABASE, MODE_PRIVATE, null));

             if (getIntent().hasExtra("order")) {
                 details = (OrderDetails) getIntent().getSerializableExtra("order");
                 mBinding.name.setText(details.customerName);
                 mBinding.details.setText(details.orderDetails);
             }else {
                 mBinding.placeOrderBtn.setOnClickListener(v -> {
                     String customerName = mBinding.name.getText().toString();
                     String orderDetails = mBinding.details.getText().toString();
                     mOrderManager.saveOrdersData(new OrderDetails(customerName, orderDetails));
                     finish();
                 });
             }


    }
}