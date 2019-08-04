package com.zjy.frame.splash;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bravin.btoast.BToast;
import com.zjy.frame.R;
import com.zjy.frame.base.BaseActivity;
import com.zjy.frame.dashboard.DashboardActivity;
import com.zjy.frame.login.LoginActivity;
import com.zjy.frame.utils.Constants;
import com.zjy.frame.utils.PreferenceUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {

    @BindView(R.id.ll_user_login)
    public LinearLayout mLinearLayout;

    @BindView(R.id.tv_login)
    public TextView mTextViewLogin;

    @BindView(R.id.btn_register)
    public Button mBtnRegister;


    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_spash;
    }


    @Override
    public Context getContext() {
        return getApplicationContext();
    }


    @Override
    public void initData() {
        super.initData();

        String account = PreferenceUtil.getString(getApplicationContext(), Constants.KEY_ACCOUNT, "");
        if (TextUtils.isEmpty(account)) {
            mLinearLayout.setVisibility(View.VISIBLE);
        } else {//auto login
            presenter.login(account, "");
        }
    }


    @OnClick({R.id.tv_login, R.id.btn_register})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:

                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_register:

                break;

            default:
                break;
        }

    }


    @Override
    public void loginSuccess() {
        BToast.success(getApplicationContext()).text("登陆成功").show();
        presenter.checkDeviceBound();


    }

    @Override
    public void loginFailed(String errorDetail) {
        BToast.success(getApplicationContext()).text("登录失败").show();
    }

    @Override
    public void getDeviceListSuccess() {
        BToast.success(getApplicationContext()).text("获取列表成功").show();
        presenter.getAndSaveToken();
    }

    @Override
    public void getDeviceListFailed() {
        BToast.success(getApplicationContext()).text("获取列表失败").show();
        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void getTokenSuccess() {
        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void getTokenFailed() {
        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(intent);
    }
}
