package com.example.orderplacer.adapter.orders_adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderplacer.R;
import com.example.orderplacer.databinding.OrderItemBinding;
import com.example.orderplacer.model.OrderDetail;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderItemViewHolder> {

    List<OrderDetail> mDetailsList = new ArrayList<>();
    OnOrderClickListener listener;

    public OrderAdapter(OnOrderClickListener listener) {
        this.listener = listener;
    }

    public void setOrder(List<OrderDetail> orderDetails) {
        mDetailsList = orderDetails;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderItemViewHolder(OrderItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        holder.binding.orderPlacedCard.setOnClickListener(v -> listener.onOrderClicked(mDetailsList.get(position)));
        holder.binding.statusBtn.setOnClickListener(v -> listener.onOrderCompletionChangeBtnClick(mDetailsList.get(position)));

        holder.binding.customerName.setText(mDetailsList.get(position).customerName);
        holder.binding.orderDetail.setText(mDetailsList.get(position).orderDetails);

        if (mDetailsList.get(position).isCompleted()) {
            holder.binding.statusBtn.setImageResource(R.drawable.ic_close);
            holder.binding.status.setText("Completed");
            holder.binding.status.setBackgroundResource(R.color.green);
        } else {
            holder.binding.statusBtn.setImageResource(R.drawable.ic_check);
            holder.binding.status.setText("INCOMPLETE");
            holder.binding.status.setBackgroundResource(R.color.gold);
        }
    }

    @Override
    public int getItemCount() {
        return mDetailsList.size();
    }
}
