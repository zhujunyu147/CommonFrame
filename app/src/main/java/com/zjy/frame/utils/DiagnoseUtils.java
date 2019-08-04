package com.zjy.frame.utils;

import android.content.Context;
import android.text.TextUtils;

import com.zjy.frame.device.IAQDevice;

public class DiagnoseUtils {



    public static boolean isCelsius(Context context) {
        String str = PreferenceUtil.getString(context, Constants.KEY_TYPE_TEMP, Constants.KEY_CELSIUS);
        if (Constants.KEY_CELSIUS.equals(str)) {
            return true;
        } else if (Constants.KEY_FAHRENHEIT.equals(str)) {
            return false;
        }
        return true;
    }


    public static boolean isHCHOLevelNormal(String hchoValue, IAQDevice iaqDevice) {

        String hchoValue_ = hchoValue;

        if (android.text.TextUtils.isEmpty(hchoValue_)) {
            hchoValue_ = "0";
        }

        try {
            String hchpRed = (TextUtils.isEmpty(iaqDevice.getmHchoRed()) ? Constants.HCHO_DEFAULT_VALUE : iaqDevice.getmHchoRed());

            int hchoLevel = DiagnoseUtils.getHCHOLevel(Float.parseFloat(hchoValue_), Float.parseFloat(hchpRed));
            if (hchoLevel == Constants.HCHO_NORMAL) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return true;
        }

    }

    public static boolean isCO2LevelNormal(String co2Value, IAQDevice iaqDevice) {
        String co2Value_ = co2Value;

        if (android.text.TextUtils.isEmpty(co2Value_)) {
            co2Value_ = "0";
        }
        try {
            String co2Red = (TextUtils.isEmpty(iaqDevice.getmCo2Red()) ? Constants.CO2_DEFAULT_VALUE : iaqDevice.getmCo2Red());

            int co2Level = DiagnoseUtils.getCO2Level(Integer.parseInt(co2Value_), Float.parseFloat(co2Red));
            if (co2Level == Constants.CO2_NORMAL) {
                return true;
            } else {
                return false;

            }
        } catch (NumberFormatException e) {
            return true;
        }

    }

    public static boolean isTVOCLevelnormal(String tvocValue, IAQDevice iaqDevice) {

        String tvocValue_ = tvocValue;

        if (android.text.TextUtils.isEmpty(tvocValue_)) {
            tvocValue_ = "0";
        }
        try {
            String tvocRed = (TextUtils.isEmpty(iaqDevice.getmTvocRed()) ? Constants.TVOC_DEFAULT_VALUE : iaqDevice.getmTvocRed());

            int tvocLevel = DiagnoseUtils.getTVOCLevel(Float.parseFloat(tvocValue_), Float.parseFloat(tvocRed));
            if (tvocLevel == Constants.TVOC_NORMAL) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return true;
        }

    }


    public static boolean isIQLevelnormal(String iqValue, IAQDevice iaqDevice) {

        String iqValue_ = iqValue;

        if (!iaqDevice.getIqBehavior().canShow()) {
            return true;
        }

        if (android.text.TextUtils.isEmpty(iqValue)) {
            iqValue_ = "0";
        }
        try {
            String iqRed = (TextUtils.isEmpty(iaqDevice.getmIqRed()) ? Constants.IQ_DEFAULT_VALUE : iaqDevice.getmIqRed());

            int iqLevel = DiagnoseUtils.getIQLevel(Float.parseFloat(iqValue_), Float.parseFloat(iqRed));
            if (iqLevel == Constants.TVOC_NORMAL) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return true;
        }
    }



    public static int getPMLevel( int pmValue, float criticalRedValue, float criticalYeValue) {

        if (pmValue >= 0 && pmValue < criticalYeValue) {
            return Constants.PM_LEVEL_2;
        } else if (pmValue >= criticalYeValue && pmValue < criticalRedValue) {
            return Constants.PM_LEVEL_4;
        } else if (pmValue >= criticalRedValue) {
            return Constants.PM_LEVEL_5;
        }
        return Constants.PM_LEVEL_UNKNOWN;
    }


    public static int getHCHOLevel(float hchoValue, float criticalValue) {
        if (hchoValue >= criticalValue) {
            return Constants.HCHO_ABNORMAL;
        } else {
            return Constants.HCHO_NORMAL;
        }
    }

    public static int getTVOCLevel(float tvocValue, float criticalValue) {
        if (tvocValue >= criticalValue) {
            return Constants.TVOC_ABNORMAL;
        } else {
            return Constants.TVOC_NORMAL;
        }
    }


    public static int getCO2Level(int co2Value, float criticalValue) {
        if (co2Value >= criticalValue) {
            return Constants.CO2_ABNORMAL;
        } else {
            return Constants.CO2_NORMAL;
        }
    }


    public static int getIQLevel(float iqValue, float criticalValue) {
        if (iqValue >= criticalValue) {
            return Constants.IQ_NORMAL;
        } else {
            return Constants.IQ_ABNORMAL;
        }
    }

}
