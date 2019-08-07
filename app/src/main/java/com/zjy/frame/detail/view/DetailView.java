package com.zjy.frame.detail.view;

import android.net.Uri;

import com.zjy.frame.base.BaseView;

public interface DetailView extends BaseView {

    void shotSuccess(Uri uri);
    void shotFailed();

}
