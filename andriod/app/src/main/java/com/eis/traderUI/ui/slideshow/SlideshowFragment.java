package com.eis.traderUI.ui.slideshow;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.R;
import com.eis.traderUI.dto.Msg;
import com.eis.traderUI.dto.OrderInfo;
import com.eis.traderUI.service.OrderService;
import com.eis.traderUI.ui.gallery.dto.Blotter;
import com.eis.traderUI.ui.slideshow.adapter.OrderStatusAdapter;
import com.eis.traderUI.util.Constant;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {
    private static final String TAG = "blotterFragment";

    private RecyclerView recyclerView;
    private OrderStatusAdapter orderStatusAdapter;
    private OrderService orderService;
    private SharedPreferences mySharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        recyclerView=root.findViewById(R.id.orderStatusList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(orderStatusAdapter);
        mySharedPreferences=getContext().getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        getData();
        return root;
    }

    private void getData(){
        orderService= Constant.RETROFIT.create(OrderService.class);
        Integer userId=mySharedPreferences.getInt("userId",0);
        String token=mySharedPreferences.getString("token","token");
        Call<Msg<List<OrderInfo>>> blotterCall = orderService.getOrderList(token,userId);
        blotterCall.enqueue(new Callback<Msg<List<OrderInfo>>>() {
            @Override
            public void onResponse(@NonNull Call<Msg<List<OrderInfo>>> call, @NonNull Response<Msg<List<OrderInfo>>> response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "response not success");
                    return;
                }
                if (response.body() == null) {
                    Log.e(TAG, "receive null body");
                    return;
                }
                Log.i(TAG,response.body().getMsg());
                final List<OrderInfo> orderInfoList = response.body().getData();
                if (orderInfoList == null || orderInfoList.isEmpty()) {
                    Log.i(TAG,"empty blotter data");
                    return;
                }
                orderStatusAdapter.updateOrderInfo(orderInfoList);
            }

            @Override
            public void onFailure(@NonNull Call<Msg<List<OrderInfo>>> call, @NonNull Throwable t) {
                Log.e(TAG, "fetch order blotter failed");
                Snackbar.make(recyclerView, "接收失败", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}