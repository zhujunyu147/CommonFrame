package com.zjy.frame.slidemenu;

import android.content.res.Resources;
import android.util.Log;

import com.zjy.frame.R;
import com.zjy.frame.base.BasePresenter;
import com.zjy.frame.base.BaseView;
import com.zjy.frame.utils.CommonUtils;
import com.zjy.frame.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

public class SlideMenuPresenter extends BasePresenter<SlideMenuView> {
    private List<SlideMenuBean> mDataList;

    public SlideMenuPresenter(SlideMenuView slideMenuView) {
        super(slideMenuView);
        mDataList = new ArrayList<>();
    }


    public void getDataList(Resources res) {

        mDataList.clear();
        final int resourceId[] = ResourceUtil.getResourceIdArray(res, R.array.sliding_menu_left_icon);
        final String text[] = ResourceUtil.getStringArray(res, R.array.sliding_menu_left_title);
        final int length = resourceId.length;
        for (int i = 0; i < length; i++) {
            mDataList.add(new SlideMenuBean(resourceId[i], null, text[i], "", null));
        }


        boolean isZh = CommonUtils.isZh(baseView.getContext());
        if (!isZh) {
            mDataList.remove(1);
        }
        baseView.getSlideMenuList(mDataList);
    }

}
