package com.yuplo.support.network;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonObject;
import com.yuplo.model.Model;
import com.yuplo.presenter.NetworkInterface;
import com.yuplo.support.network.ApiInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall {

    public void login(ApiInterface apiInterface, JsonObject data, NetworkInterface nListener) {
        Call<Model.Login> call = apiInterface.getUserLogin(data);
        call.enqueue(new Callback<Model.Login>() {
            @Override
            public void onResponse(Call<Model.Login> call, Response<Model.Login> response) {
                if (response.code() == 200) {
                    nListener.onNetworkSuccess(response);
                } else {
                    try {
                        if (response.errorBody() != null) {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            String error = jObjError.getString("error");
                            nListener.onNetworkFailure(error);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Model.Login> call, Throwable t) {
                nListener.onNetworkFailure(t.getMessage());
            }
        });
    }

    public void register(ApiInterface apiInterface, JsonObject data, NetworkInterface nListener) {
        Call<ResponseBody> call = apiInterface.getUserSignUp(data);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    nListener.onNetworkRegisterSuccess(response);
                } else {

                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        JSONObject error = object.getJSONObject("error");
                        JSONArray email = error.getJSONArray("email");
                        String emailerror = email.getString(0);
                        if(!TextUtils.isEmpty(emailerror)){
                            nListener.onNetworkRegisterFailure(emailerror);
                            return;
                        }
                        JSONArray password = error.getJSONArray("password");
                        JSONArray c_password = error.getJSONArray("c_password");
                        Log.d("output", object.toString());
                        Log.d("output", email.toString());
                        Log.d("output", password.toString());
                        Log.d("output", c_password.toString());
                        Log.d("output", emailerror);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                nListener.onNetworkRegisterFailure(t.getMessage());
            }
        });

    }
}
