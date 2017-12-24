package com.lifesense.fat;

public abstract class FatPercentage {
    public static final int Female = 1;
    public static final int Male = 0;
    public static final String fda = "FDA";

    public static final FatPercentage getInstance(String str) {
        return str == fda ? new C2263a() : null;
    }

    public abstract double getBmi(double d, double d2);

    public abstract double getBodyWater(int i, double d, int i2, double d2, boolean z);

    public abstract double getBone(int i, double d, int i2, double d2, boolean z);

    public abstract double getCalorie(int i, double d, double d2, int i2, boolean z);

    public abstract double getFat(int i, double d, int i2, double d2, boolean z);

    public abstract double getImp(int i);

    public abstract double getMuscle(int i, double d, int i2, double d2, boolean z);
}
