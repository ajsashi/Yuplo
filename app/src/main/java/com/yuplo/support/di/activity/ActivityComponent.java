package com.yuplo.support.di.activity;

import com.yuplo.presenter.HomePresenter;
import com.yuplo.presenter.profile.ProfileScreenPresenter;
import com.yuplo.presenter.login.LoginPresenter;
import com.yuplo.presenter.schedule.ScheduleScreenPresenter;
import com.yuplo.presenter.signIn.SignUpPresenter;
import com.yuplo.view.activity.HomeActivity;
import com.yuplo.view.activity.LoginActivity;
import com.yuplo.view.activity.SignUpActivity;
import com.yuplo.view.activity.SplashActivity;
import com.yuplo.view.fragment.FinalScheduleFragment;
import com.yuplo.view.fragment.HomeScreenFragment;
import com.yuplo.view.fragment.ManageAddressFragment;
import com.yuplo.view.fragment.NewScheduleScreenFragment;
import com.yuplo.view.fragment.ProfileScreenFragment;
import com.yuplo.view.fragment.ScheduleScreenFragment;

import dagger.Subcomponent;


@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {


    /*********Activities***********/

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(SignUpActivity signInActivity);

    void inject(HomeActivity homeActivity);

    /*********Fragments***********/

    void inject(HomeScreenFragment homeScreenFragment);

    void inject(ProfileScreenFragment profileScreenFragment);

    void inject(ScheduleScreenFragment scheduleScreenFragment);

    void inject(NewScheduleScreenFragment newScheduleScreenFragment);

    void inject(FinalScheduleFragment finalScheduleFragment);

    void inject(ManageAddressFragment manageAddressFragment);



    /*********Presenters***********/

    void inject(LoginPresenter loginPresenter);

    void inject(SignUpPresenter signUpPresenter);

    void inject(HomePresenter homePresenter);

    void inject(ProfileScreenPresenter profileScreenPresenter);

    void inject(ScheduleScreenPresenter scheduleScreenPresenter);


    @Subcomponent.Builder
    interface Builder {
        Builder activityModule(ActivityModule activityModule);
        ActivityComponent build();
    }
}
