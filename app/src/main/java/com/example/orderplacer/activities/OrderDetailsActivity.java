package com.example.orderplacer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.orderplacer.R;
import com.example.orderplacer.Utils.OrderManager;
import com.example.orderplacer.databinding.ActivityCreateOrUpdateOrderBinding;
import com.example.orderplacer.databinding.ActivityOrderDetailsBinding;
import com.example.orderplacer.model.OrderDetails;

public class OrderDetailsActivity extends AppCompatActivity {
    ActivityOrderDetailsBinding mBinding;
    OrderManager mOrderManager;
    OrderDetails mOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mOrderManager = OrderManager.buildWith(openOrCreateDatabase(OrderManager.ORDER_DATABASE, MODE_PRIVATE, null));
        mOrder = (OrderDetails) getIntent().getSerializableExtra("order");

        mBinding.ordDetails.setText(mOrder.toString());

        mBinding.dltBtn.setOnClickListener(v -> {
            mOrderManager.dltData(mOrder);
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            finish();
        });

        mBinding.editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Create_or_Update_Order.class);
            intent.putExtra("order", mOrder);
            startActivity(intent);
        });
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mBinding.ordDetails.setText(mOrderManager.getOrderDetail(mOrder.getId()).toString());
//    }
}