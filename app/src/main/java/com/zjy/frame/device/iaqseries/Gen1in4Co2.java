package com.zjy.frame.device.iaqseries;


import com.zjy.frame.device.iaqstategy.SleepModeCanNotShowPeriod;

/**
 * Created by Jin on 13/11/2017.
 */

public class Gen1in4Co2 extends Gen1Series {

    public Gen1in4Co2(String serialNumber) {
        super(serialNumber);

        mSleepModeBehavior = new SleepModeCanNotShowPeriod();
    }

}
