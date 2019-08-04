package com.zjy.frame.dashboard;

import com.zjy.frame.base.BaseView;
import com.zjy.frame.device.IAQDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DashboardView extends BaseView {

    void getDeviceDataSuccess(ArrayList<IAQDevice> iaqDevices);

    void setDataComplete(List<GroupBean> mDeviceInfoList, Map<Integer, List<ChildBean>> childMap);
}
