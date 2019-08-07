package com.zjy.frame.detail.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zjy.frame.base.BaseFragment;

import java.util.List;

public class DetailFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mListFragments;


    public DetailFragmentAdapter(FragmentManager fm, List<BaseFragment> mListFragments) {
        super(fm);
        this.mListFragments = mListFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragments.get(position);
    }

    @Override
    public int getCount() {
        return mListFragments.size();
    }
}
