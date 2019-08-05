package com.zjy.frame.dashboard;

import android.content.Context;
import android.text.TextUtils;

import com.zjy.frame.R;
import com.zjy.frame.base.BaseObserver;
import com.zjy.frame.base.BasePresenter;
import com.zjy.frame.device.IAQDevice;
import com.zjy.frame.device.IAQSimpleFactory;
import com.zjy.frame.utils.CommonUtils;
import com.zjy.frame.utils.Constants;
import com.zjy.frame.utils.DiagnoseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.zjy.frame.utils.DiagnoseUtils.isCO2LevelNormal;
import static com.zjy.frame.utils.DiagnoseUtils.isHCHOLevelNormal;
import static com.zjy.frame.utils.DiagnoseUtils.isIQLevelnormal;
import static com.zjy.frame.utils.DiagnoseUtils.isTVOCLevelnormal;

public class DashboardPresenter extends BasePresenter<DashboardView> {

    private int totalDeviveCount = 0;
    private int deviceCount = 0;
    private ArrayList<IAQDevice> mIaqDevices;

    private Map<Integer, List<ChildBean>> childMap;
    private List<GroupBean> mDeviceInfoList;

    public DashboardPresenter(DashboardView baseView) {
        super(baseView);
        childMap = new HashMap<>();
        mDeviceInfoList = new ArrayList<>();
    }


    public void getAllDeviceData(ArrayList<IAQDevice> iaqDevices) {
        this.mIaqDevices = iaqDevices;
        totalDeviveCount = mIaqDevices.size();

        for (IAQDevice iaqDevice : mIaqDevices) {
            setEveryDeviceLanguage(iaqDevice);
        }
    }


