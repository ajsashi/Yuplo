package com.yuplo.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.yuplo.R;
import com.yuplo.base.BaseActivity;
import com.yuplo.base.BaseFragment;
import com.yuplo.presenter.HomePresenter;
import com.yuplo.support.fragmentmanager.YuploFragmentChannel;
import com.yuplo.support.fragmentmanager.manager.FragmentManagerHandler;
import com.yuplo.view.fragment.FinalScheduleFragment;
import com.yuplo.view.fragment.HomeScreenFragment;
import com.yuplo.view.fragment.ManageAddressFragment;
import com.yuplo.view.fragment.NewScheduleScreenFragment;
import com.yuplo.view.fragment.ProfileScreenFragment;
import com.yuplo.view.fragment.ScheduleScreenFragment;

import javax.inject.Inject;

import butterknife.BindView;

import static com.yuplo.support.Constants.FRAGMENTS;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, YuploFragmentChannel, BaseFragment.FragmentInteractionCallback {


    @Inject
    HomePresenter presenter;
    @Inject
    FragmentManagerHandler fragmentManagerHandler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.activity_home_fl_container)
    FrameLayout flContainer;
    private TextView actionBarTitle;
    ImageView addSchedule;
    private ActionBarDrawerToggle toggle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getFrameLayoutContainerId() {
        return R.id.activity_home_fl_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View view = getLayoutInflater().inflate(R.layout.action_bar, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        actionBarTitle = (TextView) view.findViewById(R.id.actionbar_title);


        setSupportActionBar(toolbar);
        getSupportActionBar().setCustomView(view, params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addSchedule = toolbar.findViewById(R.id.btn_add);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

        navigationView.setNavigationItemSelectedListener(this);
        injector().inject(this);
        presenter.init(this);
        fragmentManagerHandler.setFragmentContainerId(flContainer);
        showHome();
        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                showNewSchedule();
            }
        });

        FRAGMENTS.add("ProfileScreenFragment");
        FRAGMENTS.add("ScheduleScreenFragment");
        FRAGMENTS.add("NewScheduleScreenFragment");
        FRAGMENTS.add("ManageAddressFragment");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_profile:
                showProfile();
                break;
            case R.id.nav_schedule:
                showSchedule();
                break;

            case R.id.nav_manage_address:
                showManageAddress();
                break;
            case R.id.nav_logout:
                presenter.logout();
                break;
        }
        drawerLayout.closeDrawers();
        return true;
    }


    @Override
    public void showHome() {
        simpleFragmentManager.popUpAll();
        simpleFragmentManager.addFragment(HomeScreenFragment.newInstance());
        setToolbarTitle("Home");

    }

    private void changeNavigationIcon() {
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.back_button);
    }

    @Override
    public void showProfile() {
        simpleFragmentManager.replaceFragment(ProfileScreenFragment.newInstance());
        changeNavigationIcon();

    }

    @Override
    public void showSchedule() {
        simpleFragmentManager.replaceFragment(ScheduleScreenFragment.newInstance());
        changeNavigationIcon();
    }

    @Override
    public void showNewSchedule() {
        simpleFragmentManager.addFragment(NewScheduleScreenFragment.newInstance());
    }

    @Override
    public void showFinalSchedule() {
        simpleFragmentManager.addFragment(FinalScheduleFragment.newInstance());
    }

    @Override
    public void showManageAddress() {
        simpleFragmentManager.addFragment(ManageAddressFragment.newInstance());
        changeNavigationIcon();
    }

    @Override
    public void setToolbarTitle(String title) {
        if (title.equals("Schedule")) {
            addSchedule.setVisibility(View.VISIBLE);
        } else {
            addSchedule.setVisibility(View.GONE);
        }
        actionBarTitle.setText(title);
    }

    @Override
    public void onFragmentInteractionCallback(Bundle bundle) {
//Do Nothing
    }

    @Override
    public void onBackPressed() {

        boolean isNotRoot = simpleFragmentManager.onBackPressed();
        int stack = simpleFragmentManager.getBackStackEntry();

        Log.d("Stack", String.valueOf(stack));


        if (!isNotRoot) {
            finish();
            return;
        } else if (isNotRoot && stack == 1) {
            toggle.setDrawerIndicatorEnabled(true);
        }

        for (String backPressed : FRAGMENTS) {
            if (backPressed.equals(simpleFragmentManager.getFragmentName())) {
                simpleFragmentManager.popUpAll();
                showHome();
                return;
            }
        }
    }

}
