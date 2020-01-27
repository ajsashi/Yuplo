package com.yuplo.presenter.signIn;

import android.app.Activity;
import android.content.Context;
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
import com.yuplo.support.network.NetworkCall;
import com.yuplo.support.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.yuplo.support.Constants.PASSWORD_PATTERN;

public class SignUpPresenter implements NetworkInterface {


    @Inject
    Utils utils;
    @Inject
    NetworkCall networkCall;
    @Inject
    Retrofit retrofit;
    private Context context;
    private ISignUpPresenter pListener;

    public void init(Context context, ISignUpPresenter pListener) {
        ((BaseActivity) context).injector().inject(this);
        this.context = context;
        this.pListener = pListener;
    }

    public void validation(String fName, String lName, String email, String password, String conPassword) {
        Pattern pattern = Pattern.compile(
                Constants.getPasswordPattern());
        Matcher matcher = pattern.matcher(password);
        if (!fName.isEmpty() && !lName.isEmpty() && !email.isEmpty() && !password.isEmpty() && !conPassword.isEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                KToast.normalToast((Activity) context, "Please enter a valid email address", Gravity.BOTTOM, KToast.LENGTH_AUTO);
            } else if (!matcher.matches()) {
                KToast.normalToast((Activity) context, "Password must contain upper,lower case letters, digits and one special character(>6)", Gravity.BOTTOM, KToast.LENGTH_AUTO);
            } else if (!password.equals(conPassword)) {
                KToast.normalToast((Activity) context, "Password Mismatch", Gravity.BOTTOM, KToast.LENGTH_AUTO);
            } else {
                pListener.showProgress();
                JsonObject data = Constants.getUserSignUpJson(fName, lName, email, password, conPassword);
                apiCall(data);
            }
        } else {
            KToast.normalToast((Activity) context, "Input Field Missing", Gravity.BOTTOM, KToast.LENGTH_AUTO);
        }
    }

    private void apiCall(JsonObject data) {
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        if (utils.isOnline(context)) {
            networkCall.register(apiInterface, data, this);
        } else {
            KToast.customColorToast((Activity) context, " Please Check Your Network Connection", Gravity.BOTTOM, KToast.LENGTH_SHORT, R.color.red);
        }
    }

    @Override
    public void onNetworkSuccess(Response<Model.Login> response) {
//Do Nothing
    }

    @Override
    public void onNetworkFailure(String message) {
//Do Nothing
    }

    @Override
    public void onNetworkRegisterSuccess(Response<ResponseBody> response) {
        pListener.dismissProgress();
        pListener.navigate();
    }


    @Override
    public void onNetworkRegisterFailure(String message) {
        pListener.dismissProgress();
        KToast.errorToast((Activity) context, message, Gravity.BOTTOM, KToast.LENGTH_SHORT);
    }
}
