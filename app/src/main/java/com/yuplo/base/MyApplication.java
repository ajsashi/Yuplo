package com.yuplo.base;

import android.app.Application;

import com.yuplo.support.di.activity.ActivityComponent;
import com.yuplo.support.di.activity.ActivityModule;
import com.yuplo.support.di.app.AndroidModule;
import com.yuplo.support.di.app.ApplicationComponent;
import com.yuplo.support.di.app.ApplicationModule;
import com.yuplo.support.di.app.DaggerApplicationComponent;


public class MyApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ActivityComponent getActivityComponent(BaseActivity baseActivity) {
        return component.activityComponentBuilder().activityModule(new ActivityModule(baseActivity)).build();
    }
}