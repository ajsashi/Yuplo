package com.yuplo.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yuplo.R;
import com.yuplo.base.BaseFragment;
import com.yuplo.support.fragmentmanager.manager.IFragment;

public class FinalScheduleFragment extends BaseFragment implements IFragment {
    public static IFragment newInstance() {
        return new FinalScheduleFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_final_screen;
    }

    @Override
    public String getFragmentName() {
        return "FinalScheduleFragment";
    }

    @Override
    public void setTitle() {
yuploFragmentChannel.setToolbarTitle("New Schedule");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector().inject(this);
    }
}
