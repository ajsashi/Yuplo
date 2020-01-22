package com.yuplo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.yuplo.R;
import com.yuplo.base.BaseActivity;
import com.yuplo.presenter.signIn.ISignUpPresenter;
import com.yuplo.presenter.signIn.SignUpPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements ISignUpPresenter {

    @Inject
    SignUpPresenter presenter;

    @BindView(R.id.edt_fname)
    EditText fname;
    @BindView(R.id.edt_lname)
    EditText lname;
    @BindView(R.id.edt_email)
    EditText email;
    @BindView(R.id.edt_password)
    EditText password;
    @BindView(R.id.edt_con_password)
    EditText con_password;
    @BindView(R.id.progressbar)
    ConstraintLayout progressBar;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector().inject(this);
        presenter.init(this, this);
    }

    @OnClick(R.id.btn_signUp)
    public void signUp() {
//        String sName = fname.getText().toString().trim();
//        String sLName = lname.getText().toString().trim();
//        String sEmail = email.getText().toString().trim();
//        String sPassword = password.getText().toString().trim();
//        String sConPassword = con_password.getText().toString().trim();
        String sName = "Jenni";
        String sLName = "Sashi";
        String sEmail = "ajsashiapp13@gmail.com";
        String sPassword = "Test@123";
        String sConPassword = "Test@123";
        presenter.validation(sName, sLName, sEmail, sPassword, sConPassword);
    }

    @OnClick(R.id.txt_login)
    public void login() {
        finish();
    }

    @Override
    public void navigate() {
        startActivity(new Intent(this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
