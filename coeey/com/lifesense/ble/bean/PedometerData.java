package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PedometerData implements Parcelable {
    public static final Creator CREATOR = new C2245e();
    private int battery;
    private String broadcastId;
    private double calories;
    private String date;
    private String deviceId;
    private String deviceSn;
    private int distance;
    private double examount;
    private int exerciseTime;
    private int intensityLevel;
    private int runSteps;
    private int sleepStatus;
    private int userNo;
    private int walkSteps;

    private PedometerData(Parcel parcel) {
        this.deviceSn = parcel.readString();
        this.broadcastId = parcel.readString();
        this.date = parcel.readString();
        this.deviceId = parcel.readString();
        this.userNo = parcel.readInt();
        this.walkSteps = parcel.readInt();
        this.runSteps = parcel.readInt();
        this.examount = parcel.readDouble();
        this.calories = parcel.readDouble();
        this.exerciseTime = parcel.readInt();
        this.distance = parcel.readInt();
        this.battery = parcel.readInt();
        this.sleepStatus = parcel.readInt();
        this.intensityLevel = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public int getBattery() {
        return this.battery;
    }

    public String getBroadcastId() {
        return this.broadcastId;
    }

    public double getCalories() {
        return this.calories;
    }

    public String getDate() {
        return this.date;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceSn() {
        return this.deviceSn;
    }

    public int getDistance() {
        return this.distance;
    }

    public double getExamount() {
        return this.examount;
    }

    public int getExerciseTime() {
        return this.exerciseTime;
    }

    public int getIntensityLevel() {
        return this.intensityLevel;
    }

    public int getRunSteps() {
        return this.runSteps;
    }

    public int getSleepStatus() {
        return this.sleepStatus;
    }

    public int getUserNo() {
        return this.userNo;
    }

    public int getWalkSteps() {
        return this.walkSteps;
    }

    public void setBattery(int i) {
        this.battery = i;
    }

    public void setBroadcastId(String str) {
        this.broadcastId = str;
    }

    public void setCalories(double d) {
        this.calories = d;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceSn(String str) {
        this.deviceSn = str;
    }

    public void setDistance(double d) {
        this.distance = (int) d;
    }

    public void setExamount(double d) {
        this.examount = d;
    }

    public void setExerciseTime(int i) {
        this.exerciseTime = i;
    }

    public void setIntensityLevel(int i) {
        this.intensityLevel = i;
    }

    public void setRunSteps(int i) {
        this.runSteps = i;
    }

    public void setSleepStatus(int i) {
        this.sleepStatus = i;
    }

    public void setUserNo(int i) {
        this.userNo = i;
    }

    public void setWalkSteps(int i) {
        this.walkSteps = i;
    }

    public String toString() {
        return "PedometerData [deviceSn=" + this.deviceSn + ", broadcastId=" + this.broadcastId + ", date=" + this.date + ", deviceId=" + this.deviceId + ", userNo=" + this.userNo + ", walkSteps=" + this.walkSteps + ", runSteps=" + this.runSteps + ", examount=" + this.examount + ", calories=" + this.calories + ", exerciseTime=" + this.exerciseTime + ", distance=" + this.distance + ", battery=" + this.battery + ", sleepStatus=" + this.sleepStatus + ", intensityLevel=" + this.intensityLevel + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceSn);
        parcel.writeString(this.broadcastId);
        parcel.writeString(this.date);
        parcel.writeString(this.deviceId);
        parcel.writeInt(this.userNo);
        parcel.writeInt(this.walkSteps);
        parcel.writeInt(this.runSteps);
        parcel.writeDouble(this.examount);
        parcel.writeDouble(this.calories);
        parcel.writeInt(this.exerciseTime);
        parcel.writeInt(this.distance);
        parcel.writeInt(this.battery);
        parcel.writeInt(this.sleepStatus);
        parcel.writeInt(this.intensityLevel);
    }
}
