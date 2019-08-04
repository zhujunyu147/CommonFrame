package com.zjy.frame.splash;

import com.zjy.frame.base.BaseObserver;
import com.zjy.frame.base.BasePresenter;
import com.zjy.frame.device.IAQDevice;
import com.zjy.frame.device.IAQSimpleFactory;
import com.zjy.frame.utils.CommonUtils;
import com.zjy.frame.utils.Constants;
import com.zjy.frame.utils.PreferenceUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SplashPresenter extends BasePresenter<SplashView> {

    private static final String PHONE_UUID = UUID.randomUUID().toString();

    public SplashPresenter(SplashView baseView) {
        super(baseView);
    }


    public void login(String name, String pwd) {

        Map<String, String> params = new HashMap<>();
        params.put("type", Constants.REQUEST_TYPE_LOGIN);
        params.put("password", "12345678aA");
        params.put("phoneNumber", "+8615366189920");
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
                baseView.loginSuccess();
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
                baseView.loginFailed(msg);
            }
        });
    }


    public void checkDeviceBound() {
        addDisposable(apiServer.GetBindDevice(), new BaseObserver<DeviceListResonse>(baseView) {
            @Override
            public void onSuccess(DeviceListResonse deviceListResonse) {
                List<DeviceListResonse.DevicesBean> devicesBeans = deviceListResonse.getDevices();
                for (int i = 0; i < devicesBeans.size(); i++) {
                    DeviceListResonse.DevicesBean devicesBean = devicesBeans.get(i);
                    IAQDevice iaqDevice = IAQSimpleFactory.getInstance().getIAQDevice(devicesBean.getDeviceSerial());
                    if (iaqDevice == null) {
                        iaqDevice = IAQSimpleFactory.getInstance().createIAQDevice(devicesBean.getDeviceSerial());
                    }

                    boolean onlineStatus = devicesBean.isOnline();
                    int status = Constants.DEVICE_OFFLINE;
                    if (onlineStatus) {
                        status = Constants.DEVICE_ONLINE;
                    }

                    iaqDevice.setOnlineStatus(status);
                    iaqDevice.setHomeName(devicesBean.getDeviceInfo().getHome());
                    iaqDevice.setDeviceName(devicesBean.getDeviceInfo().getRoom());
                }
                baseView.getDeviceListSuccess();
            }

            @Override
            public void onError(String msg) {
                baseView.getDeviceListFailed();
            }
        });
    }

    public void getAndSaveToken() {

        addDisposable(apiServer.GetWeatherToken(), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.getTokenSuccess();
            }

            @Override
            public void onError(String msg) {
                baseView.getTokenFailed();
            }
        });

    }


}
