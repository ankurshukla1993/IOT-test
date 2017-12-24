package com.lifesense.ble.bean;

public class DeviceUserInfo {
    private String deviceId;
    private String userName;
    private int userNumber;

    public DeviceUserInfo(int i, String str) {
        this.userName = str;
        this.userNumber = i;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getUserName() {
        return this.userName;
    }

    public int getUserNumber() {
        return this.userNumber;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public void setUserNumber(int i) {
        this.userNumber = i;
    }

    public String toString() {
        return "DeviceUserInfo [userNumber=" + this.userNumber + ", userName=" + this.userName + ", deviceId=" + this.deviceId + "]";
    }
}
