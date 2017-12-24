package com.ihealth.communication.cloud.data;

public class Data_AM_SleepSummary {
    private int f706a;
    private long f707b;
    private String f708c;
    private long f709d;
    private double f710e;
    private double f711f;
    private float f712g;
    private int f713h;
    private int f714i;
    private String f715j;

    public int getChangeType() {
        return this.f706a;
    }

    public long getLastChangeTime() {
        return this.f707b;
    }

    public double getLat() {
        return this.f710e;
    }

    public double getLon() {
        return this.f711f;
    }

    public long getPhoneCreateTime() {
        return this.f709d;
    }

    public String getPhoneDataID() {
        return this.f708c;
    }

    public int getSleepEfficiency() {
        return this.f714i;
    }

    public int getSpendMinutes() {
        return this.f713h;
    }

    public float getTimeZone() {
        return this.f712g;
    }

    public String getiHealthID() {
        return this.f715j;
    }

    public void setChangeType(int changeType) {
        this.f706a = changeType;
    }

    public void setLastChangeTime(long lastChangeTime) {
        this.f707b = lastChangeTime;
    }

    public void setLat(double lat) {
        this.f710e = lat;
    }

    public void setLon(double lon) {
        this.f711f = lon;
    }

    public void setPhoneCreateTime(long phoneCreateTime) {
        this.f709d = phoneCreateTime;
    }

    public void setPhoneDataID(String phoneDataID) {
        this.f708c = phoneDataID;
    }

    public void setSleepEfficiency(int sleepEfficiency) {
        this.f714i = sleepEfficiency;
    }

    public void setSpendMinutes(int spendMinutes) {
        this.f713h = spendMinutes;
    }

    public void setTimeZone(float timeZone) {
        this.f712g = timeZone;
    }

    public void setiHealthID(String iHealthID) {
        this.f715j = iHealthID;
    }
}
