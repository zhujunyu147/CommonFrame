package com.zjy.frame.base;

import android.content.Context;
import android.view.View;

import java.util.List;

public abstract class BasicHolder<T> {
    public View holderView;
    public List<T> mLists;

    public BasicHolder(Context context,List<T> mLists) {
        this.mLists = mLists;
        holderView = getInflateView(context);
    }

    public abstract View getInflateView(Context context);

    public abstract void bindData(int position);
}
