package com.yuplo.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.yuplo.R;
import com.yuplo.base.BaseActivity;
import com.yuplo.support.Constants;
import com.yuplo.support.MyPreferenceManager;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    @Inject
    MyPreferenceManager preferenceManager;
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected int getFrameLayoutContainerId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector().inject(this);

        Thread background = new Thread() {
            public void run() {
                try {
                    int millis = 3000;
                    sleep(millis);
                    if (preferenceManager.getBoolsData(Constants.getLoggedIn())){
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                }
                    else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        background.start();
    }

}
