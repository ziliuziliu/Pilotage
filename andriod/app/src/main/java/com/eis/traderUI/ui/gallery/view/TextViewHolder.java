package com.eis.traderUI.ui.gallery.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;
import com.eis.traderUI.ui.gallery.dto.Blotter;

public class TextViewHolder extends RecyclerView.ViewHolder{

    private TextView tradeId;
    private TextView product;
    private TextView price;
    private TextView quantity;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        tradeId=itemView.findViewById(R.id.tradeId);
        product=itemView.findViewById(R.id.product);
        price=itemView.findViewById(R.id.price);
        quantity=itemView.findViewById(R.id.quantity);
    }

    public void bind(Blotter blotter){
        tradeId.setText(blotter.getTradeId());
        product.setText(blotter.getProduct());
        price.setText(String.valueOf(blotter.getPrice()));
        quantity.setText(String.valueOf(blotter.getQuantity()));
    }
}
