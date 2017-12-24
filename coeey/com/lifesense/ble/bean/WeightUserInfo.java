package com.lifesense.ble.bean;

import com.google.android.flexbox.FlexItem;
import com.lifesense.ble.commom.C2255a;

public class WeightUserInfo {
    private int age;
    private int athleteActivityLevel;
    private String deviceId;
    private byte flags = (byte) -33;
    private float goalWeight;
    private float height = FlexItem.FLEX_SHRINK_DEFAULT;
    private boolean isAthlete;
    private String memberId;
    private int productUserNumber;
    private SexType sex;
    private UnitType unit;
    private float waistline;

    private int getSexValue(SexType sexType, boolean z) {
        return sexType == SexType.MALE ? z ? 3 : 1 : sexType == SexType.FEMALE ? z ? 4 : 2 : 1;
    }

    public static UnitType getUnitTypeByUnitValue(int i) {
        return i == 1 ? UnitType.UNIT_LB : i == 2 ? UnitType.UNIT_ST : UnitType.UNIT_KG;
    }

    private int getUnitValueByUnitType(UnitType unitType) {
        return unitType == UnitType.UNIT_LB ? 1 : unitType == UnitType.UNIT_ST ? 2 : 0;
    }

    public int getAge() {
        return this.age;
    }

    public int getAthleteActivityLevel() {
        return this.athleteActivityLevel;
    }

    public byte[] getBytes() {
        byte[] bArr = new byte[15];
        bArr[0] = (byte) 81;
        bArr[1] = (byte) -33;
        bArr[2] = (byte) this.productUserNumber;
        bArr[3] = (byte) getSexValue(this.sex, this.isAthlete);
        bArr[4] = (byte) this.age;
        bArr[5] = C2255a.m1899c(this.height)[0];
        bArr[6] = C2255a.m1899c(this.height)[1];
        bArr[7] = (byte) this.athleteActivityLevel;
        bArr[8] = (byte) getUnitValueByUnitType(this.unit);
        bArr[9] = C2255a.m1894b(this.goalWeight)[0];
        bArr[10] = C2255a.m1894b(this.goalWeight)[1];
        bArr[11] = C2255a.m1894b(this.goalWeight)[2];
        bArr[12] = C2255a.m1894b(this.goalWeight)[3];
        return bArr;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public byte getFlags() {
        return this.flags;
    }

    public float getGoalWeight() {
        return this.goalWeight;
    }

    public float getHeight() {
        return this.height;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public int getProductUserNumber() {
        return this.productUserNumber;
    }

    public SexType getSex() {
        return this.sex;
    }

    public UnitType getUnit() {
        return this.unit;
    }

    public float getWaistline() {
        return this.waistline;
    }

    public boolean isAthlete() {
        return this.isAthlete;
    }

    public void setAge(int i) {
        this.age = i;
    }

    public void setAthlete(boolean z) {
        this.isAthlete = z;
    }

    public void setAthleteActivityLevel(int i) {
        this.athleteActivityLevel = i;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setFlags(byte b) {
        this.flags = b;
    }

    public void setGoalWeight(float f) {
        this.goalWeight = f;
    }

    public void setHeight(float f) {
        this.height = f;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }

    public void setProductUserNumber(int i) {
        this.productUserNumber = i;
    }

    public void setSex(SexType sexType) {
        this.sex = sexType;
    }

    public void setUnit(UnitType unitType) {
        this.unit = unitType;
    }

    public void setWaistline(float f) {
        this.waistline = f;
    }

    public String toString() {
        return "WeightUserInfo [deviceId=" + this.deviceId + ", memberId=" + this.memberId + ", flags=" + this.flags + ", productUserNumber=" + this.productUserNumber + ", sex=" + this.sex + ", isAthlete=" + this.isAthlete + ", age=" + this.age + ", height=" + this.height + ", athleteActivityLevel=" + this.athleteActivityLevel + ", unit=" + this.unit + ", goalWeight=" + this.goalWeight + ", waistline=" + this.waistline + "]";
    }
}
