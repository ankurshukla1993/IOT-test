package com.ihealth.communication.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class IDPS implements Parcelable {
    private String f2095a;
    private String f2096b;
    private String f2097c;
    private String f2098d;
    private String f2099e;
    private String f2100f;
    private String f2101g;
    private String f2102h;
    private String f2103i;
    private String f2104j;
    private String f2105k;

    public int describeContents() {
        return 0;
    }

    public String getAccessoryFirmwareVersion() {
        return this.f2097c;
    }

    public String getAccessoryHardwareVersion() {
        return this.f2098d;
    }

    public String getAccessoryManufaturer() {
        return this.f2099e;
    }

    public String getAccessoryModelNumber() {
        return this.f2100f;
    }

    public String getAccessoryName() {
        return this.f2096b;
    }

    public String getAccessorySerialNumber() {
        return this.f2101g;
    }

    public String getDeviceIP() {
        return this.f2103i;
    }

    public String getDeviceMac() {
        return this.f2104j;
    }

    public String getDeviceName() {
        return this.f2102h;
    }

    public String getDeviceType() {
        return this.f2105k;
    }

    public String getProtoclString() {
        return this.f2095a;
    }

    public void setAccessoryFirmwareVersion(String accessoryFirmwareVersion) {
        this.f2097c = accessoryFirmwareVersion;
    }

    public void setAccessoryHardwareVersion(String accessoryHardwareVersion) {
        this.f2098d = accessoryHardwareVersion;
    }

    public void setAccessoryManufaturer(String accessoryManufaturer) {
        this.f2099e = accessoryManufaturer;
    }

    public void setAccessoryModelNumber(String accessoryModelNumber) {
        this.f2100f = accessoryModelNumber;
    }

    public void setAccessoryName(String accessoryName) {
        this.f2096b = accessoryName;
    }

    public void setAccessorySerialNumber(String accessorySerialNumber) {
        this.f2101g = accessorySerialNumber;
    }

    public void setDeviceIP(String deviceIP) {
        this.f2103i = deviceIP;
    }

    public void setDeviceMac(String deviceMac) {
        this.f2104j = deviceMac;
    }

    public void setDeviceName(String deviceName) {
        this.f2102h = deviceName;
    }

    public void setDeviceType(String deviceType) {
        this.f2105k = deviceType;
    }

    public void setProtoclString(String protoclString) {
        this.f2095a = protoclString;
    }

    public void writeToParcel(Parcel dest, int flags) {
    }
}
