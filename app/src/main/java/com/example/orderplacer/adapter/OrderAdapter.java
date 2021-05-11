package com.example.orderplacer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderplacer.databinding.OrderItemsBinding;
import com.example.orderplacer.model.OnOrderPlacedCardListener;
import com.example.orderplacer.model.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    List<OrderDetails> mDetailsList = new ArrayList<>();
    OnOrderPlacedCardListener listener;


    public OrderAdapter(OnOrderPlacedCardListener listener) {
        this.listener = listener;
    }


    public void setOrder(List<OrderDetails> orderDetails) {
        mDetailsList = orderDetails;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(OrderItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.binding.orderPlacedCard.setOnClickListener(v -> listener.onOrderPlacedCardClick(mDetailsList.get(position)));
        holder.binding.customerName.setText(mDetailsList.get(position).customerName);
        holder.binding.orderDetail.setText(mDetailsList.get(position).orderDetails);

    }

    @Override
    public int getItemCount() {
        return mDetailsList.size();
    }
}
