package com.zjy.frame.slidemenu;

import android.view.View;
import android.widget.ListView;

import com.zjy.frame.R;
import com.zjy.frame.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class SlidingMenuLeftFragment extends BaseFragment<SlideMenuPresenter> implements SlideMenuView {

    @BindView(R.id.list_menu)
    public ListView listView;


    @Override
    protected SlideMenuPresenter createPresenter() {
        return new SlideMenuPresenter(this);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_sliding_left_menu;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getDataList(getResources());
    }

    @Override
    public void getSlideMenuList(List<SlideMenuBean> list) {

        listView.setAdapter(new SlideMenuAdapter(getActivity(),list));
    }
}
