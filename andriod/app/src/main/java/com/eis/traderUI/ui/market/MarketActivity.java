package com.eis.traderUI.ui.market;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.eis.traderUI.R;
import com.eis.traderUI.dto.MarketDepth;
import com.eis.traderUI.dto.ProductData;
import com.eis.traderUI.service.ProductService;
import com.eis.traderUI.ui.common.adapter.SpinnerAdapter;
import com.eis.traderUI.ui.market.adapter.MarketDepthAdapter;
import com.eis.traderUI.util.Constant;
import com.eis.traderUI.util.MyWebSocketClient;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;

public class MarketActivity extends AppCompatActivity {
    public static final String TAG="MarketActivity";
    private RecyclerView sellSideMarket;
    private RecyclerView buySideMarket;
    private TextView productTitle;

    private MarketDepthAdapter sellAdapter=new MarketDepthAdapter("SELL");
    private MarketDepthAdapter buyAdapter=new MarketDepthAdapter("BUY");

    private String productName;
    private String productInfo;
    private MyWebSocketClient webSocketClient;
    private MarketDepth marketDepth;

    private String url="ws://202.120.40.8:30551/marketDepth/broker/";

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        sellSideMarket=findViewById(R.id.sellSideMarket);
        buySideMarket=findViewById(R.id.buySideMarket);
        productTitle=findViewById(R.id.productName);
        productName=getIntent().getStringExtra("productName");
        productInfo=getIntent().getStringExtra("productInfo");
        productTitle.setText(productInfo);
        sellSideMarket.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        buySideMarket.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        sellSideMarket.setAdapter(sellAdapter);
        buySideMarket.setAdapter(buyAdapter);
        setupWebSocket();
    }

    private void setupWebSocket() throws URISyntaxException {
        if(webSocketClient!=null&&webSocketClient.isOpen()){
            webSocketClient.close();
        }
        url=url+productName;
        webSocketClient=new MyWebSocketClient(url){
            @Override
            public void onMessage(String message) {
//                Log.i(TAG,"ws receive message: "+message);
                marketDepth= Constant.GSON.fromJson(message,MarketDepth.class);
                System.out.println(marketDepth.toString());
                sellAdapter.updatePriceAndQuantity(marketDepth.getSellPrice(),marketDepth.getSellVolume());
                buyAdapter.updatePriceAndQuantity(marketDepth.getBuyPrice(),marketDepth.getBuyVolume());
            }
        };
        webSocketClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(webSocketClient!=null&&webSocketClient.isOpen()){
            webSocketClient.close();
        }
    }
}