package com.yuplo.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yuplo.support.Internet.NetworkChangeReceiver;
import com.yuplo.support.di.activity.ActivityComponent;
import com.yuplo.support.fragmentmanager.manager.SFMActivity;

import butterknife.ButterKnife;


public abstract class BaseActivity extends SFMActivity {


    private NetworkChangeReceiver receiver;
    private IntentFilter filter =null;

    protected abstract int getLayoutId();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            ButterKnife.bind(this);

        }

    }

    public ActivityComponent injector() {
        return ((MyApplication) getApplicationContext()).getActivityComponent(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
      /*  if(getLayoutId() != 0){
            filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            receiver = new NetworkChangeReceiver();
            registerReceiver(receiver, filter);}*/
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
