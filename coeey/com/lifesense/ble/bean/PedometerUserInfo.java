package com.lifesense.ble.bean;

import com.google.android.flexbox.FlexItem;
import com.google.common.base.Ascii;
import com.lifesense.ble.commom.C2255a;

public class PedometerUserInfo {
    private String deviceId;
    private float height = FlexItem.FLEX_SHRINK_DEFAULT;
    private HourSystem hourSystem;
    private LengthUnit lengthUnit;
    private String memberId;
    private byte productUserNumber = (byte) 0;
    private float stride = FlexItem.FLEX_SHRINK_DEFAULT;
    private byte unit = (byte) 1;
    private int weekStart = 1;
    private float weekTargetCalories = FlexItem.FLEX_SHRINK_DEFAULT;
    private int weekTargetDistance = 1;
    private float weekTargetExerciseAmount = FlexItem.FLEX_SHRINK_DEFAULT;
    private int weekTargetSteps = 1;
    private float weight = FlexItem.FLEX_SHRINK_DEFAULT;
    private String weightUnit = "kg";

    private byte[] getCurrentStateBytes() {
        byte[] bArr = new byte[18];
        if (this.weightUnit.equalsIgnoreCase("kg")) {
            this.unit = (byte) 0;
        } else if (this.weightUnit.equalsIgnoreCase("lb")) {
            this.unit = (byte) 1;
        } else if (this.weightUnit.equalsIgnoreCase("st")) {
            this.unit = (byte) 2;
        } else {
            this.unit = (byte) 0;
        }
        bArr[0] = (byte) 0;
        bArr[1] = Byte.MAX_VALUE;
        bArr[2] = this.unit;
        bArr[3] = C2255a.m1884b(this.productUserNumber);
        bArr[4] = C2255a.m1881a(this.weight)[0];
        bArr[5] = C2255a.m1881a(this.weight)[1];
        bArr[6] = C2255a.m1881a(this.weight)[2];
        bArr[7] = C2255a.m1881a(this.weight)[3];
        bArr[8] = (byte) 0;
        bArr[9] = (byte) 0;
        bArr[10] = C2255a.m1899c(this.height)[0];
        bArr[11] = C2255a.m1899c(this.height)[1];
        bArr[12] = (byte) 0;
        bArr[13] = (byte) 0;
        bArr[14] = C2255a.m1899c(this.stride)[0];
        bArr[15] = C2255a.m1899c(this.stride)[1];
        bArr[16] = (byte) 0;
        bArr[17] = (byte) 0;
        return bArr;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public float getHeight() {
        return this.height;
    }

    public HourSystem getHourSystem() {
        return this.hourSystem;
    }

    public LengthUnit getLengthUnit() {
        return this.lengthUnit;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public byte getProductUserNumber() {
        return this.productUserNumber;
    }

    public float getStride() {
        return this.stride;
    }

    public byte getUnit() {
        return this.unit;
    }

    public int getWeekStart() {
        return this.weekStart;
    }

    public byte[] getWeekTargetBytes() {
        return new byte[]{(byte) 8, Ascii.US, C2255a.m1884b(this.productUserNumber), C2255a.m1883a((long) this.weekTargetSteps)[0], C2255a.m1883a((long) this.weekTargetSteps)[1], C2255a.m1883a((long) this.weekTargetSteps)[2], C2255a.m1883a((long) this.weekTargetSteps)[3], C2255a.m1894b(this.weekTargetCalories)[0], C2255a.m1894b(this.weekTargetCalories)[1], C2255a.m1894b(this.weekTargetCalories)[2], C2255a.m1894b(this.weekTargetCalories)[3], C2255a.m1894b((float) this.weekTargetDistance)[0], C2255a.m1894b((float) this.weekTargetDistance)[1], C2255a.m1894b((float) this.weekTargetDistance)[2], C2255a.m1894b((float) this.weekTargetDistance)[3], C2255a.m1894b(this.weekTargetExerciseAmount)[0], C2255a.m1894b(this.weekTargetExerciseAmount)[1], C2255a.m1894b(this.weekTargetExerciseAmount)[2], C2255a.m1894b(this.weekTargetExerciseAmount)[3]};
    }

    public float getWeekTargetCalories() {
        return this.weekTargetCalories;
    }

    public int getWeekTargetDistance() {
        return this.weekTargetDistance;
    }

    public float getWeekTargetExerciseAmount() {
        return this.weekTargetExerciseAmount;
    }

    public int getWeekTargetSteps() {
        return this.weekTargetSteps;
    }

    public float getWeight() {
        return this.weight;
    }

    public String getWeightUnit() {
        return this.weightUnit;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setHeight(double d) {
        if (d >= 0.0d) {
            this.height = (float) d;
        }
        if (d < 0.0d) {
            this.height = (float) (-d);
        }
    }

    public void setHourSystem(HourSystem hourSystem) {
        this.hourSystem = hourSystem;
    }

    public void setLengthUnit(LengthUnit lengthUnit) {
        this.lengthUnit = lengthUnit;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }

    public void setProductUserNumber(byte b) {
        if (b >= (byte) 0) {
            this.productUserNumber = b;
        }
        if (b < (byte) 0) {
            this.productUserNumber = (byte) (-b);
        }
    }

    public void setStride(double d) {
        if (d >= 0.0d) {
            this.stride = (float) d;
        }
        if (d < 0.0d) {
            this.stride = (float) (-d);
        }
    }

    public void setUnit(byte b) {
        if (b == (byte) 0 || b == (byte) 1 || b == (byte) 2) {
            this.unit = b;
        } else {
            this.unit = (byte) 0;
        }
    }

    public void setWeekStart(int i) {
        this.weekStart = i;
    }

    public void setWeekTargetCalories(double d) {
        if (d >= 0.0d) {
            this.weekTargetCalories = (float) d;
        }
        if (d < 0.0d) {
            this.weekTargetCalories = (float) (-d);
        }
    }

    public void setWeekTargetDistance(int i) {
        if (i >= 0) {
            this.weekTargetDistance = i;
        }
        if (i < 0) {
            this.weekTargetDistance = -i;
        }
    }

    public void setWeekTargetExerciseAmount(double d) {
        if (d >= 0.0d) {
            this.weekTargetExerciseAmount = (float) d;
        }
        if (d < 0.0d) {
            this.weekTargetExerciseAmount = (float) d;
        }
    }

    public void setWeekTargetSteps(int i) {
        if (i >= 0) {
            this.weekTargetSteps = i;
        }
        if (i < 0) {
            this.weekTargetSteps = -i;
        }
    }

    public void setWeight(double d) {
        if (d >= 0.0d) {
            this.weight = (float) d;
        }
        if (d < 0.0d) {
            this.weight = (float) (-d);
        }
    }

    public void setWeightUnit(String str) {
        if (str.equalsIgnoreCase("kg")) {
            this.weightUnit = str;
            this.unit = (byte) 0;
        }
        if (str.equalsIgnoreCase("lb")) {
            this.weightUnit = str;
            this.unit = (byte) 1;
        }
        if (str.equalsIgnoreCase("st")) {
            this.weightUnit = str;
            this.unit = (byte) 2;
            return;
        }
        this.weightUnit = "kg";
        this.unit = (byte) 0;
    }

    public String toString() {
        return "PedometerUserInfo [deviceId=" + this.deviceId + ", memberId=" + this.memberId + ", productUserNumber=" + this.productUserNumber + ", stride=" + this.stride + ", height=" + this.height + ", weight=" + this.weight + ", weekStart=" + this.weekStart + ", weekTargetSteps=" + this.weekTargetSteps + ", weekTargetCalories=" + this.weekTargetCalories + ", weekTargetDistance=" + this.weekTargetDistance + ", weekTargetExerciseAmount=" + this.weekTargetExerciseAmount + ", weightUnit=" + this.weightUnit + ", unit=" + this.unit + "]";
    }
}
