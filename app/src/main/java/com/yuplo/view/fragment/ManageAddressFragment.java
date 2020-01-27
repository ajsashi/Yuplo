package com.yuplo.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yuplo.R;
import com.yuplo.base.BaseFragment;
import com.yuplo.presenter.manageAddress.AddressAdapter;
import com.yuplo.support.DividerItemDecorator;
import com.yuplo.support.fragmentmanager.manager.IFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class ManageAddressFragment extends BaseFragment implements IFragment {

    @Inject
    AddressAdapter addressAdapter;

    @BindView(R.id.address_recyclerView)
    RecyclerView recyclerView;

    public static IFragment newInstance(){
        return new ManageAddressFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_manage_address_screen;
    }

    @Override
    public String getFragmentName() {
        return "ManageAddressFragment";
    }

    @Override
    public void setTitle() {
        yuploFragmentChannel.setToolbarTitle("Manage Address");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector().inject(this);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addressAdapter.init(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(addressAdapter);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
