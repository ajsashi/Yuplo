package com.yuplo.presenter.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;

import com.google.gson.JsonObject;
import com.onurkaganaldemir.ktoastlib.KToast;
import com.yuplo.R;
import com.yuplo.base.BaseActivity;
import com.yuplo.model.Model;
import com.yuplo.presenter.NetworkInterface;
import com.yuplo.support.network.ApiInterface;
import com.yuplo.support.Constants;
import com.yuplo.support.MyPreferenceManager;
import com.yuplo.support.network.NetworkCall;
import com.yuplo.support.Utils;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenter implements NetworkInterface {
    private Context context;

    @Inject
    Retrofit retrofit;
    @Inject
    NetworkCall networkCall;
    @Inject
    Utils utils;
    @Inject
    MyPreferenceManager preferenceManager;
    private ILoginPresenter pListener;


    public void init(Context context, ILoginPresenter pListener) {
        ((BaseActivity) context).injector().inject(this);
        this.context = context;
        this.pListener = pListener;
    }

    public Boolean isLoggedIn(){
        return preferenceManager.getBoolsData(Constants.getLoggedIn());
    }

    public void validation(String email, String password) {
        boolean valid = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (email.isEmpty()) {
            KToast.customColorToast((Activity) context, "Please enter an email address", Gravity.BOTTOM, KToast.LENGTH_AUTO, R.color.red);
        } else if (password.isEmpty()) {
            KToast.customColorToast((Activity) context, "Please enter your password", Gravity.BOTTOM, KToast.LENGTH_SHORT, R.color.red);
        } else if (valid) {
            JsonObject data = Constants.getUserLoginJson(email, password);
            pListener.showProgress();
            apiCall(data);
        } else {
            KToast.customColorToast((Activity) context, "Please enter a valid email address", Gravity.BOTTOM, KToast.LENGTH_SHORT, R.color.red);
        }
        Log.d("validation", String.valueOf(valid));
    }

    private void apiCall(JsonObject data) {
        final ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        if (utils.isOnline(context)) {
            networkCall.login(apiInterface, data, this);
        } else {
            pListener.dismissProgress();
            KToast.customColorToast((Activity) context, " Please Check Your Network Connection", Gravity.BOTTOM, KToast.LENGTH_SHORT, R.color.red);
        }
    }

    @Override
    public void onNetworkSuccess(Response<Model.Login> response) {
        preferenceManager.storeData(Constants.getTOKEN(), response.body().getToken());
        preferenceManager.storeBoolsData(Constants.getLoggedIn(), true);
        pListener.dismissProgress();
        pListener.navigate();
    }

    @Override
    public void onNetworkFailure(String message) {
        pListener.dismissProgress();
        KToast.errorToast((Activity) context,message,Gravity.BOTTOM,KToast.LENGTH_SHORT);
    }

    @Override
    public void onNetworkRegisterSuccess(Response<ResponseBody> response) {
        //Do Nothing
    }

    @Override
    public void onNetworkRegisterFailure(String message) {
//Do Nothing
    }


}
