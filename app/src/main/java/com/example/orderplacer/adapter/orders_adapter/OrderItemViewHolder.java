package com.example.orderplacer.adapter.orders_adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderplacer.databinding.OrderItemBinding;

public class OrderItemViewHolder extends RecyclerView.ViewHolder {
    OrderItemBinding binding;

    public OrderItemViewHolder(@NonNull OrderItemBinding binding) {
        super( binding.getRoot());
        this.binding = binding;
    }
}
