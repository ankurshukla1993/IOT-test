package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class LsDeviceInfo implements Parcelable {
    public static final Creator CREATOR = new C2244d();
    private String broadcastID;
    private String deviceId;
    private String deviceName;
    private String deviceSn;
    private String deviceType;
    private int deviceUserNumber;
    private String firmwareVersion;
    private String hardwareVersion;
    private String manufactureName;
    private int maxUserQuantity;
    private String modelNumber;
    private int pairSingSignature;
    private int pairStatus;
    private String password;
    private String protocolType;
    private String softwareVersion;
    private int supportDownloadInfoFeature;
    private String systemId;

    private LsDeviceInfo(Parcel parcel) {
        this.deviceType = parcel.readString();
        this.password = parcel.readString();
        this.broadcastID = parcel.readString();
        this.deviceName = parcel.readString();
        this.deviceId = parcel.readString();
        this.deviceSn = parcel.readString();
        this.modelNumber = parcel.readString();
        this.softwareVersion = parcel.readString();
        this.hardwareVersion = parcel.readString();
        this.firmwareVersion = parcel.readString();
        this.manufactureName = parcel.readString();
        this.systemId = parcel.readString();
        this.deviceUserNumber = parcel.readInt();
        this.pairStatus = parcel.readInt();
        this.maxUserQuantity = parcel.readInt();
        this.pairSingSignature = parcel.readInt();
        this.supportDownloadInfoFeature = parcel.readInt();
        this.protocolType = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getBroadcastID() {
        return this.broadcastID;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceSn() {
        return this.deviceSn;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public int getDeviceUserNumber() {
        return this.deviceUserNumber;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public String getManufactureName() {
        return this.manufactureName;
    }

    public int getMaxUserQuantity() {
        return this.maxUserQuantity;
    }

    public String getModelNumber() {
        return this.modelNumber;
    }

    public int getPairSingSignature() {
        return this.pairSingSignature;
    }

    public int getPairStatus() {
        return this.pairStatus;
    }

    public String getPassword() {
        return this.password;
    }

    public String getProtocolType() {
        return this.protocolType;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public int getSupportDownloadInfoFeature() {
        return this.supportDownloadInfoFeature;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public void setBroadcastID(String str) {
        this.broadcastID = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setDeviceSn(String str) {
        this.deviceSn = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setDeviceUserNumber(int i) {
        this.deviceUserNumber = i;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
    }

    public void setHardwareVersion(String str) {
        this.hardwareVersion = str;
    }

    public void setManufactureName(String str) {
        this.manufactureName = str;
    }

    public void setMaxUserQuantity(int i) {
        this.maxUserQuantity = i;
    }

    public void setModelNumber(String str) {
        this.modelNumber = str;
    }

    public void setPairSingSignature(int i) {
        this.pairSingSignature = i;
    }

    public void setPairStatus(int i) {
        this.pairStatus = i;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setProtocolType(String str) {
        this.protocolType = str;
    }

    public void setSoftwareVersion(String str) {
        this.softwareVersion = str;
    }

    public void setSupportDownloadInfoFeature(int i) {
        this.supportDownloadInfoFeature = i;
    }

    public void setSystemId(String str) {
        this.systemId = str;
    }

    public String toString() {
        return "LsDeviceInfo [deviceType=" + this.deviceType + ", password=" + this.password + ", broadcastID=" + this.broadcastID + ", deviceName=" + this.deviceName + ", deviceId=" + this.deviceId + ", deviceSn=" + this.deviceSn + ", modelNumber=" + this.modelNumber + ", softwareVersion=" + this.softwareVersion + ", hardwareVersion=" + this.hardwareVersion + ", firmwareVersion=" + this.firmwareVersion + ", manufactureName=" + this.manufactureName + ", systemId=" + this.systemId + ", deviceUserNumber=" + this.deviceUserNumber + ", pairStatus=" + this.pairStatus + ", maxUserQuantity=" + this.maxUserQuantity + ", pairSingSignature=" + this.pairSingSignature + ", supportDownloadInfoFeature=" + this.supportDownloadInfoFeature + ", protocolType=" + this.protocolType + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceType);
        parcel.writeString(this.password);
        parcel.writeString(this.broadcastID);
        parcel.writeString(this.deviceName);
        parcel.writeString(this.deviceId);
        parcel.writeString(this.deviceSn);
        parcel.writeString(this.modelNumber);
        parcel.writeString(this.softwareVersion);
        parcel.writeString(this.hardwareVersion);
        parcel.writeString(this.firmwareVersion);
        parcel.writeString(this.manufactureName);
        parcel.writeString(this.systemId);
        parcel.writeInt(this.deviceUserNumber);
        parcel.writeInt(this.pairStatus);
        parcel.writeInt(this.maxUserQuantity);
        parcel.writeInt(this.pairSingSignature);
        parcel.writeInt(this.supportDownloadInfoFeature);
        parcel.writeString(this.protocolType);
    }
}
