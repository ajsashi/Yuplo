package com.yuplo.presenter;

import com.yuplo.model.Model;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface NetworkInterface {
    void onNetworkSuccess(Response<Model.Login> response);
    void onNetworkFailure(String message);
    void onNetworkRegisterSuccess(Response<ResponseBody> response);
    void onNetworkRegisterFailure(String message);
}
