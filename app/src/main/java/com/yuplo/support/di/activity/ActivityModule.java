package com.yuplo.support.di.activity;

import android.content.Context;

import com.yuplo.base.BaseActivity;
import com.yuplo.presenter.PresenterModule;
import com.yuplo.support.Constants;
import com.yuplo.support.NetworkCall;
import com.yuplo.support.Utils;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@ActivityScope
@Module(includes = {PresenterModule.class})
public class ActivityModule {

    private final BaseActivity baseActivity;

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
    public NetworkCall provideNetworkCall(){
        return new NetworkCall();
    }

   /* @Provides
    public FragmentManager provideFragmentManager() {
        return baseActivity.getSupportFragmentManager();
    }

    @Provides
    public FragmentManagerHandler provideStarterKitFragmentManager(FragmentManager fragmentManager) {
        return new FragmentManagerHandler(fragmentManager);
    }*/
}
