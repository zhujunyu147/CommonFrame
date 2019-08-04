package com.zjy.frame.login;

import com.zjy.frame.base.BaseView;

public interface LoginView extends BaseView {
    void onLoginSucc();

    void onLoginFailed(String msg);

}
