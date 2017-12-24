package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class HeightData implements Parcelable {
    public static final Creator CREATOR = new C2243c();
    public static int HEIGHT_LOCKED = 1;
    public static int HEIGHT_UNLOCKED = 0;
    private Integer battery;
    private String broadcastId;
    private String date;
    private String deviceId;
    private String deviceSn;
    private double height;
    public int heightStatus;
    private String unit;
    private int userNo;

    private HeightData(Parcel parcel) {
        this.deviceSn = parcel.readString();
        this.broadcastId = parcel.readString();
        this.date = parcel.readString();
        this.deviceId = parcel.readString();
        this.userNo = parcel.readInt();
        this.height = parcel.readDouble();
        this.unit = parcel.readString();
        this.battery = Integer.valueOf(parcel.readInt());
        this.heightStatus = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public Integer getBattery() {
        return this.battery;
    }

    public String getBroadcastId() {
        return this.broadcastId;
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

    public double getHeight() {
        return this.height;
    }

    public String getUnit() {
        return this.unit;
    }

    public int getUserNo() {
        return this.userNo;
    }

    public void setBattery(int i) {
        this.battery = Integer.valueOf(i);
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

    public void setDeviceSn(String str) {
        this.deviceSn = str;
    }

    public void setHeight(double d) {
        this.height = d;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public void setUserNo(int i) {
        this.userNo = i;
    }

    public String toString() {
        return "HeightData [deviceSn=" + this.deviceSn + ", broadcastId=" + this.broadcastId + ", date=" + this.date + ", deviceId=" + this.deviceId + ", userNo=" + this.userNo + ", height=" + this.height + ", unit=" + this.unit + ", battery=" + this.battery + ", heightStatus=" + this.heightStatus + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceSn);
        parcel.writeString(this.broadcastId);
        parcel.writeString(this.date);
        parcel.writeString(this.deviceId);
        parcel.writeInt(this.userNo);
        parcel.writeDouble(this.height);
        parcel.writeString(this.unit);
        parcel.writeInt(this.battery.intValue());
        parcel.writeInt(this.heightStatus);
    }
}
