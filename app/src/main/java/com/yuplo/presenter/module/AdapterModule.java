package com.yuplo.presenter.module;

import com.yuplo.presenter.homeScreen.HomeScreenAdapter;
import com.yuplo.presenter.manageAddress.AddressAdapter;
import com.yuplo.presenter.schedule.ScheduleAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {
    @Provides
    public HomeScreenAdapter homeScreenAdapter() {
        return new HomeScreenAdapter();
    }

    @Provides
    public ScheduleAdapter scheduleAdapter() {
        return new ScheduleAdapter();
    }

    @Provides
    public AddressAdapter addressAdapter() {
        return new AddressAdapter();
    }
}
