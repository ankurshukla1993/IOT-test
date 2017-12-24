package com.cooey.devices.five_in_one.data;

public class ECG {
    public int HEART_RATE_INVALID = 0;
    public int RESP_RATE_INVALID = 0;
    private int heartRate;
    private int restRate;
    private int status;

    public ECG(int heartRate, int restRate, int status) {
        this.heartRate = heartRate;
        this.restRate = restRate;
        this.status = status;
    }

    public int getHeartRate() {
        return this.heartRate;
    }

    public int getRestRate() {
        return this.restRate;
    }

    public int getStatus() {
        return this.status;
    }

    public String toString() {
        return "Heart Rate:" + (this.heartRate != this.HEART_RATE_INVALID ? Integer.valueOf(this.heartRate) : "- -") + "  Resp Rate:" + (this.restRate != this.RESP_RATE_INVALID ? Integer.valueOf(this.restRate) : "- -");
    }
}
