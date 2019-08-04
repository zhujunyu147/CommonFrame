package com.zjy.frame.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjy.frame.R;
import com.zjy.frame.widget.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    public Context context;
    private ProgressDialog dialog;
    private LoadingDialog mLoadingDialog;
    @BindView(R.id.iv_left)
    protected ImageView mLeft;
    @BindView(R.id.iv_right)
    protected ImageView mRight;
    @BindView(R.id.tv_title)
    protected TextView mTextViewTitle;

    protected String mTitle;

    protected P presenter;
    protected abstract P createPresenter();
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.IAQBaseTheme);
        context = this;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        presenter = createPresenter();
        initTitleBar();
        initView();
        initData();
    }



    private void initTitleBar() {
        initLeftIcon(mLeft);
        initRightIcon(mRight);
        initTitle(mTextViewTitle);
    }

    protected void initLeftIcon(ImageView left) {
        left.setImageResource(R.mipmap.ic_arrow_back_white);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void initRightIcon(ImageView right) {

    }

    protected void initTitle(TextView title) {
        if (!TextUtils.isEmpty(mTitle)) {
            title.setText(mTitle);
        }
    }

    public void initData() {
    }

    public void initView() {
    }


    public void showLoadingDialog() {
        if (null == mLoadingDialog) {
            initLoadingDialog();
        }
        mLoadingDialog.show();
    }


    private void initLoadingDialog() {
        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setCancelable(false);
    }

    public void dismissLoadingDialog() {
        if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoadingFileDialog() {

    }

    @Override
    public void hideLoadingFileDialog() {

    }

    @Override
    public void onProgress(long totalSize, long downSize) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onErrorCode(BaseModel model) {

    }
}
