package com.yuplo.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yuplo.R;
import com.yuplo.base.BaseFragment;
import com.yuplo.presenter.schedule.ScheduleAdapter;
import com.yuplo.support.fragmentmanager.manager.IFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class ScheduleScreenFragment extends BaseFragment implements IFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    ScheduleAdapter adapter;

    public static IFragment newInstance() {
        return new ScheduleScreenFragment();
    }

    @Override
    protected int getLayoutId() {
    return R.layout.fragment_schedule_screen;
    }

    @Override
    public String getFragmentName() {
        return "ScheduleScreenFragment";
    }

    @Override
    public void setTitle() {
        yuploFragmentChannel.setToolbarTitle("Schedule");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        yuploFragmentChannel.setToolbarTitle("Schedule");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
