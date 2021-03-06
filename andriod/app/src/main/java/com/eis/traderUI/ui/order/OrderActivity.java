package com.eis.traderUI.ui.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.eis.traderUI.MainActivity;
import com.eis.traderUI.R;
import com.eis.traderUI.dto.Msg;
import com.eis.traderUI.dto.OrderInfo;
import com.eis.traderUI.dto.ProductData;
import com.eis.traderUI.service.OrderService;
import com.eis.traderUI.service.ProductService;
import com.eis.traderUI.ui.common.adapter.SpinnerAdapter;
import com.eis.traderUI.ui.gallery.dto.Blotter;
import com.eis.traderUI.util.Constant;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    public static final String TAG="OrderActivity";
    private Spinner orderSideSpinner;
    private Spinner orderTypeSpinner;
    private EditText quantityInput;
    private EditText priceInput;
    private LinearLayout quantityLayout;
    private LinearLayout priceLayout;
    private Button submitButton;
    private Button cancelButton;
    private Switch icebergSwitch;
    private TextView productNameTitle;

    private SpinnerAdapter orderSideAdapter;
    private SpinnerAdapter orderTypeAdapter;

    private final String[] orderSide={"SELL","BUY"};
    private final String[] orderType={"MARKET", "LIMIT", "STOP"};
    private String productInfo;
    private String productName;
    private OrderInfo orderInfo=new OrderInfo();
    private boolean iceberg;

    private OrderService orderService;

    private SharedPreferences mySharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mySharedPreferences=getApplicationContext().getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        Intent intent=getIntent();
        productName=intent.getStringExtra("productName");
        productInfo=intent.getStringExtra("productInfo");
        orderInfo.setProduct(productName);
        bindView();
        bindData();
    }

    private void bindView(){
        orderSideSpinner=findViewById(R.id.orderSideSpinner);
        orderTypeSpinner=findViewById(R.id.orderTypeSpinner);
        quantityInput=findViewById(R.id.quantityInput);
        priceInput=findViewById(R.id.priceInput);
        quantityLayout=findViewById(R.id.quantityLayout);
        priceLayout=findViewById(R.id.priceLayout);
        submitButton=findViewById(R.id.submitOrderButton);
        cancelButton=findViewById(R.id.cancelButton);
        icebergSwitch=findViewById(R.id.icebergSwitch);
        productNameTitle=findViewById(R.id.productNameTitle);
    }

    private void bindData(){
        productNameTitle.setText(productInfo);
        orderSideAdapter=new SpinnerAdapter(Arrays.asList(orderSide),getApplicationContext());
        orderSideSpinner.setAdapter(orderSideAdapter);
        orderSideSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                orderInfo.setSide(orderSide[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        orderTypeAdapter=new SpinnerAdapter(Arrays.asList(orderType),getApplicationContext());
        orderTypeSpinner.setAdapter(orderTypeAdapter);
        orderTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                orderInfo.setType(orderType[position]);
                switch (position){
                    case 0:
                        quantityLayout.setVisibility(View.VISIBLE);
                        priceLayout.setVisibility(View.INVISIBLE);
                        icebergSwitch.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                    case 2:
                        quantityLayout.setVisibility(View.VISIBLE);
                        priceLayout.setVisibility(View.VISIBLE);
                        icebergSwitch.setVisibility(View.GONE);
                        break;
                    default:
                        quantityLayout.setVisibility(View.INVISIBLE);
                        priceLayout.setVisibility(View.INVISIBLE);
                        icebergSwitch.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!quantityInput.getText().toString().equals("")){
                    orderInfo.setQuantity(Integer.parseInt(quantityInput.getText().toString()));
                }
                if(!priceInput.getText().toString().equals("")){
                    orderInfo.setPrice(Integer.parseInt(priceInput.getText().toString()));
                }
                submitOrder();
            }
        });
        icebergSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iceberg=isChecked;
            }
        });
    }

    private void submitOrder(){
        orderService= Constant.RETROFIT.create(OrderService.class);
        Integer userId=mySharedPreferences.getInt("userId",0);
        String token=mySharedPreferences.getString("token","token");
        orderInfo.setUserId(userId);
        System.out.println(orderInfo.toString());
        if(iceberg){
            Call<Msg<Boolean>> orderCall=orderService.submitIceberg(token,orderInfo);
            orderCall.enqueue(new Callback<Msg<Boolean>>() {
                @Override
                public void onResponse(Call<Msg<Boolean>> call, Response<Msg<Boolean>> response) {
                    if (!response.isSuccessful()) {
                        Log.e(TAG, "response not success");
                        return;
                    }
                    if (response.body() == null) {
                        Log.e(TAG, "receive null body");
                        return;
                    }
                    startActivity(new Intent(OrderActivity.this, MainActivity.class));
                }

                @Override
                public void onFailure(Call<Msg<Boolean>> call, Throwable t) {
                    Log.e(TAG, "fetch order blotter failed");
                    Snackbar.make(submitButton, "????????????", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
        else {
            Call<Msg<JsonObject>> orderCall = orderService.submitOrder(token, orderInfo);
            orderCall.enqueue(new Callback<Msg<JsonObject>>() {
                @Override
                public void onResponse(Call<Msg<JsonObject>> call, Response<Msg<JsonObject>> response) {
                    if (!response.isSuccessful()) {
                        Log.e(TAG, "response not success");
                        return;
                    }
                    if (response.body() == null) {
                        Log.e(TAG, "receive null body");
                        return;
                    }
                    System.out.println(response.body().getMsg());
                    startActivity(new Intent(OrderActivity.this, MainActivity.class));
                }

                @Override
                public void onFailure(Call<Msg<JsonObject>> call, Throwable t) {
                    Log.e(TAG, "fetch order blotter failed");
                    Snackbar.make(submitButton, "????????????", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }
}