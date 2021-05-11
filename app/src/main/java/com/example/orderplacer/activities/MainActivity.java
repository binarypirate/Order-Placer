package com.example.orderplacer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.orderplacer.Utils.OrderManager;
import com.example.orderplacer.adapter.OrderAdapter;
import com.example.orderplacer.databinding.ActivityMainBinding;
import com.example.orderplacer.model.OnOrderPlacedCardListener;
import com.example.orderplacer.model.OrderDetails;

public class MainActivity extends AppCompatActivity implements OnOrderPlacedCardListener {

    ActivityMainBinding mBinding;
    OrderManager mOrderManager;
    OrderAdapter mOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mOrderAdapter.setOrder(mOrderManager.getOrderHistoryData(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Create_or_Update_Order.class);
            startActivity(intent);
        });

        mOrderManager = OrderManager.buildWith(openOrCreateDatabase(OrderManager.ORDER_DATABASE, MODE_PRIVATE, null));
        mOrderAdapter = new OrderAdapter(this);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mOrderAdapter);

    }

    @Override
    public void onOrderPlacedCardClick(OrderDetails orderDetails) {
        Intent intent = new Intent(MainActivity.this, OrderDetailsActivity.class);
        intent.putExtra("order", orderDetails);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mOrderAdapter.setOrder(mOrderManager.getOrderHistoryData());
    }
}