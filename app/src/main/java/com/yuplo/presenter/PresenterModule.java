package com.yuplo.presenter;

import android.util.Log;

import com.yuplo.presenter.login.LoginPresenter;
import com.yuplo.presenter.signIn.SignUpPresenter;

import dagger.Module;
import dagger.Provides;

@Module()
public class PresenterModule {

    @Provides
    public LoginPresenter loginPresenter(){
        return new LoginPresenter();
    }

    @Provides
    public SignUpPresenter signUpPresenter(){
        return new SignUpPresenter();
    }

}
