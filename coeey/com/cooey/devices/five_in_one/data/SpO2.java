package com.cooey.devices.five_in_one.data;

public class SpO2 {
    public int PULSE_RATE_INVALID = 255;
    public int SPO2_INVALID = 127;
    private int SpO2;
    private int pulseRate;
    private int status;

    public SpO2(int spO2, int pulseRate, int status) {
        this.SpO2 = spO2;
        this.pulseRate = pulseRate;
        this.status = status;
    }

    public int getSpO2() {
        return this.SpO2;
    }

    public void setSpO2(int spO2) {
        this.SpO2 = spO2;
    }

    public int getPulseRate() {
        return this.pulseRate;
    }

    public String toString() {
        return "SpO2: " + (this.SpO2 != this.SPO2_INVALID ? Integer.valueOf(this.SpO2) : "- -") + "  Pulse Rate:" + (this.pulseRate != this.PULSE_RATE_INVALID ? Integer.valueOf(this.pulseRate) : "- -");
    }
}
