package com.yuplo.presenter;

import android.content.Context;
import android.content.Intent;

import com.yuplo.base.BaseActivity;
import com.yuplo.base.BaseFragment;
import com.yuplo.support.Constants;
import com.yuplo.support.MyPreferenceManager;
import com.yuplo.view.activity.LoginActivity;

import javax.inject.Inject;

public class HomePresenter {
    @Inject
    MyPreferenceManager preferenceManager;
    private Context context;

    public void init(Context context){
        this.context = context;
        ((BaseActivity)context).injector().inject(this);
    }

    public void logout(){
        preferenceManager.storeBoolsData(Constants.getLoggedIn(),false);
        context.startActivity(new Intent(context, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

}
