package com.zjy.frame.detail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.zjy.frame.R;
import com.zjy.frame.base.BaseActivity;
import com.zjy.frame.base.BasePresenter;
import com.zjy.frame.utils.Constants;
import com.zjy.frame.widget.CustomViewPager;

import butterknife.BindView;

public class DetailActivity extends BaseActivity {

    public String deviceId;

    @BindView(R.id.pager)
    public CustomViewPager mCustomViewPager;
    @BindView(R.id.iv_share)
    public ImageView mImageViewShare;
    @BindView(R.id.iv_info)
    public ImageView mImageViewInfo;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public Context getContext() {
        return getContext();
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
    public void initData() {
        super.initData();
        deviceId = getIntent().getStringExtra(Constants.INTENT_DEVICE_ID);

    }
}
