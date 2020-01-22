package com.yuplo.support.di.activity;

import com.yuplo.presenter.login.LoginPresenter;
import com.yuplo.presenter.signIn.SignUpPresenter;
import com.yuplo.view.activity.LoginActivity;
import com.yuplo.view.activity.SignUpActivity;

import dagger.Subcomponent;


@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(LoginActivity loginActivity);

    void inject(SignUpActivity signInActivity);

    void inject(LoginPresenter loginPresenter);

    void inject(SignUpPresenter signUpPresenter);

    @Subcomponent.Builder
    interface Builder {
        Builder activityModule(ActivityModule activityModule);
        ActivityComponent build();
    }
}
