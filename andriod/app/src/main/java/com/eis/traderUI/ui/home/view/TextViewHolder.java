package com.eis.traderUI.ui.home.view;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;
import com.eis.traderUI.dto.ProductData;
import com.eis.traderUI.ui.market.MarketActivity;

import org.w3c.dom.Text;

public class TextViewHolder extends RecyclerView.ViewHolder {

    private TextView productId;
    private TextView productName;
    private ProductData productData;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        productId=itemView.findViewById(R.id.productId);
        productName=itemView.findViewById(R.id.productInfo);
    }

    public void bind(ProductData productData){
        productId.setText(String.valueOf(productData.getProductId()));
        productName.setText(productData.getProductInfo());
        this.productData=productData;
        productName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MarketActivity.class);
                intent.putExtra("productName", productData.getProductName());
                intent.putExtra("productInfo", productData.getProductInfo());
                v.getContext().startActivity(intent);
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        System.out.println("click");
//        Intent intent = new Intent(v.getContext(), MarketActivity.class);
//        intent.putExtra("productName", productData.getProductName());
//        intent.putExtra("productInfo", productData.getProductInfo());
//        v.getContext().startActivity(intent);
//    }
}
