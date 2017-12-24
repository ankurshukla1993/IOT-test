package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class WeightData_A3 implements Parcelable {
    public static final Creator CREATOR = new C2252l();
    public static final String IMPEDANCE_STATUS_BAREFOOT = "BAREFOOT";
    public static final String IMPEDANCE_STATUS_ERROR = "ERROR";
    public static final String IMPEDANCE_STATUS_FINISH = "FINISH";
    public static final String IMPEDANCE_STATUS_IDLE = "IDLE";
    public static final String IMPEDANCE_STATUS_PROCESSING = "PROCESSING";
    public static final String IMPEDANCE_STATUS_SHOES = "SHOES";
    public static final String WEIGHT_STATUS_WEIGHT_STABLE = "WEIGHT STABLE";
    public static final String WEIGHT_STATUS_WEIGHT_UNSTABLE = "WEIGHT UNSTABLE";
    private String accuracyStatus;
    private boolean appendMeasurement;
    private float basalMetabolism;
    private int battery;
    private float bodyFatRatio;
    private float bodyWaterRatio;
    private float boneDensity;
    private String broadcastId;
    private String date;
    private String deviceId;
    private String deviceSelectedUnit;
    private String deviceSn;
    private double impedance;
    private String impedanceStatus;
    private float muscleMassRatio;
    private int userId;
    private long utc;
    private float visceralFatLevel;
    private double weight;
    private double weightDifferenceValue;
    private String weightStatus;

    public WeightData_A3() {
        this.deviceSelectedUnit = "kg";
    }

    private WeightData_A3(Parcel parcel) {
        this.deviceSelectedUnit = "kg";
        this.deviceId = parcel.readString();
        this.deviceSn = parcel.readString();
        this.broadcastId = parcel.readString();
        this.deviceSelectedUnit = parcel.readString();
        this.weight = parcel.readDouble();
        this.utc = parcel.readLong();
        this.date = parcel.readString();
        this.weightDifferenceValue = parcel.readDouble();
        this.impedance = parcel.readDouble();
        this.userId = parcel.readInt();
        this.weightStatus = parcel.readString();
        this.impedanceStatus = parcel.readString();
        this.appendMeasurement = parcel.readByte() != (byte) 0;
        this.accuracyStatus = parcel.readString();
        this.basalMetabolism = parcel.readFloat();
        this.bodyFatRatio = parcel.readFloat();
        this.bodyWaterRatio = parcel.readFloat();
        this.visceralFatLevel = parcel.readFloat();
        this.muscleMassRatio = parcel.readFloat();
        this.boneDensity = parcel.readFloat();
        this.battery = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public String getAccuracyStatus() {
        return this.accuracyStatus;
    }

    public float getBasalMetabolism() {
        return this.basalMetabolism;
    }

    public int getBattery() {
        return this.battery;
    }

    public float getBodyFatRatio() {
        return this.bodyFatRatio;
    }

    public float getBodyWaterRatio() {
        return this.bodyWaterRatio;
    }

    public float getBoneDensity() {
        return this.boneDensity;
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

    public String getDeviceSelectedUnit() {
        return this.deviceSelectedUnit;
    }

    public String getDeviceSn() {
        return this.deviceSn;
    }

    public double getImpedance() {
        return this.impedance;
    }

    public String getImpedanceStatus() {
        return this.impedanceStatus;
    }

    public float getMuscleMassRatio() {
        return this.muscleMassRatio;
    }

    public int getUserId() {
        return this.userId;
    }

    public long getUtc() {
        return this.utc;
    }

    public float getVisceralFatLevel() {
        return this.visceralFatLevel;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getWeightDifferenceValue() {
        return this.weightDifferenceValue;
    }

    public String getWeightStatus() {
        return this.weightStatus;
    }

    public boolean isAppendMeasurement() {
        return this.appendMeasurement;
    }

    public void setAccuracyStatus(String str) {
        this.accuracyStatus = str;
    }

    public void setAppendMeasurement(boolean z) {
        this.appendMeasurement = z;
    }

    public void setBasalMetabolism(float f) {
        this.basalMetabolism = f;
    }

    public void setBattery(int i) {
        this.battery = i;
    }

    public void setBodyFatRatio(float f) {
        this.bodyFatRatio = f;
    }

    public void setBodyWaterRatio(float f) {
        this.bodyWaterRatio = f;
    }

    public void setBoneDensity(float f) {
        this.boneDensity = f;
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

    public void setDeviceSelectedUnit(String str) {
        this.deviceSelectedUnit = str;
    }

    public void setDeviceSn(String str) {
        this.deviceSn = str;
    }

    public void setImpedance(double d) {
        this.impedance = d;
    }

    public void setImpedanceStatus(String str) {
        this.impedanceStatus = str;
    }

    public void setMuscleMassRatio(float f) {
        this.muscleMassRatio = f;
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public void setUtc(long j) {
        this.utc = j;
    }

    public void setVisceralFatLevel(float f) {
        this.visceralFatLevel = f;
    }

    public void setWeight(double d) {
        this.weight = d;
    }

    public void setWeightDifferenceValue(double d) {
        this.weightDifferenceValue = d;
    }

    public void setWeightStatus(String str) {
        this.weightStatus = str;
    }

    public String toString() {
        return "WeightData_A3 [deviceId=" + this.deviceId + ", deviceSn=" + this.deviceSn + ", broadcastId=" + this.broadcastId + ", deviceSelectedUnit=" + this.deviceSelectedUnit + ", weight=" + this.weight + ", utc=" + this.utc + ", date=" + this.date + ", weightDifferenceValue=" + this.weightDifferenceValue + ", impedance=" + this.impedance + ", userId=" + this.userId + ", weightStatus=" + this.weightStatus + ", impedanceStatus=" + this.impedanceStatus + ", appendMeasurement=" + this.appendMeasurement + ", accuracyStatus=" + this.accuracyStatus + ", basalMetabolism=" + this.basalMetabolism + ", bodyFatRatio=" + this.bodyFatRatio + ", bodyWaterRatio=" + this.bodyWaterRatio + ", visceralFatLevel=" + this.visceralFatLevel + ", muscleMassRatio=" + this.muscleMassRatio + ", boneDensity=" + this.boneDensity + ", battery=" + this.battery + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceId);
        parcel.writeString(this.deviceSn);
        parcel.writeString(this.broadcastId);
        parcel.writeString(this.deviceSelectedUnit);
        parcel.writeDouble(this.weight);
        parcel.writeLong(this.utc);
        parcel.writeString(this.date);
        parcel.writeDouble(this.weightDifferenceValue);
        parcel.writeDouble(this.impedance);
        parcel.writeInt(this.userId);
        parcel.writeString(this.weightStatus);
        parcel.writeString(this.impedanceStatus);
        parcel.writeByte((byte) (this.appendMeasurement ? 1 : 0));
        parcel.writeString(this.accuracyStatus);
        parcel.writeFloat(this.basalMetabolism);
        parcel.writeFloat(this.bodyFatRatio);
        parcel.writeFloat(this.bodyWaterRatio);
        parcel.writeFloat(this.visceralFatLevel);
        parcel.writeFloat(this.muscleMassRatio);
        parcel.writeFloat(this.boneDensity);
        parcel.writeInt(this.battery);
    }
}
