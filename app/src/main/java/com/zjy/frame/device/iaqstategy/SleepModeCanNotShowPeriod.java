package com.zjy.frame.device.iaqstategy;

/**
 * Created by Jin on 13/11/2017.
 */

public class SleepModeCanNotShowPeriod implements SleepModeBehavior {

    @Override
    public boolean canShow() {
        return true;
    }

    @Override
    public boolean canShowPeriod() {
        return false;
    }

}
