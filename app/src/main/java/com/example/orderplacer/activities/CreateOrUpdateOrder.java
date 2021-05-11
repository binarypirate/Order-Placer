package com.example.orderplacer.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderplacer.Utils.OrderDao;
import com.example.orderplacer.databinding.ActivityCreateOrUpdateOrderBinding;
import com.example.orderplacer.model.Order;
import com.example.orderplacer.model.OrderDetail;


public class CreateOrUpdateOrder extends AppCompatActivity {

    ActivityCreateOrUpdateOrderBinding mBinding;
    OrderDao mOrderDao;
    OrderDetail details = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCreateOrUpdateOrderBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mOrderDao = OrderDao.buildWith(openOrCreateDatabase(OrderDao.ORDER_DATABASE, MODE_PRIVATE, null));

        if (getIntent().hasExtra("order")) {
            details = (OrderDetail) getIntent().getSerializableExtra("order");
            mBinding.name.setText(details.customerName);
            mBinding.details.setText(details.orderDetails);
            mBinding.placeOrderBtn.setText("Update Order");
        }

        mBinding.placeOrderBtn.setOnClickListener(v -> {
            String customerName = mBinding.name.getText().toString().trim();
            String orderDetails = mBinding.details.getText().toString().trim();

            if (getIntent().hasExtra("order")) {
                OrderDetail orderDetail = new OrderDetail(customerName, orderDetails);
                orderDetail.setId(details.getId());
                mOrderDao.updateOrder(orderDetail);
                Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();
            } else {
                mOrderDao.saveOrdersData(new Order(customerName, orderDetails)); // Create
                Toast.makeText(this, "Created!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}