    private void setEveryDeviceLanguage(final IAQDevice iaqDevice) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.REQUEST_TYPE, Constants.TYPE_LANGUAGE);
        params.put(Constants.KEY_DEVICE_ID, iaqDevice.getDeviceId());
        params.put(Constants.KEY_DATA, CommonUtils.getLanguage(baseView.getContext()));
        JSONObject bodyAsJson = new JSONObject(params);
        String JsonStr = bodyAsJson.toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), JsonStr);

        addDisposable(apiServer.SetLanguage(body), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                getEveryDeviceAlarmValue(iaqDevice);
            }

            @Override
            public void onError(String msg) {
                getEveryDeviceAlarmValue(iaqDevice);
            }
        });
    }


    private void getEveryDeviceAlarmValue(final IAQDevice iaqDevice) {
        Map<String, String> params = new HashMap<>();
        params.put(Constants.REQUEST_TYPE, Constants.TYPE_ALARM);
        params.put(Constants.KEY_DEVICE_ID, iaqDevice.getDeviceId());

        JSONObject bodyAsJson = new JSONObject(params);
        String JsonStr = bodyAsJson.toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), JsonStr);
        addDisposable(apiServer.GetAlarmValue(body), new BaseObserver<AlarmValueResponse>(baseView) {
            @Override
            public void onSuccess(AlarmValueResponse response) {

                String jsonString = response.getData();
                JSONObject jb = null;
                try {
                    jb = new JSONObject(jsonString);
                    String mPm25Red = jb.optString(Constants.KEY_PM25_RED);
                    String mPm25Yellow = jb.optString(Constants.KEY_PM25_YELLOW);
                    String mCo2Red = jb.optString(Constants.KEY_CO2_RED);
                    String mHchoRed = jb.optString(Constants.KEY_HCHO_RED);
                    String mTvocRed = jb.optString(Constants.KEY_TVOC_RED);
                    String mIqRed = jb.optString(Constants.KEY_IQ_RED);

                    iaqDevice.setmPm25Red(mPm25Red);
                    iaqDevice.setmPm25Yellow(mPm25Yellow);
                    iaqDevice.setmTvocRed(mTvocRed);
                    iaqDevice.setmHchoRed(mHchoRed);
                    iaqDevice.setmCo2Red(mCo2Red);
                    iaqDevice.setmIqRed(mIqRed);
                    getEveryDeviceLocation(iaqDevice);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String msg) {
                getEveryDeviceLocation(iaqDevice);
            }
        });
    }


    private void getEveryDeviceLocation(final IAQDevice iaqDevice) {
        Map<String, String> params = new HashMap<>();
        params.put(Constants.REQUEST_TYPE, Constants.TYPE_GET_LOCATION);
        params.put(Constants.KEY_DEVICE_ID, iaqDevice.getDeviceId());

        JSONObject bodyAsJson = new JSONObject(params);
        String JsonStr = bodyAsJson.toString();
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), JsonStr);
        addDisposable(apiServer.GetLocation(body), new BaseObserver<GetLocationResponse>(baseView) {
            @Override
            public void onSuccess(GetLocationResponse response) {
                iaqDevice.setCity(response.getName());
                changeCount();
            }

            @Override
            public void onError(String msg) {
                changeCount();
            }
        });

    }


    private synchronized void changeCount() {
        deviceCount++;
        if (deviceCount == totalDeviveCount) {
            baseView.getDeviceDataSuccess(mIaqDevices);
        }
    }


    public void setData(ArrayList<IAQDevice> iaqDevices) {

        ArrayList<GroupBean> homeList = new ArrayList<>();
        for (IAQDevice iaqDevice : iaqDevices) {
            GroupBean groupBean = new GroupBean();
            String homeName = iaqDevice.getHomeName();
            String location = iaqDevice.getCity();
            groupBean.setHome(homeName);
            groupBean.setLocation(location);
            homeList.add(groupBean);
        }

        ArrayList<String> groupList = new ArrayList<>();
        ArrayList<String> groupListLocation = new ArrayList<>();


        for (GroupBean deviceInformation : homeList) {
            if (groupList.contains(deviceInformation.getHome()) && groupListLocation.contains(deviceInformation.getLoacation())) {
            } else {
                groupList.add(deviceInformation.getHome());
                groupListLocation.add(deviceInformation.getLoacation());
                mDeviceInfoList.add(deviceInformation);
            }
        }

        for (int i = 0; i < mDeviceInfoList.size(); i++) {
            setChildItemData(i, mDeviceInfoList.get(i).getHome(), mDeviceInfoList.get(i).getLoacation());
        }

        baseView.setDataComplete(mDeviceInfoList, childMap);

    }


    private void setChildItemData(int index, String homeName, String location) {
        List<IAQDevice> iaqDeviceList = IAQSimpleFactory.getInstance().getIAQDevice(homeName, location);
        if (iaqDeviceList == null)
            return;
        List<ChildBean> childItems = new ArrayList<>();
        for (IAQDevice iaqDevice : iaqDeviceList) {
            ChildBean childItem = new ChildBean(R.mipmap.circle1, R.mipmap.icon_tep, R.mipmap.icon_humidity);
            childItem.setRoom(iaqDevice.getDeviceName());
            childItem.setRoom(iaqDevice.getDeviceName());
            int onlineStatus = iaqDevice.getOnlineStatus();
            if (onlineStatus == Constants.DEVICE_ONLINE) {
                String temperature = iaqDevice.getTemperature();
                if (temperature == null || temperature.length() == 0) {
                    temperature = baseView.getContext().getString(R.string.unknown);
                }
                childItem.setTemperature(temperature);

                String room = iaqDevice.getDeviceName();
                String temperatureUnit = iaqDevice.getTemperatureUnit();
                Map<String, String> tempMap = new HashMap<>();
                tempMap.put(room, temperatureUnit);
                childItem.setTemperatureUnit(tempMap);

                String humidity = iaqDevice.getHumidity();
                if (humidity == null || humidity.length() == 0) {
                    humidity = baseView.getContext().getString(R.string.unknown);
                }
                childItem.setHumidity(humidity);

                String tvoc = iaqDevice.getTvoc();
                String hcho = iaqDevice.getHcho();
                String co2 = iaqDevice.getCo2();
                String iq = iaqDevice.getIq();

                String pm25 = iaqDevice.getPm25();
                String pmStatus = baseView.getContext().getString(R.string.getting_data);
                if (pm25 == null || pm25.length() == 0) {
                    if (CommonUtils.isNetworkAvailable(baseView.getContext())) {
                        pmStatus = baseView.getContext().getString(R.string.getting_data);
                    } else {
                        pmStatus = baseView.getContext().getString(R.string.get_data_fail);
                    }
                    pm25 = "";
                } else {
                    try {
                        String pm25Red = (TextUtils.isEmpty(iaqDevice.getmPm25Red()) ? "200" : iaqDevice.getmPm25Red());
                        String pm25Yellow = (TextUtils.isEmpty(iaqDevice.getmPm25Yellow()) ? "80" : iaqDevice.getmPm25Yellow());

                        pmStatus = getPMStatus(baseView.getContext(), Integer.parseInt(pm25), Float.parseFloat(pm25Red), Float.parseFloat(pm25Yellow));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                childItem.setPm25(pm25);
                childItem.setPmStatus(pmStatus);
                if (pm25.length() == 0) {
                    childItem.setPmLevel(Constants.PM_LEVEL_UNKNOWN);
                } else {
                    try {


                        String pm25Red = (TextUtils.isEmpty(iaqDevice.getmPm25Red()) ? "200" : iaqDevice.getmPm25Red());
                        String pm25Yellow = (TextUtils.isEmpty(iaqDevice.getmPm25Yellow()) ? "80" : iaqDevice.getmPm25Yellow());

                        int pm25Level = DiagnoseUtils.getPMLevel(Integer.parseInt(pm25), Float.parseFloat(pm25Red), Float.parseFloat(pm25Yellow));


                        if (pm25Level == Constants.PM_LEVEL_2) {
                            childItem.setPmLevel(Constants.PM_LEVEL_2);
                        } else if (pm25Level == Constants.PM_LEVEL_4) {
                            childItem.setPmLevel(Constants.PM_LEVEL_4);
                        } else if (pm25Level == Constants.PM_LEVEL_5) {
                            childItem.setPmLevel(Constants.PM_LEVEL_5);
                        } else {
                            childItem.setPmLevel(Constants.PM_LEVEL_UNKNOWN);
                        }

                        if (!isHCHOLevelNormal(hcho, iaqDevice)
                                || !isCO2LevelNormal(co2, iaqDevice)
                                || !isTVOCLevelnormal(tvoc, iaqDevice)
                                || !isIQLevelnormal(iq, iaqDevice)) {

                            childItem.setPmLevel(Constants.PM_LEVEL_5);
                            pmStatus = baseView.getContext().getString(R.string.status_not_good);
                        }
                        childItem.setPmStatus(pmStatus);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                childItem.setTemperature(baseView.getContext().getString(R.string.unknown));
                childItem.setHumidity(baseView.getContext().getString(R.string.unknown));
                childItem.setPm25("");
                childItem.setPmStatus(baseView.getContext().getString(R.string.iaq_disconnect));
                childItem.setPmLevel(Constants.PM_LEVEL_UNKNOWN);
            }
            childItem.setSerialNum(iaqDevice.getDeviceId());
            childItem.setDeviceId(iaqDevice.getDeviceId());
            childItems.add(childItem);
        }
        childMap.put(index, childItems);

    }


    public static String getPMStatus(Context context, int pmValue, float criticalRedValue, float criticalYeValue) {

        if (pmValue >= 0 && pmValue < criticalYeValue) {
            return context.getString(R.string.status_good);
        } else if (pmValue >= criticalYeValue && pmValue < criticalRedValue) {
            return context.getString(R.string.status_ok);
        } else if (pmValue >= criticalRedValue) {
            return context.getString(R.string.status_not_good);
        }
        return context.getString(R.string.unknown);
    }


}
