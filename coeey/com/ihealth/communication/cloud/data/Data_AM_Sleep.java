package com.ihealth.communication.cloud.data;

public class Data_AM_Sleep {
    private int f668a;
    private long f669b;
    private String f670c;
    private long f671d;
    private double f672e;
    private double f673f;
    private float f674g;
    private int f675h;
    private String f676i;
    private long f677j;
    private String f678k;
    private String f679l;
    private String f680m;

    public int getChangeType() {
        return this.f668a;
    }

    public long getLastChangeTime() {
        return this.f669b;
    }

    public double getLat() {
        return this.f672e;
    }

    public double getLon() {
        return this.f673f;
    }

    public long getMeasureTime() {
        return this.f677j;
    }

    public String getMechineDeviceID() {
        return this.f679l;
    }

    public String getMechineType() {
        return this.f678k;
    }

    public long getPhoneCreateTime() {
        return this.f671d;
    }

    public String getPhoneDataID() {
        return this.f670c;
    }

    public int getSleepLevel() {
        return this.f675h;
    }

    public String getTimeSectionId() {
        return this.f676i;
    }

    public float getTimeZone() {
        return this.f674g;
    }

    public String getiHealthID() {
        return this.f680m;
    }

    public void setChangeType(int changeType) {
        this.f668a = changeType;
    }

    public void setLastChangeTime(long lastChangeTime) {
        this.f669b = lastChangeTime;
    }

    public void setLat(double lat) {
        this.f672e = lat;
    }

    public void setLon(double lon) {
        this.f673f = lon;
    }

    public void setMeasureTime(long measureTime) {
        this.f677j = measureTime;
    }

    public void setMechineDeviceID(String mechineDeviceID) {
        this.f679l = mechineDeviceID;
    }

    public void setMechineType(String mechineType) {
        this.f678k = mechineType;
    }

    public void setPhoneCreateTime(long phoneCreateTime) {
        this.f671d = phoneCreateTime;
    }

    public void setPhoneDataID(String phoneDataID) {
        this.f670c = phoneDataID;
    }

    public void setSleepLevel(int sleepLevel) {
        this.f675h = sleepLevel;
    }

    public void setTimeSectionId(String timeSectionId) {
        this.f676i = timeSectionId;
    }

    public void setTimeZone(float timeZone) {
        this.f674g = timeZone;
    }

    public void setiHealthID(String iHealthID) {
        this.f680m = iHealthID;
    }
}
