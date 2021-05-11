package com.example.orderplacer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.example.orderplacer.Utils.OrderDao;
import com.example.orderplacer.adapter.orders_adapter.OrderAdapter;
import com.example.orderplacer.databinding.ActivityMainBinding;
import com.example.orderplacer.adapter.orders_adapter.OnOrderClickListener;
import com.example.orderplacer.model.OrderDetail;

public class MainActivity extends AppCompatActivity implements OnOrderClickListener {

    ActivityMainBinding mBinding;
    OrderDao mOrderDao;
    OrderAdapter mOrderAdapter;
    String mFilter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mOrderDao = OrderDao.buildWith(openOrCreateDatabase(OrderDao.ORDER_DATABASE, MODE_PRIVATE, null));

        mBinding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFilter = s.toString();
                mOrderAdapter.setOrder(mOrderDao.getAllOrders(mFilter));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.floatingActionButton.setOnClickListener(v -> startActivity(new Intent(this, CreateOrUpdateOrder.class)));

        mOrderAdapter = new OrderAdapter(this);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mOrderAdapter);
    }

    @Override
    public void onOrderClicked(OrderDetail orderDetails) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order", orderDetails);
        startActivity(intent);
    }

    @Override
    public void onOrderCompletionChangeBtnClick(OrderDetail orderDetail) {
        mOrderDao.updateOrderCompletion(orderDetail.getId(), !orderDetail.isCompleted());
        mOrderAdapter.setOrder(mOrderDao.getAllOrders(mFilter));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mOrderAdapter.setOrder(mOrderDao.getAllOrders(mFilter));
    }
}