package com.zjy.frame.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Locale;

public class CommonUtils {

    public static String getLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh")) {
            return "zh-CN";
        } else {
            return "en-US";
        }
    }


    public static boolean isZh(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh")) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 华氏温度转摄氏温度
     *
     * @param tW 华氏温度
     * @return 摄氏温度
     */
    public static float W2C(float tW) {
        return (tW - 32) * 5 / 9;
    }

    /**
     * 摄氏温度转华氏温度
     *
     * @param tC 摄氏温度
     * @return 华氏温度
     */
    public static int C2W(float tC) {
        return (int) ((9 * tC / 5 + 32));
    }

}
