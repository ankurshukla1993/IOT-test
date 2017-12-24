package com.thefinestartist.converters;

import com.thefinestartist.Base;

public class UnitConverter {
    protected UnitConverter() {
    }

    public static float dpToPx(float dp) {
        return Base.getDisplayMetrics().density * dp;
    }

    public static int dpToPx(int dp) {
        return (int) ((((float) dp) * Base.getDisplayMetrics().density) + 0.5f);
    }

    public static float pxToDp(float px) {
        return px / Base.getDisplayMetrics().density;
    }

    public static int pxToDp(int px) {
        return (int) ((((float) px) / Base.getDisplayMetrics().density) + 0.5f);
    }

    public static float spToPx(float sp) {
        return Base.getDisplayMetrics().scaledDensity * sp;
    }

    public static int spToPx(int sp) {
        return (int) ((((float) sp) * Base.getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static float pxToSp(float px) {
        return px / Base.getDisplayMetrics().scaledDensity;
    }

    public static int pxToSp(int px) {
        return (int) ((((float) px) / Base.getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
