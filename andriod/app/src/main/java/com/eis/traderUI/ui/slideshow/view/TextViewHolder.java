package com.eis.traderUI.ui.slideshow.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.eis.traderUI.MainActivity;
import com.eis.traderUI.R;
import com.eis.traderUI.dto.Msg;
import com.eis.traderUI.dto.OrderInfo;
import com.eis.traderUI.service.OrderService;
import com.eis.traderUI.ui.gallery.dto.Blotter;
import com.eis.traderUI.ui.order.OrderActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TextViewHolder extends RecyclerView.ViewHolder{
    public static final String TAG="TextViewHolder";

    private TextView product;
    private TextView price;
    private TextView quantity;
    private TextView status;
    private TextView cancelOperation;
    private OrderService orderService;

    private OrderInfo orderInfo;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        product=itemView.findViewById(R.id.orderProduct);
        price=itemView.findViewById(R.id.orderPrice);
        quantity=itemView.findViewById(R.id.orderQuantity);
        status=itemView.findViewById(R.id.orderStatus);
        cancelOperation=itemView.findViewById(R.id.operation);
    }

    public void bind(OrderInfo orderInfo, OrderService orderService){
        this.orderInfo=orderInfo;
        this.orderService=orderService;
        product.setText(orderInfo.getProduct());
        price.setText(String.valueOf(orderInfo.getPrice()));
        quantity.setText(String.valueOf(orderInfo.getQuantity()));
        status.setText(orderInfo.getStatus());
        if(orderInfo.getStatus().equals("WAITING")){
            cancelOperation.setText("CANCEL");
            cancelOperation.setTextColor(Color.BLUE);
            cancelOperation.setClickable(true);
            cancelOperation.setOnClickListener(v -> {
                AlertDialog.Builder dialog=new AlertDialog.Builder(v.getContext());
                //获取AlertDialog对象
                dialog.setTitle("CANCEL ORDER");//设置标题
                dialog.setMessage("sure to cancel?");//设置信息具体内容
                dialog.setCancelable(true);//设置是否可取消
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override//设置ok的事件
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cancelOrder();
                    }
                });
                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override//设置取消事件
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //在此写入取消的事件
                    }
                });
                dialog.show();
            });
        }else{
            cancelOperation.setText("NONE");
            cancelOperation.setTextColor(Color.BLACK);
            cancelOperation.setClickable(false);
        }
    }

    private void cancelOrder(){
        SharedPreferences mySharedPreferences=cancelOperation.getContext().getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        String token=mySharedPreferences.getString("token","token");
        orderInfo.setType("CANCEL");
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
            }

            @Override
            public void onFailure(Call<Msg<JsonObject>> call, Throwable t) {
                Log.e(TAG, "fetch order blotter failed");
                Snackbar.make(cancelOperation, "接收失败", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
