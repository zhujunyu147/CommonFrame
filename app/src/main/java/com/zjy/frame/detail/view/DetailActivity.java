package com.zjy.frame.detail.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjy.frame.R;
import com.zjy.frame.base.BaseActivity;
import com.zjy.frame.base.BaseFragment;
import com.zjy.frame.base.BasePresenter;
import com.zjy.frame.detail.presenter.DetailPresenter;
import com.zjy.frame.permission.IAQPermission;
import com.zjy.frame.permission.Permission;
import com.zjy.frame.permission.PermissionListener;
import com.zjy.frame.utils.Constants;
import com.zjy.frame.widget.CustomViewPager;
import com.zjy.frame.widget.DirectionalViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity<DetailPresenter> implements ViewPager.OnPageChangeListener, CustomViewPager.MyDirectListener, DetailView, PermissionListener {

    public String deviceId;
    private boolean isStoragePermission = false;
    private IAQPermission mIaqPermission;

    @BindView(R.id.pager)
    public CustomViewPager mCustomViewPager;
    @BindView(R.id.iv_share)
    public ImageView mImageViewShare;
    @BindView(R.id.iv_info)
    public ImageView mImageViewInfo;
    private RealTimeDataFragment mRealTimeDataFragment;
    private HistoryChartFragment mHistoryChartFragment;
    private List<BaseFragment> mListFragments;
    private int pageIndex = 0;//用户判断当前在viewpager的哪个页面，分别调用截图方法


    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    protected void initLeftIcon(ImageView left) {
        super.initLeftIcon(left);
        left.setImageResource(R.mipmap.ic_arrow_back_white);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initTitle(TextView title) {
        super.initTitle(title);
        title.setVisibility(View.GONE);
    }

    @Override
    public void initView() {
        super.initView();
        mListFragments = new ArrayList<BaseFragment>();
        mListFragments.clear();

        mRealTimeDataFragment = RealTimeDataFragment.getInstance(deviceId);
        mListFragments.add(mRealTimeDataFragment);
        mHistoryChartFragment = HistoryChartFragment.getInstance(deviceId);
        mListFragments.add(mHistoryChartFragment);
        mCustomViewPager.setAdapter(new DetailFragmentAdapter(getSupportFragmentManager(), mListFragments));
        mCustomViewPager.setOrientation(DirectionalViewPager.VERTICAL);
        mCustomViewPager.setCurrentItem(0);
        mCustomViewPager.setOffscreenPageLimit(2);
        mCustomViewPager.setOnPageChangeListener(this);
        mCustomViewPager.setMyDirectListener(this);
        mIaqPermission = new IAQPermission(this);

    }

    @Override
    public void initData() {
        super.initData();
        deviceId = getIntent().getStringExtra(Constants.INTENT_DEVICE_ID);

    }

    @OnClick({R.id.iv_share, R.id.iv_info})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_share:
                checkPermission();
                if(!isStoragePermission){
                    return;
                }
                presenter.getCommonShot();
                break;
            case R.id.iv_info:

                break;
        }
    }

    private void checkPermission() {
        mIaqPermission.checkAndRequestPermission(Permission.PermissionCodes.STORAGE_REQUEST_CODE, this);
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pageIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void getsliderLister(int direct) {

    }

    @Override
    public void shotSuccess(Uri uri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.share_image_to)));
    }

    @Override
    public void shotFailed() {

    }

    @Override
    public void onPermissionGranted(int permissionCode) {
        switch (permissionCode) {
            case Permission.PermissionCodes.STORAGE_REQUEST_CODE:
                isStoragePermission = true;
                break;
        }
    }

    @Override
    public void onPermissionNotGranted(String[] permission, int permissionCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(permission, permissionCode);
        }
    }

    @Override
    public void onPermissionDenied(int permissionCode) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case IAQPermission.PermissionCodes.STORAGE_REQUEST_CODE:

                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                    }
                    isStoragePermission = true;
                    presenter.getCommonShot();
                } else {
                    isStoragePermission = false;
                }
                break;

        }
    }
}
