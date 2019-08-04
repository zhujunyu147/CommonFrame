package com.zjy.frame.login;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.zjy.frame.R;
import com.zjy.frame.base.BaseActivity;
import com.zjy.frame.utils.Constants;
import com.zjy.frame.utils.PreferenceUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {


    @BindView(R.id.phone_number)
    public EditText mPhoneNumber;
    @BindView(R.id.password)
    public EditText mPwd;
    @BindView(R.id.btn_login)
    public Button mLogin;
    @BindView(R.id.register)
    public TextView mRegister;
    @BindView(R.id.forget_pwd)
    public TextView mTvForgotPwd;
    @BindView(R.id.ch_wifi_pwd)
    public CheckBox mCheckBox;
    @BindView(R.id.sp_country)
    public Spinner mSpinner;
    @BindView(R.id.et_select_country)
    public EditText mEditTextCountrySelect;

    private String CountryCode;


    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;

    }

    @Override
    public void initView() {
        super.initView();
    }

    @OnClick(R.id.btn_login)
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                presenter.login("15366189920", "12345678aA", "+86");
                break;
        }

    }


    @Override
    public void onLoginSucc() {
        Log.e("TTTTTTT", "onLoginSucc");
        PreferenceUtil.commitString(getApplicationContext(), Constants.KEY_ACCOUNT,"15366189920");

    }

    @Override
    public void onLoginFailed(String msg) {
        Log.e("TTTTTTT", msg);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
