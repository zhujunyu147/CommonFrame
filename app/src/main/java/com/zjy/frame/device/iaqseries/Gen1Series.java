package com.zjy.frame.device.iaqseries;


import com.zjy.frame.device.IAQDevice;
import com.zjy.frame.device.iaqstategy.AlarmCanNotShow;
import com.zjy.frame.device.iaqstategy.IqCanNotShow;
import com.zjy.frame.device.iaqstategy.SavePowerCanNotShow;
import com.zjy.frame.device.iaqstategy.StandbyScreenCanNotShow;

/**
 * Created by Jin on 13/11/2017.
 */

public abstract class Gen1Series extends IAQDevice {

    public Gen1Series(String serialNumber) {
        mGenVersion = GEN_1;
        mDeviceId = serialNumber;

        mStandbyScreenBehavior = new StandbyScreenCanNotShow();
        mSavePowerBehavior = new SavePowerCanNotShow();
        mIqBehavior = new IqCanNotShow();
        mAlarmBehavior = new AlarmCanNotShow();
    }

}
