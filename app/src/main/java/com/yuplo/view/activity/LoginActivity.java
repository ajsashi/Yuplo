package com.yuplo.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.yuplo.R;
import com.yuplo.base.BaseActivity;
import com.yuplo.presenter.login.ILoginPresenter;
import com.yuplo.presenter.login.LoginPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginPresenter {

    @Inject
    LoginPresenter presenter;
    @BindView(R.id.edt_email)
    EditText email;
    @BindView(R.id.txt_signUp)
    TextView txtSignUp;
    @BindView(R.id.edt_password)
    EditText password;
    @BindView(R.id.progressbar)
    ConstraintLayout progressBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getFrameLayoutContainerId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        startActivity(new Intent(this,HomeActivity.class));
        injector().inject(this);
        presenter.init(this, this);
        if(presenter.isLoggedIn()){
            startActivity(new Intent(this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            return;
        }
        SpannableString txtSpannable = new SpannableString(getResources().getString(R.string.sign_up));
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        txtSpannable.setSpan(boldSpan, 23, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtSignUp.setText(txtSpannable, TextView.BufferType.SPANNABLE);


    }


    @OnClick(R.id.login_btn)
    public void login() {
//        presenter.validation(email.getText().toString().trim(), password.getText().toString().trim());
        presenter.validation("praveenkumar.aitech@gmail.com", "12345678");

    }

    @OnClick(R.id.txt_signUp)
    public void signUp() {
        startActivity(new Intent(this, SignUpActivity.class));
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
        if (progressBar.getVisibility()==View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
