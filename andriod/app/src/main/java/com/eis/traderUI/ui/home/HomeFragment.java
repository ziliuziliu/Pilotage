package com.eis.traderUI.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;
import com.eis.traderUI.dto.OrderInfo;
import com.eis.traderUI.dto.ProductData;
import com.eis.traderUI.service.ProductService;
import com.eis.traderUI.ui.common.adapter.SpinnerAdapter;
import com.eis.traderUI.ui.home.adapter.MarketDepthAdapter;
import com.eis.traderUI.util.Constant;
import com.eis.traderUI.util.MyWebSocketClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    public static final String TAG="HomeFragment";
    private RecyclerView sellSideMarket;
    private RecyclerView buySideMarket;
    private Spinner orderProductSpinner;

    private MarketDepthAdapter sellAdapter=new MarketDepthAdapter("卖");
    private MarketDepthAdapter buyAdapter=new MarketDepthAdapter("买");
    private SpinnerAdapter productAdapter;

    private List<String> productInfoList=new ArrayList<>();
    private List<ProductData> productDataList;

    private ProductService productService;
    private String product;
    private MyWebSocketClient webSocketClient;

    private String url="ws://202.120.40.8:30551/marketDepth/broker/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_home, container, false);
        sellSideMarket=root.findViewById(R.id.sellSideMarket);
        buySideMarket=root.findViewById(R.id.buySideMarket);
        orderProductSpinner=root.findViewById(R.id.productSpinner);
        sellSideMarket.setAdapter(sellAdapter);
        buySideMarket.setAdapter(buyAdapter);
        getData();
        bind();
        return root;
    }

    private void bind(){
        productAdapter=new SpinnerAdapter(productInfoList,getContext());
        orderProductSpinner.setAdapter(productAdapter);
        orderProductSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SneakyThrows
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product=productDataList.get(position).getProductName();
                System.out.println(product);
                url=url+product;
                webSocketClient=new MyWebSocketClient(url){
                    @Override
                    public void onMessage(String message) {
                        Log.i(TAG,"ws receive message: "+message);
                    }
                };
                webSocketClient.connect();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getData(){
        productService= Constant.RETROFIT.create(ProductService.class);
        Call<List<ProductData>> productCall=productService.getAllProduct();
        productCall.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductData>> call, @NonNull Response<List<ProductData>> response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "response not success");
                    return;
                }
                if (response.body() == null) {
                    Log.e(TAG, "receive null body");
                    return;
                }
                final List<ProductData> result=response.body();
                productDataList=result;
                for(ProductData item:result){
                    productInfoList.add(item.getProductInfo());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductData>> call, @NonNull Throwable t) {
                Log.e(TAG, "fetch order blotter failed");
                Snackbar.make(buySideMarket, "接收失败", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}