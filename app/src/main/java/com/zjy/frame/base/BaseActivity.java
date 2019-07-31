package com.zjy.frame.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    public Context context;
    private ProgressDialog dialog;
    protected P presenter;
    protected abstract P createPresenter();
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(getLayoutId());
        presenter = createPresenter();
        initView();
        initData();
    }

    public void initData() {
    }

    public void initView() {
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
