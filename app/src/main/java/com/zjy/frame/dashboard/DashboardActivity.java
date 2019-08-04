package com.zjy.frame.dashboard;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bravin.btoast.BToast;
import com.nineoldandroids.view.ViewHelper;
import com.zjy.frame.R;
import com.zjy.frame.base.BaseActivity;
import com.zjy.frame.device.IAQDevice;
import com.zjy.frame.device.IAQSimpleFactory;
import com.zjy.frame.eroll.AddDeviceActivity;
import com.zjy.frame.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class DashboardActivity extends BaseActivity<DashboardPresenter> implements DashboardView {

    @BindView(R.id.device_list)
    public ExpandableListView expandableListView;

    @BindView(R.id.main_layout)
    public DrawerLayout mDrawerLayout;

    private ArrayList<GroupBean> mDeviceInfoList;
    private Map<Integer, List<ChildBean>> childMap;
    private DashboardAdapter mAdapter;

    @Override
    protected DashboardPresenter createPresenter() {
        return new DashboardPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void initView() {
        super.initView();
        setListener();
        mDeviceInfoList = new ArrayList<>();
        childMap = new HashMap<>();
        mAdapter = new DashboardAdapter(getApplicationContext(), mDeviceInfoList, childMap);
        expandableListView.setAdapter(mAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    @Override
    protected void initTitle(TextView title) {
        super.initTitle(title);
        title.setText(R.string.dashboard_title);
    }

    @Override
    protected void initLeftIcon(ImageView left) {
        super.initLeftIcon(left);
        left.setImageResource(R.mipmap.icon_menu);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeftMenu();
            }
        });
    }

    public void openLeftMenu() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.LEFT);
    }

    public void setListener() {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                if ("START".equals(drawerView.getTag())) {

                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                    ViewHelper.setTranslationX(mContent, mMenu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                } else {
                    ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth() * slideOffset);
                    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                    ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                }

            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }
        });
    }


    @Override
    public void initData() {
        super.initData();
        getData();

    }
    private void getData(){
        if (!CommonUtils.isNetworkAvailable(getApplicationContext())) {
            dismissLoadingDialog();
            BToast.success(getApplicationContext()).text(getString(R.string.no_network)).show();
            return;
        }

        // refine
        ArrayList<IAQDevice> iaqDevices = IAQSimpleFactory.getInstance().getIAQDevices();
        if (iaqDevices.isEmpty()) {
            dismissLoadingDialog();
            startActivity(new Intent(this, AddDeviceActivity.class));
            return;
        }

        presenter.getAllDeviceData(iaqDevices);

    }

    @Override
    public void getDeviceDataSuccess(ArrayList<IAQDevice> iaqDevices) {
        Log.e("getDeviceDataSuccess","getDeviceDataSuccess");
        presenter.setData(iaqDevices);

    }

    @Override
    public void setDataComplete(List<GroupBean> mDeviceInfoList, Map<Integer, List<ChildBean>> childMap) {
        Log.e("setDataComplete","setDataComplete");
        mAdapter.setGroupTitle(mDeviceInfoList);
        mAdapter.setChildMap(childMap);
        mAdapter.notifyDataSetChanged();
        int groupCount = mDeviceInfoList.size();
        for (int i = 0; i < groupCount; i++) {
            expandableListView.expandGroup(i);
        }
    }
}
