package com.zjy.frame.login;

import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.zjy.frame.R;
import com.zjy.frame.base.BaseActivity;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

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
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //示例代码，示例接口
                presenter.login(mEmailView.getText().toString(), mPasswordView.getText().toString());
////                presenter.upload("/storage/emulated/0/DCIM/Camera/IMG_20180710_152800_BURST19.jpg");


            }
        });
    }

    @Override
    public void onLoginSucc() {
        Log.e("TTTTTTT", "onLoginSucc");

    }
}
