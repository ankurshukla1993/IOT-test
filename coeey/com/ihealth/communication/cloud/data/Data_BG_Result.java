package com.ihealth.communication.cloud.data;

public class Data_BG_Result {
    private int f716a;
    private long f717b;
    private String f718c;
    private long f719d;
    private double f720e;
    private double f721f;
    private float f722g;
    private float f723h;
    private int f724i;
    private int f725j;
    private int f726k;
    private long f727l;
    private String f728m;
    private String f729n;
    private String f730o;
    private String f731p;
    private int f732q;
    private int f733r;
    private int f734s;
    private String f735t;

    public Data_BG_Result() {
        this.f718c = new String();
        this.f728m = new String();
        this.f729n = new String();
        this.f730o = new String();
        this.f731p = new String();
        this.f735t = new String();
    }

    public Data_BG_Result(int changeType, long lastChangeTime, String phoneDataID, long phoneCreateTime, double lat, double lon, float timeZone, float bGValue, int medication, int mTimeType, int measureType, long measureTime, String note, String mechineType, String mechineDeviceID, String bottleId, int sports, int snacks, int effective, String iHealthID) {
        this.f716a = changeType;
        this.f717b = lastChangeTime;
        this.f718c = phoneDataID;
        this.f719d = phoneCreateTime;
        this.f720e = lat;
        this.f721f = lon;
        this.f722g = timeZone;
        this.f723h = bGValue;
        this.f724i = medication;
        this.f725j = mTimeType;
        this.f726k = measureType;
        this.f727l = measureTime;
        this.f728m = note;
        this.f729n = mechineType;
        this.f730o = mechineDeviceID;
        this.f731p = bottleId;
        this.f732q = sports;
        this.f733r = snacks;
        this.f734s = effective;
        this.f735t = iHealthID;
    }

    public float getBGValue() {
        return this.f723h;
    }

    public String getBottleId() {
        return this.f731p;
    }

    public int getChangeType() {
        return this.f716a;
    }

    public int getEffective() {
        return this.f734s;
    }

    public long getLastChangeTime() {
        return this.f717b;
    }

    public double getLat() {
        return this.f720e;
    }

    public double getLon() {
        return this.f721f;
    }

    public int getMTimeType() {
        return this.f725j;
    }

    public long getMeasureTime() {
        return this.f727l;
    }

    public int getMeasureType() {
        return this.f726k;
    }

    public String getMechineDeviceID() {
        return this.f730o;
    }

    public String getMechineType() {
        return this.f729n;
    }

    public int getMedication() {
        return this.f724i;
    }

    public String getNote() {
        return this.f728m;
    }

    public long getPhoneCreateTime() {
        return this.f719d;
    }

    public String getPhoneDataID() {
        return this.f718c;
    }

    public int getSnacks() {
        return this.f733r;
    }

    public int getSports() {
        return this.f732q;
    }

    public float getTimeZone() {
        return this.f722g;
    }

    public String getiHealthID() {
        return this.f735t;
    }

    public void setBGValue(float bGValue) {
        this.f723h = bGValue;
    }

    public void setBottleId(String bottleId) {
        this.f731p = bottleId;
    }

    public void setChangeType(int changeType) {
        this.f716a = changeType;
    }

    public void setEffective(int effective) {
        this.f734s = effective;
    }

    public void setLastChangeTime(long lastChangeTime) {
        this.f717b = lastChangeTime;
    }

    public void setLat(double lat) {
        this.f720e = lat;
    }

    public void setLon(double lon) {
        this.f721f = lon;
    }

    public void setMTimeType(int mTimeType) {
        this.f725j = mTimeType;
    }

    public void setMeasureTime(long measureTime) {
        this.f727l = measureTime;
    }

    public void setMeasureType(int measureType) {
        this.f726k = measureType;
    }

    public void setMechineDeviceID(String mechineDeviceID) {
        this.f730o = mechineDeviceID;
    }

    public void setMechineType(String mechineType) {
        this.f729n = mechineType;
    }

    public void setMedication(int medication) {
        this.f724i = medication;
    }

    public void setNote(String note) {
        this.f728m = note;
    }

    public void setPhoneCreateTime(long phoneCreateTime) {
        this.f719d = phoneCreateTime;
    }

    public void setPhoneDataID(String phoneDataID) {
        this.f718c = phoneDataID;
    }

    public void setSnacks(int snacks) {
        this.f733r = snacks;
    }

    public void setSports(int sports) {
        this.f732q = sports;
    }

    public void setTimeZone(float timeZone) {
        this.f722g = timeZone;
    }

    public void setiHealthID(String iHealthID) {
        this.f735t = iHealthID;
    }
}
