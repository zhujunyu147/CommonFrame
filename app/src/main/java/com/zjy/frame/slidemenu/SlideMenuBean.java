package com.zjy.frame.slidemenu;

import android.graphics.drawable.Drawable;

public class SlideMenuBean {
    public int mIconId;
    public Drawable mIcon;
    public String mText;
    public String mType;
    public Object mObject;

    public SlideMenuBean(int mIconId, Drawable mIcon, String mText, String mType, Object mObject) {
        this.mIconId = mIconId;
        this.mIcon = mIcon;
        this.mText = mText;
        this.mType = mType;
        this.mObject = mObject;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int iconId) {
        this.mIconId = iconId;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        this.mIcon = icon;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

}
