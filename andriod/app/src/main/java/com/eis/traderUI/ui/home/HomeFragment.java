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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;
import com.eis.traderUI.dto.OrderInfo;
import com.eis.traderUI.dto.ProductData;
import com.eis.traderUI.service.ProductService;
import com.eis.traderUI.ui.common.adapter.SpinnerAdapter;
import com.eis.traderUI.ui.home.adapter.ProductAdapter;
import com.eis.traderUI.ui.market.adapter.MarketDepthAdapter;
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
    private RecyclerView productList;
    private ProductAdapter productAdapter=new ProductAdapter();
    private ProductService productService;
    private List<ProductData>productDataList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_home, container, false);
        productList=root.findViewById(R.id.productList);
        productList.setLayoutManager(new LinearLayoutManager(getContext()));
        productList.setAdapter(productAdapter);
        getData();
        return root;
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
                productDataList=response.body();
                System.out.println(productDataList);
                productAdapter.updateProduct(productDataList);
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductData>> call, @NonNull Throwable t) {
                Log.e(TAG, "fetch order blotter failed");
                Snackbar.make(productList, "接收失败", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}