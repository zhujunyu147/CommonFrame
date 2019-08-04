package com.zjy.frame.slidemenu;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjy.frame.R;
import com.zjy.frame.base.BasicHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends BasicHolder<SlideMenuBean> {

    @BindView(R.id.tv_text)
    public TextView tv;
    @BindView(R.id.iv_icon)
    public ImageView iv;

    public ViewHolder(Context context, List<SlideMenuBean> mLists) {
        super(context,mLists);

    }

    @Override
    public View getInflateView(Context context) {
        View view = View.inflate(context, R.layout.list_icon_text, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void bindData(int position) {
        SlideMenuBean slideMenuBean = mLists.get(position);
        iv.setImageResource(slideMenuBean.getIconId());
        tv.setText(slideMenuBean.getText());
    }
}
