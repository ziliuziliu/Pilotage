package com.eis.traderUI.ui.slideshow.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;
import com.eis.traderUI.dto.OrderInfo;
import com.eis.traderUI.ui.gallery.dto.Blotter;

public class TextViewHolder extends RecyclerView.ViewHolder{

    private TextView product;
    private TextView price;
    private TextView quantity;
    private TextView status;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        product=itemView.findViewById(R.id.orderProduct);
        price=itemView.findViewById(R.id.orderPrice);
        quantity=itemView.findViewById(R.id.orderQuantity);
        status=itemView.findViewById(R.id.orderStatus);
    }

    public void bind(OrderInfo orderInfo){
        product.setText(orderInfo.getProduct());
        price.setText(String.valueOf(orderInfo.getPrice()));
        quantity.setText(String.valueOf(orderInfo.getQuantity()));
        status.setText(orderInfo.getStatus());
    }
}
