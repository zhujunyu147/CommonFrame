package com.zjy.frame.device.iaqseries;


import com.zjy.frame.device.iaqstategy.AlarmCanNotShow;
import com.zjy.frame.device.iaqstategy.IqCanNotShow;

/**
 * Created by Jin on 13/11/2017.
 */

public class Gen2in4Hcho extends Gen2Series {

    public Gen2in4Hcho(String serialNumber) {
        super(serialNumber);

        mIqBehavior = new IqCanNotShow();
        mAlarmBehavior = new AlarmCanNotShow();

    }

}
