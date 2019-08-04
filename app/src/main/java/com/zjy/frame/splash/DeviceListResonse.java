package com.zjy.frame.splash;


import java.util.List;

public class DeviceListResonse {

    private List<DevicesBean> devices;

    public List<DevicesBean> getDevices() {
        return devices;
    }

    public void setDevices(List<DevicesBean> devices) {
        this.devices = devices;
    }

    public static class DevicesBean {
        /**
         * deviceId : 002000030018331BA847
         * deviceSerial : 002000030018331BA847
         * deviceInfo : {"room":"Living room","home":"666"}
         * online : false
         */

        private String deviceId;
        private String deviceSerial;
        private DeviceInfoBean deviceInfo;
        private boolean online;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceSerial() {
            return deviceSerial;
        }

        public void setDeviceSerial(String deviceSerial) {
            this.deviceSerial = deviceSerial;
        }

        public DeviceInfoBean getDeviceInfo() {
            return deviceInfo;
        }

        public void setDeviceInfo(DeviceInfoBean deviceInfo) {
            this.deviceInfo = deviceInfo;
        }

        public boolean isOnline() {
            return online;
        }

        public void setOnline(boolean online) {
            this.online = online;
        }

        public static class DeviceInfoBean {
            /**
             * room : Living room
             * home : 666
             */

            private String room;
            private String home;

            public String getRoom() {
                return room;
            }

            public void setRoom(String room) {
                this.room = room;
            }

            public String getHome() {
                return home;
            }

            public void setHome(String home) {
                this.home = home;
            }
        }
    }
}
