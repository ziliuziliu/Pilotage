package com.eis.traderUI.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Constant {
    public static final String API_URL = "http://202.120.40.8:30551/trader/";
    public static final Gson GSON = new GsonBuilder()
            .create();
    public static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build();
}
