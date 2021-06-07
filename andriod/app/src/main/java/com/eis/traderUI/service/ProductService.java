package com.eis.traderUI.service;

import com.eis.traderUI.dto.ProductData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("process/product/findAll")
    Call<List<ProductData>> getAllProduct();
}
