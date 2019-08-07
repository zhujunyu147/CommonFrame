package com.zjy.frame.detail.view;

import android.os.Bundle;

import com.zjy.frame.base.BaseFragment;
import com.zjy.frame.detail.presenter.RealTimeDataPresenter;
import com.zjy.frame.utils.Constants;

public class RealTimeDataFragment extends BaseFragment<RealTimeDataPresenter> {


    public static RealTimeDataFragment getInstance(String deviceId) {
        Bundle args = new Bundle();
        args.putString(Constants.KEY_DEVICE_ID, deviceId);
        RealTimeDataFragment realTimeDataFragment = new RealTimeDataFragment();
        realTimeDataFragment.setArguments(args);
        return realTimeDataFragment;
    }

    @Override
    protected RealTimeDataPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
