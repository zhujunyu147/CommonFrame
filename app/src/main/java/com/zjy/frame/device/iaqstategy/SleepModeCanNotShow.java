package com.zjy.frame.device.iaqstategy;

/**
 * Created by Jin on 13/11/2017.
 */

public class SleepModeCanNotShow implements SleepModeBehavior {

    @Override
    public boolean canShow() {
        return false;
    }

    @Override
    public boolean canShowPeriod() {
        return false;
    }

}
