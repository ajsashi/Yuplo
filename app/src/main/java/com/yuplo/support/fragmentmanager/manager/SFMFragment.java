package com.yuplo.support.fragmentmanager.manager;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yuplo.support.fragmentmanager.FragmentChannel;


public abstract class SFMFragment<F extends FragmentChannel> extends Fragment implements IFragment {

    protected F fragmenChannel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null && context instanceof FragmentChannel) {
            fragmenChannel = (F) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getParentFragment() != null && getParentFragment() instanceof FragmentChannel) {
            fragmenChannel = (F) getParentFragment();
        }
    }
}
