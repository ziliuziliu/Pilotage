package com.eis.traderUI.ui.gallery.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;
import com.eis.traderUI.ui.gallery.dto.Blotter;
import com.eis.traderUI.ui.gallery.view.TextViewHolder;

import java.util.ArrayList;
import java.util.List;


public class BlotterAdapter extends RecyclerView.Adapter<TextViewHolder>{

    @NonNull
    private List<Blotter> blotterList=new ArrayList<>();

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_order_blotter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        if(blotterList.isEmpty()){
            System.out.println("null blotterList");
            return;
        }
        holder.bind(blotterList.get(position));
    }

    public void updateBlotter(@NonNull List<Blotter> blotters){
        this.blotterList=blotters;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return blotterList.size();
    }
}
