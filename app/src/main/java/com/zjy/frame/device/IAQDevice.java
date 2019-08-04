package com.zjy.frame.device;

import android.util.Log;

import com.zjy.frame.device.iaqstategy.AlarmBehavior;
import com.zjy.frame.device.iaqstategy.IqBehavior;
import com.zjy.frame.device.iaqstategy.SavePowerBehavior;
import com.zjy.frame.device.iaqstategy.SleepModeBehavior;
import com.zjy.frame.device.iaqstategy.StandbyScreenBehavior;


/**
 * Created by Jin on 13/11/2017.
 */

public abstract class IAQDevice {

    public static final int GEN_1 = 1;
    public static final int GEN_2 = 2;

    protected int mGenVersion;

    // strategy mode
    protected SavePowerBehavior mSavePowerBehavior;
    protected SleepModeBehavior mSleepModeBehavior;
    protected StandbyScreenBehavior mStandbyScreenBehavior;
    protected IqBehavior mIqBehavior;
    protected AlarmBehavior mAlarmBehavior;

    // common properties
    protected String mHomeName;
    protected String mDeviceName;
    protected String mCity;
    protected String mCityKey;
    protected String mDeviceId;
    protected int mOnlineStatus;

    protected String mTemperature;
    protected String mHumidity;
    protected String mPm25;

    // special properties
    protected String mCo2;
    protected String mHcho;
    protected String mTvoc;

    //critical value
    protected String mPm25Red;
    protected String mPm25Yellow;
    protected String mCo2Red;
    protected String mHchoRed;
    protected String mTvocRed;
    protected String mIqRed;

    protected String mSleepMode;
    protected String mSleepStart;
    protected String mSleepEnd;
    protected String mSavePower;
    protected String mStandbyScreen;
    protected String mTemperatureUnit;
    protected String mIq;


    public void setSavePowerBehavior(SavePowerBehavior savePowerBehavior) {
        this.mSavePowerBehavior = savePowerBehavior;
    }

    public void setSleepModeBehavior(SleepModeBehavior sleepModeBehavior) {
        this.mSleepModeBehavior = sleepModeBehavior;
    }

    public void setStandbyScreenBehavior(StandbyScreenBehavior standbyScreenBehavior) {
        this.mStandbyScreenBehavior = standbyScreenBehavior;
    }

    public void setIqBehavior(IqBehavior iqBehavior) {
        mIqBehavior = iqBehavior;
    }

    public SavePowerBehavior getSavePowerBehavior() {
        return mSavePowerBehavior;
    }

    public SleepModeBehavior getSleepModeBehavior() {
        return mSleepModeBehavior;
    }

    public StandbyScreenBehavior getStandbyScreenBehavior() {
        return mStandbyScreenBehavior;
    }

    public IqBehavior getIqBehavior() {
        return mIqBehavior;
    }

    public int getGenVersion() {
        return mGenVersion;
    }

    public String getHomeName() {
        return mHomeName;
    }

    public void setHomeName(String homeName) {
        mHomeName = homeName;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getmCityKey() {
        return mCityKey;
    }

    public void setmCityKey(String mCityKey) {
        this.mCityKey = mCityKey;
    }

    public String getDeviceId() {
        return mDeviceId;
    }

    public int getOnlineStatus() {
        return mOnlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        mOnlineStatus = onlineStatus;
    }

    public String getDeviceName() {
        return mDeviceName;
    }

    public void setDeviceName(String deviceName) {
        mDeviceName = deviceName;
    }

    public String getTemperature() {
        return mTemperature;
    }

    public void setTemperature(String temperature) {
        mTemperature = temperature;
    }

    public String getHumidity() {
        return mHumidity;
    }

    public void setHumidity(String humidity) {
        mHumidity = humidity;
    }

    public String getPm25() {
        return mPm25;
    }

    public void setPm25(String pm25) {
        mPm25 = pm25;
    }

    public void setDeviceId(String deviceId) {
        mDeviceId = deviceId;
    }

    public String getCo2() {
        return mCo2;
    }

    public void setCo2(String co2) {
        mCo2 = co2;
    }

    public String getHcho() {
        return mHcho;
    }

    public void setHcho(String hcho) {
        mHcho = hcho;
    }

    public String getTvoc() {
        return mTvoc;
    }

    public void setTvoc(String tvoc) {
        mTvoc = tvoc;
    }

    public String getSleepMode() {
        return mSleepMode;
    }

    public void setSleepMode(String sleepMode) {
        mSleepMode = sleepMode;
    }

    public String getSleepStart() {
        return mSleepStart;
    }

    public void setSleepStart(String sleepStart) {
        mSleepStart = sleepStart;
    }

    public String getSleepEnd() {
        return mSleepEnd;
    }

    public void setSleepEnd(String sleepEnd) {
        mSleepEnd = sleepEnd;
    }

    public String getSavePower() {
        return mSavePower;
    }

    public void setSavePower(String savePower) {
        mSavePower = savePower;
    }

    public String getStandbyScreen() {
        return mStandbyScreen;
    }

    public void setStandbyScreen(String standbyScreen) {
        mStandbyScreen = standbyScreen;
    }

    public String getTemperatureUnit() {
        return mTemperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        mTemperatureUnit = temperatureUnit;
    }

    public String getIq() {
        return mIq;
    }

    public void setIq(String iq) {
        mIq = iq;
    }


    public void setmPm25Red(String mPm25Red) {
        this.mPm25Red = mPm25Red;
    }

    public void setmPm25Yellow(String mPm25Yellow) {
        this.mPm25Yellow = mPm25Yellow;
    }

    public void setmCo2Red(String mCo2Red) {
        this.mCo2Red = mCo2Red;
        Log.e("TTTTT","mCo2Red:"+this.mCo2Red);
    }

    public void setmHchoRed(String mHchoRed) {

        this.mHchoRed = mHchoRed;
        Log.e("TTTTT","mHchoRed:"+this.mHchoRed);
    }

    public void setmTvocRed(String mTvocRed) {
        this.mTvocRed = mTvocRed;
    }

    public String getmPm25Red() {
        return mPm25Red;
    }

    public String getmPm25Yellow() {
        return mPm25Yellow;
    }

    public String getmCo2Red() {
        return mCo2Red;
    }

    public String getmHchoRed() {
        return mHchoRed;
    }

    public String getmTvocRed() {
        return mTvocRed;
    }

    public String getmIqRed() {
        return mIqRed;
    }

    public void setmIqRed(String mIqRed) {
        this.mIqRed = mIqRed;
        Log.e("TTTTT","mIqRed:"+mIqRed);
    }
}
