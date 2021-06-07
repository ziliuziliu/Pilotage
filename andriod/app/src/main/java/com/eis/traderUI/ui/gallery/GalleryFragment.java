package com.eis.traderUI.ui.gallery;

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
import com.eis.traderUI.service.BlotterService;
import com.eis.traderUI.ui.gallery.adapter.BlotterAdapter;
import com.eis.traderUI.ui.gallery.dto.Blotter;
import com.eis.traderUI.util.Constant;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryFragment extends Fragment {
    private static final String TAG = "blotterFragment";

    private BlotterService blotterService;
    private RecyclerView recyclerView;
    private BlotterAdapter blotterAdapter=new BlotterAdapter();

    private SharedPreferences mySharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView=root.findViewById(R.id.orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(blotterAdapter);
        mySharedPreferences=getContext().getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        getBlotter();
        return root;
    }

    private void getBlotter(){
        blotterService= Constant.RETROFIT.create(BlotterService.class);
        Integer userId=mySharedPreferences.getInt("userId",0);
        String token=mySharedPreferences.getString("token","token");
        Call<Msg<List<Blotter>>> blotterCall = blotterService.getOrderBlotter(token,userId);
        blotterCall.enqueue(new Callback<Msg<List<Blotter>>>() {
            @Override
            public void onResponse(@NonNull Call<Msg<List<Blotter>>> call, @NonNull Response<Msg<List<Blotter>>> response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "response not success");
                    return;
                }
                if (response.body() == null) {
                    Log.e(TAG, "receive null body");
                    return;
                }
                Log.i(TAG,response.body().getMsg());
                final List<Blotter> blotterList = response.body().getData();
                if (blotterList == null || blotterList.isEmpty()) {
                    Log.i(TAG,"empty blotter data");
                    return;
                }
                blotterAdapter.updateBlotter(blotterList);
            }

            @Override
            public void onFailure(@NonNull Call<Msg<List<Blotter>>> call, @NonNull Throwable t) {
                Log.e(TAG, "fetch order blotter failed");
                Snackbar.make(recyclerView, "接收失败", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}