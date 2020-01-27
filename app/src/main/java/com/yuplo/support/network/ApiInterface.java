package com.yuplo.support.network;

import com.google.gson.JsonObject;
import com.yuplo.model.Model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("login")
    Call<Model.Login> getUserLogin(@Body JsonObject json);


    @POST("register")
    Call<ResponseBody> getUserSignUp(@Body JsonObject json);
}
