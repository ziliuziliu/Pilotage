package com.eis.traderUI.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;
import com.eis.traderUI.ui.home.adapter.MarketDepthAdapter;

public class HomeFragment extends Fragment {
    private RecyclerView sellSideMarket;
    private RecyclerView buySideMarket;

    private MarketDepthAdapter sellAdapter=new MarketDepthAdapter(getContext().getResources().getString(R.string.market_item_sell_side),getContext());
    private MarketDepthAdapter buyAdapter=new MarketDepthAdapter(getContext().getResources().getString(R.string.market_item_buy_side),getContext());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_home, container, false);
        sellSideMarket=root.findViewById(R.id.sellSideMarket);
        buySideMarket=root.findViewById(R.id.buySideMarket);
        return root;
    }
}