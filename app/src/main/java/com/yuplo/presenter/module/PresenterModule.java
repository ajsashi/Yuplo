package com.yuplo.presenter.module;

import com.yuplo.presenter.HomePresenter;
import com.yuplo.presenter.login.LoginPresenter;
import com.yuplo.presenter.signIn.SignUpPresenter;
import com.yuplo.view.fragment.ProfileScreenFragment;

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

    @Provides
    public HomePresenter homeScreenPresenter(){
        return new HomePresenter();
    }


}
