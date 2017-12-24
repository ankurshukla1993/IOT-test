package com.cooey.devices.five_in_one.data;

public class NIBP {
    public int HIGH_PRESSURE_INVALID = 0;
    public int LOW_PRESSURE_INVALID = 0;
    private int cuffPressure;
    private int highPressure;
    private int lowPressure;
    private int meanPressure;
    private int status;

    public NIBP(int highPressure, int meanPressure, int lowPressure, int cuffPressure, int status) {
        this.highPressure = highPressure;
        this.meanPressure = meanPressure;
        this.lowPressure = lowPressure;
        this.cuffPressure = cuffPressure;
        this.status = status;
    }

    public int getMeanPressure() {
        return this.meanPressure;
    }

    public int getHighPressure() {
        return this.highPressure;
    }

    public int getLowPressure() {
        return this.lowPressure;
    }

    public int getCuffPressure() {
        return this.cuffPressure;
    }

    public int getStatus() {
        return this.status;
    }

    public String toString() {
        return "Cuff:" + (this.cuffPressure != 0 ? Integer.valueOf(this.cuffPressure) : "- -") + "\r\nHigh:" + (this.highPressure != 0 ? Integer.valueOf(this.highPressure) : "- -") + " Low:" + (this.lowPressure != 0 ? Integer.valueOf(this.lowPressure) : "- -") + " Mean:" + (this.meanPressure != 0 ? Integer.valueOf(this.meanPressure) : "- -");
    }
}
