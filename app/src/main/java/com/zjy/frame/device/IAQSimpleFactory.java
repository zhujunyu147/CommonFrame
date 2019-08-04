package com.zjy.frame.device;

import com.zjy.frame.device.iaqseries.Gen1in3;
import com.zjy.frame.device.iaqseries.Gen1in4Co2;
import com.zjy.frame.device.iaqseries.Gen1in4Hcho;
import com.zjy.frame.device.iaqseries.Gen1in4Tvoc;
import com.zjy.frame.device.iaqseries.Gen1in6;
import com.zjy.frame.device.iaqseries.Gen2in3;
import com.zjy.frame.device.iaqseries.Gen2in4Hcho;
import com.zjy.frame.device.iaqseries.Gen2in5;
import com.zjy.frame.device.iaqseries.Gen2in6;

import java.util.ArrayList;
import java.util.List;

public class IAQSimpleFactory {

    private ArrayList<IAQDevice> mIAQDevices = new ArrayList<>();

    private static IAQSimpleFactory mIAQSimpleFactory;

    private IAQSimpleFactory() {

    }

    public static IAQSimpleFactory getInstance() {
        if (mIAQSimpleFactory == null) {
            mIAQSimpleFactory = new IAQSimpleFactory();
        }

        return mIAQSimpleFactory;
    }

    public IAQDevice createIAQDevice(String serialNumber) {
        IAQDevice iaqDevice;
        //002000020017306B48A3
        if (serialNumber.startsWith("001000021") || serialNumber.startsWith("001000022")) {
            iaqDevice = new Gen1in3(serialNumber);
        } else if (serialNumber.startsWith("001000023") || serialNumber.startsWith("001000024") || (serialNumber.startsWith("001000030")) || (serialNumber.startsWith("001000034"))) {
            iaqDevice = new Gen1in6(serialNumber);
        } else if (serialNumber.startsWith("001000025") || serialNumber.startsWith("001000026") || (serialNumber.startsWith("001000031"))) {
            iaqDevice = new Gen1in4Tvoc(serialNumber);
        } else if (serialNumber.startsWith("001000027") || serialNumber.startsWith("001000028") || (serialNumber.startsWith("001000032"))) {
            iaqDevice = new Gen1in4Hcho(serialNumber);
        } else if (serialNumber.startsWith("001000029") || serialNumber.startsWith("00100002a") || (serialNumber.startsWith("001000032"))) {
            iaqDevice = new Gen1in4Co2(serialNumber);
        } else if (serialNumber.startsWith("002000010")) {
            iaqDevice = new Gen2in3(serialNumber);
        } else if (serialNumber.startsWith("002000020")) {
            iaqDevice = new Gen2in4Hcho(serialNumber);
        } else if (serialNumber.startsWith("002000030")) {
            iaqDevice = new Gen2in6(serialNumber);
        } else if (serialNumber.startsWith("002000040")) {
            iaqDevice = new Gen2in5(serialNumber);
        } else if (serialNumber.startsWith("002000050")) {
            iaqDevice = new Gen2in3(serialNumber);
        } else {
            iaqDevice = new Gen1in3(serialNumber);
        }

        iaqDevice.setDeviceId(serialNumber);
        mIAQDevices.add(iaqDevice);

        return iaqDevice;
    }

    public void setIAQDevices(ArrayList<IAQDevice> IAQDevices) {
        mIAQDevices = IAQDevices;
    }

    public ArrayList<IAQDevice> getIAQDevices() {
        return mIAQDevices;
    }

    public IAQDevice getIAQDevice(String deviceId) {
        if (deviceId == null)
            return null;

        for (IAQDevice iaqDevice : mIAQDevices) {
            if (deviceId.equals(iaqDevice.getDeviceId()))
                return iaqDevice;
        }
        return null;
    }

    public List<IAQDevice> getIAQDevice(String homeName, String city) {
        List<IAQDevice> iaqDevices = new ArrayList<>();
        if (homeName == null || city == null)
            return null;

        for (IAQDevice iaqDevice : mIAQDevices) {
            if (homeName.equals(iaqDevice.getHomeName()) && city.equals(iaqDevice.getCity())) {
                iaqDevices.add(iaqDevice);
            }

        }
        return iaqDevices;
    }

    public void removeIAQAllDevices() {
        if (mIAQDevices != null) {
            mIAQDevices.clear();
        }

    }

    public void removeIAQDevice(String deviceId) {
        IAQDevice iaqDevice = getIAQDevice(deviceId);
        if (mIAQDevices.contains(iaqDevice))
            mIAQDevices.remove(iaqDevice);
    }

}
