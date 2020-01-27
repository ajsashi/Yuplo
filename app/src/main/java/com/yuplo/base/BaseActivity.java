package com.yuplo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.yuplo.R;
import com.yuplo.support.di.activity.ActivityComponent;
import com.yuplo.support.fragmentmanager.manager.SFMActivity;

import butterknife.ButterKnife;


public abstract class BaseActivity extends SFMActivity {

    protected abstract int getLayoutId();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
    }

    public ActivityComponent injector() {
        return ((MyApplication) getApplicationContext()).getActivityComponent(this);
    }

}
