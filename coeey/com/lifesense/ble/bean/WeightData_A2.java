package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public class WeightData_A2 implements Parcelable {
    public static final Creator CREATOR = new C2251k();
    public static int MEASUREMENT_STATUS_IMPEDANCE_STATUS_BAREFOOT = 3;
    public static int MEASUREMENT_STATUS_IMPEDANCE_STATUS_ERROR = 5;
    public static int MEASUREMENT_STATUS_IMPEDANCE_STATUS_FINISH = 4;
    public static int MEASUREMENT_STATUS_IMPEDANCE_STATUS_IDLE = 0;
    public static int MEASUREMENT_STATUS_IMPEDANCE_STATUS_PROCESSING = 1;
    public static int MEASUREMENT_STATUS_IMPEDANCE_STATUS_SHOES = 2;
    public static int MEASUREMENT_STATUS_WEIGHT_STATUS_WEIGHT_STABLE = 1;
    public static int MEASUREMENT_STATUS_WEIGHT_STATUS_WEIGHT_UNSTABLE = 0;
    private float BasalMetabolism;
    private byte Battery;
    private float BodyFatRatio;
    private float BodyWaterRatio;
    private float BoneDensity;
    private float MuscleMassRatio;
    public byte[] UTCbytes;
    private float VisceralFatLevel;
    private String broadcastId;
    private String date;
    private String deviceId;
    private String deviceSelectedUnit;
    private String deviceSn;
    private int flag;
    public boolean hasAppendMeasurement;
    private int impedanceStatus;
    private double pbf;
    private double resistance_1;
    private double resistance_2;
    private int userNo;
    private double voltageData;
    private double weight;
    private int weightStatus;

    public WeightData_A2() {
        this.UTCbytes = new byte[4];
        this.deviceSelectedUnit = "kg";
        this.flag = 0;
    }

    private WeightData_A2(Parcel parcel) {
        boolean z = false;
        this.UTCbytes = new byte[4];
        this.deviceSelectedUnit = "kg";
        this.flag = 0;
        this.deviceSn = parcel.readString();
        this.broadcastId = parcel.readString();
        this.date = parcel.readString();
        this.UTCbytes = new byte[parcel.readInt()];
        parcel.readByteArray(this.UTCbytes);
        this.deviceId = parcel.readString();
        this.userNo = parcel.readInt();
        this.weight = parcel.readDouble();
        this.pbf = parcel.readDouble();
        this.resistance_1 = parcel.readDouble();
        this.resistance_2 = parcel.readDouble();
        this.deviceSelectedUnit = parcel.readString();
        this.flag = parcel.readInt();
        this.BasalMetabolism = parcel.readFloat();
        this.BodyFatRatio = parcel.readFloat();
        this.BodyWaterRatio = parcel.readFloat();
        this.VisceralFatLevel = parcel.readFloat();
        this.MuscleMassRatio = parcel.readFloat();
        this.BoneDensity = parcel.readFloat();
        this.Battery = parcel.readByte();
        this.weightStatus = parcel.readInt();
        this.impedanceStatus = parcel.readInt();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        }
        this.hasAppendMeasurement = z;
    }

    public double calculateLBValueWithKGValue(double d) {
        return 0.0d;
    }

    public double calculateSTValueWithKGValue(double d) {
        return 0.0d;
    }

    public int describeContents() {
        return 0;
    }

    public float getBasalMetabolism() {
        return this.BasalMetabolism;
    }

    public byte getBattery() {
        return this.Battery;
    }

    public float getBodyFatRatio() {
        return this.BodyFatRatio;
    }

    public float getBodyWaterRatio() {
        return this.BodyWaterRatio;
    }

    public float getBoneDensity() {
        return this.BoneDensity;
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

    public int getFlag() {
        return this.flag;
    }

    public int getImpedanceStatus() {
        return this.impedanceStatus;
    }

    public float getMuscleMassRatio() {
        return this.MuscleMassRatio;
    }

    public double getPbf() {
        return this.pbf;
    }

    public double getResistance_1() {
        return this.resistance_1;
    }

    public double getResistance_2() {
        return this.resistance_2;
    }

    public byte[] getUTCbytes() {
        return this.UTCbytes;
    }

    public int getUserNo() {
        return this.userNo;
    }

    public float getVisceralFatLevel() {
        return this.VisceralFatLevel;
    }

    public double getVoltageData() {
        return this.voltageData;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getWeightStatus() {
        return this.weightStatus;
    }

    public boolean isHasAppendMeasurement() {
        return this.hasAppendMeasurement;
    }

    public void setBasalMetabolism(float f) {
        this.BasalMetabolism = f;
    }

    public void setBattery(byte b) {
        this.Battery = b;
    }

    public void setBodyFatRatio(float f) {
        this.BodyFatRatio = f;
    }

    public void setBodyWaterRatio(float f) {
        this.BodyWaterRatio = f;
    }

    public void setBoneDensity(float f) {
        this.BoneDensity = f;
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

    public void setFlag(int i) {
        this.flag = i;
    }

    public void setHasAppendMeasurement(boolean z) {
        this.hasAppendMeasurement = z;
    }

    public void setImpedanceStatus(int i) {
        this.impedanceStatus = i;
    }

    public void setMuscleMassRatio(float f) {
        this.MuscleMassRatio = f;
    }

    public void setPbf(double d) {
        this.pbf = d;
    }

    public void setResistance_1(double d) {
        this.resistance_1 = d;
    }

    public void setResistance_2(double d) {
        this.resistance_2 = d;
    }

    public void setUTCbytes(byte[] bArr) {
        this.UTCbytes = bArr;
    }

    public void setUserNo(int i) {
        this.userNo = i;
    }

    public void setVisceralFatLevel(float f) {
        this.VisceralFatLevel = f;
    }

    public void setVoltageData(double d) {
        this.voltageData = d;
    }

    public void setWeight(double d) {
        this.weight = d;
    }

    public void setWeightStatus(int i) {
        this.weightStatus = i;
    }

    public String toString() {
        return "WeightData_A2 [deviceSn=" + this.deviceSn + ", broadcastId=" + this.broadcastId + ", date=" + this.date + ", UTCbytes=" + Arrays.toString(this.UTCbytes) + ", deviceId=" + this.deviceId + ", userNo=" + this.userNo + ", weight=" + this.weight + ", pbf=" + this.pbf + ", resistance_1=" + this.resistance_1 + ", resistance_2=" + this.resistance_2 + ", deviceSelectedUnit=" + this.deviceSelectedUnit + ", flag=" + this.flag + ", BasalMetabolism=" + this.BasalMetabolism + ", BodyFatRatio=" + this.BodyFatRatio + ", BodyWaterRatio=" + this.BodyWaterRatio + ", VisceralFatLevel=" + this.VisceralFatLevel + ", MuscleMassRatio=" + this.MuscleMassRatio + ", BoneDensity=" + this.BoneDensity + ", Battery=" + this.Battery + ", weightStatus=" + this.weightStatus + ", impedanceStatus=" + this.impedanceStatus + ", hasAppendMeasurement=" + this.hasAppendMeasurement + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceSn);
        parcel.writeString(this.broadcastId);
        parcel.writeString(this.date);
        parcel.writeInt(this.UTCbytes.length);
        parcel.writeByteArray(this.UTCbytes);
        parcel.writeString(this.deviceId);
        parcel.writeInt(this.userNo);
        parcel.writeDouble(this.weight);
        parcel.writeDouble(this.pbf);
        parcel.writeDouble(this.resistance_1);
        parcel.writeDouble(this.resistance_2);
        parcel.writeString(this.deviceSelectedUnit);
        parcel.writeInt(this.flag);
        parcel.writeFloat(this.BasalMetabolism);
        parcel.writeFloat(this.BodyFatRatio);
        parcel.writeFloat(this.BodyWaterRatio);
        parcel.writeFloat(this.VisceralFatLevel);
        parcel.writeFloat(this.MuscleMassRatio);
        parcel.writeFloat(this.BoneDensity);
        parcel.writeByte(this.Battery);
        parcel.writeInt(this.weightStatus);
        parcel.writeInt(this.impedanceStatus);
        parcel.writeByte((byte) (this.hasAppendMeasurement ? 1 : 0));
    }
}
