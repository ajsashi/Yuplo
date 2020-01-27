package com.yuplo.support.di.activity;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.yuplo.base.BaseActivity;
import com.yuplo.presenter.module.AdapterModule;
import com.yuplo.presenter.module.PresenterModule;
import com.yuplo.support.Constants;
import com.yuplo.support.network.NetworkCall;
import com.yuplo.support.Utils;
import com.yuplo.support.fragmentmanager.manager.FragmentManagerHandler;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@ActivityScope
@Module(includes = {PresenterModule.class, AdapterModule.class})
public class ActivityModule {

    private BaseActivity baseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @Named(Constants.ACTIVITY_CONTEXT)
    public Context provideActivityContext() {
        return baseActivity;
    }

    @Provides
    public Utils provideUtils() {
        return new Utils();
    }

    @Provides
    public NetworkCall provideNetworkCall() {
        return new NetworkCall();
    }

    @Provides
    public FragmentManager provideFManager(){
        return baseActivity.getSupportFragmentManager();
    }

    @Provides
    public FragmentManagerHandler provideFMHandler(FragmentManager fragmentManager) {
        return new FragmentManagerHandler(fragmentManager);
    }
}
