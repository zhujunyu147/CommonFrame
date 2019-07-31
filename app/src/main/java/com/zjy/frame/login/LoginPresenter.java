package com.zjy.frame.login;

import com.zjy.frame.base.BaseObserver;
import com.zjy.frame.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView baseView) {
        super(baseView);
    }


    public void login(String name, String pwd) {

        addDisposable(apiServer.LoginByRx(name, pwd), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.onLoginSucc();

            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }
}
