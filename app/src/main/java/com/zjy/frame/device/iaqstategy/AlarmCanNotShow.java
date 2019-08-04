package com.zjy.frame.device.iaqstategy;

/**
 * Created by Jin on 2/11/2017.
 */

public class AlarmCanNotShow implements AlarmBehavior {

    @Override
    public boolean canShow() {
        return false;
    }
    
}
