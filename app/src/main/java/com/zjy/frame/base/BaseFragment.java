package com.zjy.frame.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjy.frame.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    public Context context;
    private ProgressDialog dialog;
    protected P presenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.IAQBaseTheme);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View rootView = localInflater.inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, rootView);
        initView(rootView);
        initData();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void initView(View view) {

    }

    public void initData() {
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
