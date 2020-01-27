package com.yuplo.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yuplo.R;
import com.yuplo.base.BaseFragment;
import com.yuplo.presenter.homeScreen.HomeScreenAdapter;
import com.yuplo.support.fragmentmanager.manager.IFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeScreenFragment extends BaseFragment implements IFragment {


    @Inject
    HomeScreenAdapter adapter;

    @BindView((R.id.recyclerView))
    RecyclerView recyclerView;

    public static IFragment newInstance() {
        return new HomeScreenFragment();
    }

    @Override
    public String getFragmentName() {
        return "HomeScreenFragment";
    }

    @Override
    public void setTitle() {
        yuploFragmentChannel.setToolbarTitle("Home");    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_screen;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter.init(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
