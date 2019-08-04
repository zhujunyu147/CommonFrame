package com.zjy.frame.device.iaqseries;


import com.zjy.frame.device.iaqstategy.SleepModeCanNotShow;


public class Gen1in3 extends Gen1Series {

    public Gen1in3(String serialNumber) {
        super(serialNumber);

        mSleepModeBehavior = new SleepModeCanNotShow();
    }

}
