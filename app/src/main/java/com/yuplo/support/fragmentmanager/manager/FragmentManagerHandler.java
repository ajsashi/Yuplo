package com.yuplo.support.fragmentmanager.manager;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.yuplo.presenter.FragmentPresenter;

import org.parceler.Parcels;


public class FragmentManagerHandler {

    private static final String KEY_TAGS = "key_tags";
    private FragmentManager fragmentManager;
    private int fragmentContainerId;
    private FragmentTagStack fragmentTagStack;

    public FragmentManagerHandler(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        fragmentTagStack = new FragmentTagStack();
    }

    public void setFragmentContainerId(int fragmentContainerId) {
        this.fragmentContainerId = fragmentContainerId;
    }

    public void setFragmentContainerId(FrameLayout flContainer) {
        setFragmentContainerId(flContainer.getId());
    }
}
