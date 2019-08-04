package com.zjy.frame.device.iaqseries;


import com.zjy.frame.device.iaqstategy.AlarmCanShow;
import com.zjy.frame.device.iaqstategy.IqCanShow;

/**
 * Created by Jin on 13/11/2017.
 */

public class Gen2in5 extends Gen2Series {

    public Gen2in5(String serial) {
        super(serial);

        mIqBehavior = new IqCanShow();
        mAlarmBehavior = new AlarmCanShow();

    }

}
