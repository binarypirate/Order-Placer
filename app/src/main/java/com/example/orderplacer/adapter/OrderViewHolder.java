package com.example.orderplacer.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderplacer.databinding.OrderItemsBinding;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    OrderItemsBinding binding;

    public OrderViewHolder(@NonNull OrderItemsBinding binding) {
        super( binding.getRoot());
        this.binding = binding;
    }
}
