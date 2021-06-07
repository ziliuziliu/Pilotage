package com.eis.traderUI.ui.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import com.eis.traderUI.R;

public class SpinnerAdapter extends BaseAdapter {
    private List<String> mData;
    private Context mContext;

    public SpinnerAdapter(List<String> mData, Context context) {
        this.mData = mData;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_spinner_item, parent, false);
        TextView textView=convertView.findViewById(R.id.spinnerText);
        textView.setText(mData.get(position));
        return convertView;
    }

    public void updateData(List<String> data){
        this.mData.clear();
        this.mData.addAll(data);
        notifyDataSetChanged();
    }
}
