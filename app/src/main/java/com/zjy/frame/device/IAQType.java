package com.zjy.frame.device;

import android.content.Context;

import com.zjy.frame.utils.Constants;

public class IAQType {


    public static final int GEN1_HCHO_ONLY = 110;
    public static final int GEN1_CO2_ONLY = 111;
    public static final int GEN1_TVOC_ONLY = 112;
    public static final int GEN1_HCHO_CO2 = 113;
    public static final int GEN1_HCHO_TVOC = 114;
    public static final int GEN1_CO2_TVOC = 115;
    public static final int GEN1_HCHO_CO2_TVOC = 116;
    public static final int GEN1_HCHO_CO2_TVOC_NONE = 118;

    public static final int GEN2_HCHO_CO2_TVOC_NONE = 119;
    public static final int GEN2_HCHO_ONLY = 120;
    public static final int GEN2_HCHO_CO2_TVOC = 121;
    public static final int GEN2_CO2_TVOC = 122;


    public static int getSupportNewParameter(String serialNum) {


        if (serialNum.startsWith("001000021") || serialNum.startsWith("001000022")) {
            return GEN1_HCHO_CO2_TVOC_NONE;
        } else if (serialNum.startsWith("001000023") || serialNum.startsWith("001000024")) {
            return GEN1_HCHO_CO2_TVOC;
        } else if (serialNum.startsWith("001000025") || serialNum.startsWith("001000026")) {
            return GEN1_TVOC_ONLY;
        } else if (serialNum.startsWith("001000027") || serialNum.startsWith("001000028")) {
            return GEN1_HCHO_ONLY;
        } else if (serialNum.startsWith("001000029") || serialNum.startsWith("00100002a")) {
            return GEN1_CO2_ONLY;
        } else if (serialNum.startsWith("001000030")) {
            return GEN1_HCHO_CO2_TVOC;
        } else if (serialNum.startsWith("001000031")) {
            return GEN1_TVOC_ONLY;
        } else if (serialNum.startsWith("001000032")) {
            return GEN1_HCHO_ONLY;
        } else if (serialNum.startsWith("001000033")) {
            return GEN1_CO2_ONLY;
        } else if (serialNum.startsWith("001000034")) {
            return GEN1_HCHO_CO2_TVOC;
        } else if (serialNum.startsWith("002000010")) {
            return GEN2_HCHO_CO2_TVOC_NONE;
        } else if (serialNum.startsWith("002000020")) {
            return GEN2_HCHO_ONLY;
        } else if (serialNum.startsWith("002000030")) {
            return GEN2_HCHO_CO2_TVOC;
        } else if (serialNum.startsWith("002000040")) {
            return GEN2_CO2_TVOC;
        } else if (serialNum.startsWith("002000050")) {
            return GEN2_HCHO_CO2_TVOC_NONE;
        }
        return GEN1_HCHO_CO2_TVOC;
    }

    public static String getGeneration(String serialNum) {

        if (serialNum.startsWith("001")) {
            return Constants.GEN_1;
        } else if (serialNum.startsWith("002")) {
            return Constants.GEN_2;
        }
        return Constants.GEN_1;
    }
}
