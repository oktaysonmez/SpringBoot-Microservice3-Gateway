package com.sha.gateway.channel.service;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ProductServiceCallable
{
    @GET("api/product")
    Call<List<JsonElement>> getAll();

    @DELETE("api/product/{productID}")
    Call<Void> deleteByID(@Path("productID") Integer productID);

    @POST("api/product")
    Call<JsonElement> save(@Body JsonElement requestBody);


}
