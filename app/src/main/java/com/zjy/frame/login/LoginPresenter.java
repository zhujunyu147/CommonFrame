package com.zjy.frame.login;

import com.zjy.frame.base.BaseObserver;
import com.zjy.frame.base.BasePresenter;
import com.zjy.frame.utils.CommonUtils;
import com.zjy.frame.utils.Constants;
import com.zjy.frame.utils.PreferenceUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginPresenter extends BasePresenter<LoginView> {

    private static final String PHONE_UUID = UUID.randomUUID().toString();

    public LoginPresenter(LoginView loginView) {
        super(loginView);
    }


    public void login(String phoneNumber, String pwd, String countryCode) {

        Map<String, String> params = new HashMap<>();
        params.put("type", Constants.REQUEST_TYPE_LOGIN);
        params.put("password", pwd);
        params.put("phoneNumber", countryCode + phoneNumber);
        params.put("phoneUuid", PHONE_UUID);
        params.put("phoneType", Constants.PHONE_TYPE_ANDROID);
        params.put("language", CommonUtils.getLanguage(baseView.getContext()));
        JSONObject bodyAsJson = new JSONObject(params);
        String JsonStr = bodyAsJson.toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), JsonStr);
        String cookie = PreferenceUtil.getString(baseView.getContext(), Constants.KEY_COOKIE, "");

        addDisposable(apiServer.Login(body), new BaseObserver(baseView) {

            @Override
            public void onSuccess(Object o) {
                JSONObject jsonObject = (JSONObject) o;

                baseView.onLoginSucc();

            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
                baseView.onLoginFailed(msg);
            }
        });


    }
}
