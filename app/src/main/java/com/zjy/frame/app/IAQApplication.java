package com.zjy.frame.app;

import android.app.Application;
import android.content.Context;

import com.bravin.btoast.BToast;

public class IAQApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        BToast.Config.getInstance().apply(this);
        context = getApplicationContext();
    }


    public static Context getContext() {
        return context;
    }
}
