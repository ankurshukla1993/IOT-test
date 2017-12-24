package com.ihealth.communication.cloud.data;

public class Data_TB_Unit {
    private String f866a;
    private int f867b;
    private long f868c;
    private int f869d;
    private long f870e;
    private int f871f;
    private long f872g;
    private int f873h;
    private long f874i;
    private int f875j;
    private long f876k;
    private int f877l;
    private long f878m;
    private int f879n;
    private long f880o;

    public Data_TB_Unit() {
        this.f866a = new String();
    }

    public Data_TB_Unit(String UserName, int BPUnit, long BPUnitTS, int BGUnit, long BGUnitTS, int HeightUnit, long HeightUnitTS, int WeightUnit, long WeightUnitTS, int FoodWeightUnit, long FoodWeightUnitTS, int LengthUnit, long LengthUnitTS, int TemperatureUnit, long TemperatureTS) {
        this.f866a = UserName;
        this.f867b = BPUnit;
        this.f868c = BPUnitTS;
        this.f869d = BGUnit;
        this.f870e = BGUnitTS;
        this.f871f = HeightUnit;
        this.f872g = HeightUnitTS;
        this.f873h = WeightUnit;
        this.f874i = WeightUnitTS;
        this.f875j = FoodWeightUnit;
        this.f876k = FoodWeightUnitTS;
        this.f877l = LengthUnit;
        this.f878m = LengthUnitTS;
        this.f879n = TemperatureUnit;
        this.f880o = TemperatureTS;
    }

    public int getBGUnit() {
        return this.f869d;
    }

    public long getBGUnitTS() {
        return this.f870e;
    }

    public int getBPUnit() {
        return this.f867b;
    }

    public long getBPUnitTS() {
        return this.f868c;
    }

    public int getFoodWeightUnit() {
        return this.f875j;
    }

    public long getFoodWeightUnitTS() {
        return this.f876k;
    }

    public int getHeightUnit() {
        return this.f871f;
    }

    public long getHeightUnitTS() {
        return this.f872g;
    }

    public int getLengthUnit() {
        return this.f877l;
    }

    public long getLengthUnitTS() {
        return this.f878m;
    }

    public long getTemperatureTS() {
        return this.f880o;
    }

    public int getTemperatureUnit() {
        return this.f879n;
    }

    public String getUserName() {
        return this.f866a;
    }

    public int getWeightUnit() {
        return this.f873h;
    }

    public long getWeightUnitTS() {
        return this.f874i;
    }

    public void setBGUnit(int bGUnit) {
        this.f869d = bGUnit;
    }

    public void setBGUnitTS(long bGUnitTS) {
        this.f870e = bGUnitTS;
    }

    public void setBPUnit(int bPUnit) {
        this.f867b = bPUnit;
    }

    public void setBPUnitTS(long bPUnitTS) {
        this.f868c = bPUnitTS;
    }

    public void setFoodWeightUnit(int foodWeightUnit) {
        this.f875j = foodWeightUnit;
    }

    public void setFoodWeightUnitTS(long foodWeightUnitTS) {
        this.f876k = foodWeightUnitTS;
    }

    public void setHeightUnit(int heightUnit) {
        this.f871f = heightUnit;
    }

    public void setHeightUnitTS(long heightUnitTS) {
        this.f872g = heightUnitTS;
    }

    public void setLengthUnit(int lengthUnit) {
        this.f877l = lengthUnit;
    }

    public void setLengthUnitTS(long lengthUnitTS) {
        this.f878m = lengthUnitTS;
    }

    public void setTemperatureTS(long temperatureTS) {
        this.f880o = temperatureTS;
    }

    public void setTemperatureUnit(int temperatureUnit) {
        this.f879n = temperatureUnit;
    }

    public void setUnitNetDefult() {
        this.f867b = 0;
        this.f868c = System.currentTimeMillis() / 1000;
        this.f869d = 1;
        this.f870e = System.currentTimeMillis() / 1000;
        this.f871f = 1;
        this.f872g = System.currentTimeMillis() / 1000;
        this.f873h = 1;
        this.f874i = System.currentTimeMillis() / 1000;
        this.f875j = 1;
        this.f876k = System.currentTimeMillis() / 1000;
        this.f877l = 1;
        this.f878m = System.currentTimeMillis() / 1000;
        this.f879n = 0;
        this.f880o = System.currentTimeMillis() / 1000;
    }

    public void setUserName(String userName) {
        this.f866a = userName;
    }

    public void setWeightUnit(int weightUnit) {
        this.f873h = weightUnit;
    }

    public void setWeightUnitTS(long weightUnitTS) {
        this.f874i = weightUnitTS;
    }
}
