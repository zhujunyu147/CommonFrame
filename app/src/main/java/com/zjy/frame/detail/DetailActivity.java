package com.zjy.frame.detail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.zjy.frame.R;
import com.zjy.frame.base.BaseActivity;
import com.zjy.frame.base.BasePresenter;

public class DetailActivity extends BaseActivity {

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
        return null;
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


}
