package com.zjy.frame.detail.view;

import android.os.Bundle;

import com.zjy.frame.base.BaseFragment;
import com.zjy.frame.detail.presenter.HistoryChartPresenter;
import com.zjy.frame.utils.Constants;

public class HistoryChartFragment extends BaseFragment<HistoryChartPresenter> {


    public static HistoryChartFragment getInstance(String deviceId) {
        Bundle args = new Bundle();
        args.putString(Constants.KEY_DEVICE_ID, deviceId);
        HistoryChartFragment historyChartFragment = new HistoryChartFragment();
        historyChartFragment.setArguments(args);
        return historyChartFragment;
    }

    @Override
    protected HistoryChartPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
