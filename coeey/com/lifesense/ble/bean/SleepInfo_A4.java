package com.lifesense.ble.bean;

public class SleepInfo_A4 {
    private String broadcastId;
    private String date;
    private String deviceId;
    private int sleepStatus;
    private long utc;

    public String getBroadcastId() {
        return this.broadcastId;
    }

    public String getDate() {
        return this.date;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public int getSleepStatus() {
        return this.sleepStatus;
    }

    public long getUtc() {
        return this.utc;
    }

    public void setBroadcastId(String str) {
        this.broadcastId = str;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setSleepStatus(int i) {
        this.sleepStatus = i;
    }

    public void setUtc(long j) {
        this.utc = j;
    }

    public String toString() {
        return "SleepInfo_A4 [deviceId=" + this.deviceId + ", utc=" + this.utc + ", date=" + this.date + ", sleepStatus=" + this.sleepStatus + "]";
    }
}
