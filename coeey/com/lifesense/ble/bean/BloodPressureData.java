package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class BloodPressureData implements Parcelable {
    public static final String BODY_MOVEMENT_DURING_MEASUREMENT = "BODY MOVEMENT DURING MEASUREMENT";
    public static final Creator CREATOR = new C2241a();
    public static final String CUFF_FITS_PROPERLY = "CUFF FITS PROPERLY";
    public static final String CUFF_TOO_LOOSE = "CUFF TOO LOOSE";
    public static final String IMPROPER_MEASUREMENT_POSITION = "IMPROPER MEASUREMENT POSITION";
    public static final String IRREGULAR_PULSE_DETECTED = "IRREGULAR PULSE DETECTED";
    public static final String NO_BODY_MOVEMENT = "NO BODY MOVEMENT";
    public static final String NO_IRREGULAR_PULSE_DETECTED = "NO IRREGULAR PULSE DETECTED";
    public static final String PROPER_MEASUREMENT_POSITION = "PROPER MEASUREMENT POSITION";
    public static final String PULSE_RATE_EXCEEDS_UPPER_LIMIT = "PULSE RATE EXCEEDS UPPER LIMIT";
    public static final String PULSE_RATE_IS_LESS_THAN_LOWER_LIMIT = "PULSE RATE IS LESS THAN LOWER LIMIT";
    public static final String PULSE_RATE_IS_WITHIN_THE_RANGE = "PULSE RATE IS WITHIN THE RANGE";
    public static final String RESERVED_FOR_FUTURE_USE = "RESERVED FOR FUTURE USE";
    private int battery;
    private String bodyMovementDetectionFlag;
    private String broadcastId;
    private String cuffFitDetectionFlag;
    private String date;
    private String deviceId;
    private String deviceSelectedUnit;
    private String deviceSn;
    private float diastolic;
    private String irregularPulseDetectionFlag;
    private float meanArterialPressure;
    private String measurementPositionDetectionFlag;
    private float pulseRate;
    private String pulseRateRangeDetectionFlags;
    private float systolic;
    private int userId;
    private long utc;

    private BloodPressureData(Parcel parcel) {
        this.deviceId = parcel.readString();
        this.deviceSn = parcel.readString();
        this.broadcastId = parcel.readString();
        this.date = parcel.readString();
        this.userId = parcel.readInt();
        this.systolic = parcel.readFloat();
        this.diastolic = parcel.readFloat();
        this.pulseRate = parcel.readFloat();
        this.meanArterialPressure = parcel.readFloat();
        this.utc = parcel.readLong();
        this.deviceSelectedUnit = parcel.readString();
        this.battery = parcel.readInt();
        this.bodyMovementDetectionFlag = parcel.readString();
        this.cuffFitDetectionFlag = parcel.readString();
        this.irregularPulseDetectionFlag = parcel.readString();
        this.pulseRateRangeDetectionFlags = parcel.readString();
        this.measurementPositionDetectionFlag = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public int getBattery() {
        return this.battery;
    }

    public String getBodyMovementDetectionFlag() {
        return this.bodyMovementDetectionFlag;
    }

    public String getBroadcastId() {
        return this.broadcastId;
    }

    public String getCuffFitDetectionFlag() {
        return this.cuffFitDetectionFlag;
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

    public float getDiastolic() {
        return this.diastolic;
    }

    public String getIrregularPulseDetectionFlag() {
        return this.irregularPulseDetectionFlag;
    }

    public float getMeanArterialPressure() {
        return this.meanArterialPressure;
    }

    public String getMeasurementPositionDetectionFlag() {
        return this.measurementPositionDetectionFlag;
    }

    public float getPulseRate() {
        return this.pulseRate;
    }

    public String getPulseRateRangeDetectionFlags() {
        return this.pulseRateRangeDetectionFlags;
    }

    public float getSystolic() {
        return this.systolic;
    }

    public int getUserId() {
        return this.userId;
    }

    public long getUtc() {
        return this.utc;
    }

    public void setBattery(int i) {
        this.battery = i;
    }

    public void setBodyMovementDetectionFlag(String str) {
        this.bodyMovementDetectionFlag = str;
    }

    public void setBroadcastId(String str) {
        this.broadcastId = str;
    }

    public void setCuffFitDetectionFlag(String str) {
        this.cuffFitDetectionFlag = str;
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

    public void setDiastolic(float f) {
        this.diastolic = f;
    }

    public void setIrregularPulseDetectionFlag(String str) {
        this.irregularPulseDetectionFlag = str;
    }

    public void setMeanArterialPressure(float f) {
        this.meanArterialPressure = f;
    }

    public void setMeasurementPositionDetectionFlag(String str) {
        this.measurementPositionDetectionFlag = str;
    }

    public void setPulseRate(float f) {
        this.pulseRate = f;
    }

    public void setPulseRateRangeDetectionFlags(String str) {
        this.pulseRateRangeDetectionFlags = str;
    }

    public void setSystolic(float f) {
        this.systolic = f;
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public void setUtc(long j) {
        this.utc = j;
    }

    public String toString() {
        return "BloodPressureData [deviceId=" + this.deviceId + ", deviceSn=" + this.deviceSn + ", broadcastId=" + this.broadcastId + ", date=" + this.date + ", systolic=" + this.systolic + ", diastolic=" + this.diastolic + ", meanArterialPressure=" + this.meanArterialPressure + ", utc=" + this.utc + ", pulseRate=" + this.pulseRate + ", userId=" + this.userId + ", bodyMovementDetectionFlag=" + this.bodyMovementDetectionFlag + ", cuffFitDetectionFlag=" + this.cuffFitDetectionFlag + ", irregularPulseDetectionFlag=" + this.irregularPulseDetectionFlag + ", pulseRateRangeDetectionFlags=" + this.pulseRateRangeDetectionFlags + ", measurementPositionDetectionFlag=" + this.measurementPositionDetectionFlag + ", deviceSelectedUnit=" + this.deviceSelectedUnit + ", battery=" + this.battery + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceId);
        parcel.writeString(this.deviceSn);
        parcel.writeString(this.broadcastId);
        parcel.writeString(this.date);
        parcel.writeInt(this.userId);
        parcel.writeFloat(this.systolic);
        parcel.writeFloat(this.diastolic);
        parcel.writeFloat(this.pulseRate);
        parcel.writeFloat(this.meanArterialPressure);
        parcel.writeLong(this.utc);
        parcel.writeString(this.deviceSelectedUnit);
        parcel.writeInt(this.battery);
        parcel.writeString(this.bodyMovementDetectionFlag);
        parcel.writeString(this.cuffFitDetectionFlag);
        parcel.writeString(this.irregularPulseDetectionFlag);
        parcel.writeString(this.pulseRateRangeDetectionFlags);
        parcel.writeString(this.measurementPositionDetectionFlag);
    }
}
