package com.eis.traderUI.ui.market.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;
import com.eis.traderUI.ui.market.view.TextViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MarketDepthAdapter extends RecyclerView.Adapter<TextViewHolder> {
    @NonNull
    private String type;
    @NonNull
    private List<Integer> priceList=new ArrayList<>();
    @NonNull
    private List<Integer> quantityList=new ArrayList<>();

    public MarketDepthAdapter(String type){
        this.type=type;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_market_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        if(priceList.size()==0||quantityList.size()==0)return;
        if(type.equals("SELL")){
            holder.bind(type,5-position,priceList.get(4-position),quantityList.get(4-position));
        }else if(type.equals("BUY")){
            holder.bind(type,position,priceList.get(position),quantityList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return priceList.size();
    }

    public void updatePriceAndQuantity(@NonNull List<Integer> price,@NonNull List<Integer> quantity){
        this.priceList.clear();
        this.priceList.addAll(price);
        this.quantityList.clear();
        this.quantityList.addAll(quantity);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
