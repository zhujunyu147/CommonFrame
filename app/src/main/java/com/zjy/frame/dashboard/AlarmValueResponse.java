package com.zjy.frame.dashboard;

public class AlarmValueResponse {

    /**
     * data : {"co2":"1000.0","hcho":"80.0","iqRed":"60.0","pm25Red":"200.0","pm25Yellow":"80.0","tvoc":"380.0"}
     * type : IAQAlertValue
     */

    private String data;
    private String type;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
