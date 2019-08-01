package com.zjy.frame.login;

import com.zjy.frame.base.BaseObserver;
import com.zjy.frame.base.BasePresenter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView baseView) {
        super(baseView);
    }


    public void login(String name, String pwd) {

//        addDisposable(apiServer.LoginByRx(name, pwd), new BaseObserver(baseView) {
//            @Override
//            public void onSuccess(Object o) {
//                baseView.onLoginSucc();
//
//            }
//
//            @Override
//            public void onError(String msg) {
//                baseView.showError(msg);
//            }
//        });

        Map<String, String> params = new HashMap<>();
        params.put("type", "LoginUser");
        params.put("password", "12345678aA");
        params.put("phoneNumber", "+8615366189920");
        params.put("phoneUuid", "04822fa9-e011-4beb-b0c3-028aac9abc9f");
        params.put("phoneType", "android");
        params.put("language", "zh-CN");
        JSONObject bodyAsJson = new JSONObject(params);
        String JsonStr = bodyAsJson.toString();

        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), JsonStr);

        addDisposable(apiServer.Login(body), new BaseObserver(baseView) {
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
