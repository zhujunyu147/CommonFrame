package com.zjy.frame.device.iaqstategy;

/**
 * Created by Jin on 13/11/2017.
 */

public class StandbyScreenCanNotShow implements StandbyScreenBehavior {

    @Override
    public boolean canShow() {
        return false;
    }
    
}
