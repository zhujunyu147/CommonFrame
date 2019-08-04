
package com.zjy.frame.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class ResourceUtil {

    public static int[] getResourceIdArray(Resources res, int arrayId) {
        if (null == res || arrayId <= 0) {
            return null;
        } else {
            TypedArray ta = res.obtainTypedArray(arrayId);
            if (null != ta) {
                final int length = ta.length();
                int[] result = new int[length];
                for (int i = 0; i < length; i++) {
                    result[i] = ta.getResourceId(i, -1);
                }
                ta.recycle();
                return result;
            } else {
                return null;
            }
        }
    }


    public static String[] getStringArray(Resources res, int arrayId) {
        return (null == res || arrayId <= 0) ? null : res.getStringArray(arrayId);
    }


}
