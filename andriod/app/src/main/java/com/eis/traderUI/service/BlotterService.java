package com.eis.traderUI.service;

import com.eis.traderUI.dto.Msg;
import com.eis.traderUI.ui.gallery.dto.Blotter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface BlotterService {
    @GET("trader/transaction")
    Call<Msg<List<Blotter>>> getOrderBlotter(@Header("token")String token,
                                             @Query("userId")Integer userId);
}
