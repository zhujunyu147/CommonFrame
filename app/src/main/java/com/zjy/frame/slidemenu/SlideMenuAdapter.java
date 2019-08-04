package com.zjy.frame.slidemenu;

import android.content.Context;

import com.zjy.frame.base.BasicAdapter;
import com.zjy.frame.base.BasicHolder;

import java.util.List;

public class SlideMenuAdapter extends BasicAdapter {

    protected List<SlideMenuBean> mLists;
    private Context mContext;

    public SlideMenuAdapter( Context mContext,List<SlideMenuBean> mLists) {
        super(mLists);
        this.mLists = mLists;
        this.mContext = mContext;
    }



    @Override
    public BasicHolder initHolder() {
        return new ViewHolder(mContext,mLists);
    }
}
