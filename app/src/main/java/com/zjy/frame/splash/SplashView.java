package com.zjy.frame.splash;

import com.zjy.frame.base.BaseView;

public interface SplashView extends BaseView {

    void loginSuccess();

    void loginFailed(String errorDetail);

    void getDeviceListSuccess();

    void getDeviceListFailed();

    void getTokenSuccess();

    void getTokenFailed();

}
