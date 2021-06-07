package com.eis.traderUI.service;

import com.eis.traderUI.dto.Msg;
import com.eis.traderUI.dto.OrderInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {
    @GET("trader/order")
    Call<Msg<List<OrderInfo>>> getOrderList(@Header("token")String token,
                                            @Query("userId")Integer userId);

    @POST("trader/order")
    Call<Msg<List<OrderInfo>>> submitOrder(@Header("token")String token,
                                           @Body OrderInfo orderInfo);

    @POST("trader/iceberg")
    Call<Msg<Boolean>> submitIceberg(@Header("token")String token,
                                           @Body OrderInfo orderInfo);
}
