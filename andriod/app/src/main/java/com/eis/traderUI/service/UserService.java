package com.eis.traderUI.service;

import com.eis.traderUI.dto.Msg;
import com.eis.traderUI.dto.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("trader/login")
    Call<Msg<UserInfo>> login(@Body UserInfo userInfo);
}
