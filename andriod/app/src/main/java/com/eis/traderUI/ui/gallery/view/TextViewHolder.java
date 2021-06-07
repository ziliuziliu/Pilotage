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
    private TextView buyName;
    private TextView buyCompany;
    private TextView sellName;
    private TextView sellCompany;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        tradeId=itemView.findViewById(R.id.tradeId);
        product=itemView.findViewById(R.id.product);
        price=itemView.findViewById(R.id.price);
        quantity=itemView.findViewById(R.id.quantity);
        buyName=itemView.findViewById(R.id.buyName);
        buyCompany=itemView.findViewById(R.id.buyCompany);
        sellName=itemView.findViewById(R.id.sellName);
        sellCompany=itemView.findViewById(R.id.sellCompany);
    }

    public void bind(Blotter blotter){
        tradeId.setText("id: "+blotter.getTradeId());
        product.setText("product: "+blotter.getProduct());
        price.setText("price: "+String.valueOf(blotter.getPrice()));
        quantity.setText("quantity: "+String.valueOf(blotter.getQuantity()));
        buyName.setText(blotter.getBuyName());
        buyCompany.setText(blotter.getBuyCompany());
        sellCompany.setText(blotter.getSellCompany());
        sellName.setText(blotter.getSellName());
    }
}
