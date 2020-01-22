package com.yuplo.support.di.app;


import com.yuplo.support.di.activity.ActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Bajic Dusko (www.bajicdusko.com) on 23/03/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, AndroidModule.class})
public interface ApplicationComponent {
    ActivityComponent.Builder activityComponentBuilder();
}
