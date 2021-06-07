package com.eis.traderUI.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;
import com.eis.traderUI.dto.ProductData;
import com.eis.traderUI.ui.home.view.TextViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<TextViewHolder>{
    @NonNull
    private List<ProductData> productDataList=new ArrayList<>();

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.bind(productDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }

    public void updateProduct(List<ProductData> productData){
        this.productDataList.clear();
        this.productDataList.addAll(productData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
