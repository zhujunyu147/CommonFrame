package com.zjy.frame.device.iaqseries;


import com.zjy.frame.device.IAQDevice;
import com.zjy.frame.device.iaqstategy.SavePowerCanShow;
import com.zjy.frame.device.iaqstategy.SleepModeCanShow;
import com.zjy.frame.device.iaqstategy.StandbyScreenCanShow;

/**
 * Created by Jin on 13/11/2017.
 */

public abstract class Gen2Series extends IAQDevice {

    public Gen2Series(String serialNumber) {
        mGenVersion = GEN_2;
        mDeviceId = serialNumber;

        mStandbyScreenBehavior = new StandbyScreenCanShow();
        mSavePowerBehavior = new SavePowerCanShow();
        mSleepModeBehavior = new SleepModeCanShow();
    }

}
