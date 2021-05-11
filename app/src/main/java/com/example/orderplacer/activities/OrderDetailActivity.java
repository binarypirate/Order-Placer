package com.example.orderplacer.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.orderplacer.Utils.OrderDao;
import com.example.orderplacer.databinding.ActivityOrderDetailBinding;
import com.example.orderplacer.model.OrderDetail;

public class OrderDetailActivity extends AppCompatActivity {

    ActivityOrderDetailBinding mBinding;
    OrderDao mOrderDao;
    OrderDetail mOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mOrderDao = OrderDao.buildWith(openOrCreateDatabase(OrderDao.ORDER_DATABASE, MODE_PRIVATE, null));

        mOrder = (OrderDetail) getIntent().getSerializableExtra("order");

        mBinding.ordDetails.setText(mOrder.toString());

        mBinding.dltBtn.setOnClickListener(v -> {
            mOrderDao.deleteOrder(mOrder);
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            finish();
        });

        mBinding.editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateOrUpdateOrder.class);
            intent.putExtra("order", mOrder);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
       super.onResume();
       mBinding.ordDetails.setText(mOrderDao.getOrderDetail(mOrder.getId()).toString());
    }
}