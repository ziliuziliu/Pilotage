package com.eis.traderUI.ui.market.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;

public class TextViewHolder extends RecyclerView.ViewHolder{

    private TextView marketItemTitle;
    private TextView marketItemPrice;
    private TextView marketItemQuantity;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        marketItemTitle=itemView.findViewById(R.id.marketItemTitle);
        marketItemPrice=itemView.findViewById(R.id.marketItemPrice);
        marketItemQuantity=itemView.findViewById(R.id.marketItemQuantity);
    }

    public void bind(String type,Integer index,Integer price,Integer quantity){
        String title=type+" "+index;
        marketItemTitle.setText(title);
        marketItemPrice.setText(String.valueOf(price));
        marketItemQuantity.setText(String.valueOf(quantity));
    }
}
