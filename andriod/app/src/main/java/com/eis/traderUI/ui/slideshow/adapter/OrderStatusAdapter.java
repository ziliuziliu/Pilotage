package com.eis.traderUI.ui.slideshow.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;
import com.eis.traderUI.dto.OrderInfo;
import com.eis.traderUI.service.OrderService;
import com.eis.traderUI.ui.gallery.dto.Blotter;
import com.eis.traderUI.ui.slideshow.view.TextViewHolder;

import java.util.ArrayList;
import java.util.List;


public class OrderStatusAdapter extends RecyclerView.Adapter<TextViewHolder>{
    @NonNull
    private List<OrderInfo> orderInfoList=new ArrayList<>();

    private OrderService orderService;

    public void setOrderService(OrderService orderService){
        this.orderService=orderService;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_order_status_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.bind(orderInfoList.get(position),orderService);
    }

    public void updateOrderInfo(@NonNull List<OrderInfo> orderInfo){
        this.orderInfoList.clear();
        this.orderInfoList.addAll(orderInfo);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return orderInfoList.size();
    }
}
