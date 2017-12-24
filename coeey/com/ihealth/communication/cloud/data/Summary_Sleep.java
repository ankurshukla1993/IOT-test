package com.ihealth.communication.cloud.data;

public class Summary_Sleep {
    private int f971a;
    private String f972b;
    private int f973c;
    private float f974d;
    private int f975e;

    public int getId() {
        return this.f971a;
    }

    public int getIsDelayFifty() {
        return this.f975e;
    }

    public String getOverTime() {
        return this.f972b;
    }

    public float getSleepEfficiency() {
        return this.f974d;
    }

    public int getUseTime() {
        return this.f973c;
    }

    public void setId(int id) {
        this.f971a = id;
    }

    public void setIsDelayFifty(int isDelayFifty) {
        this.f975e = isDelayFifty;
    }

    public void setOverTime(String overTime) {
        this.f972b = overTime;
    }

    public void setSleepEfficiency(float sleepEfficiency) {
        this.f974d = sleepEfficiency;
    }

    public void setUseTime(int useTime) {
        this.f973c = useTime;
    }
}
