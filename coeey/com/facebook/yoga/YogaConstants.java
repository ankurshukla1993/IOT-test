package com.facebook.yoga;

public class YogaConstants {
    public static final float UNDEFINED = Float.NaN;

    public static boolean isUndefined(float value) {
        return Float.compare(value, UNDEFINED) == 0;
    }

    public static boolean isUndefined(YogaValue value) {
        return value.unit == YogaUnit.UNDEFINED;
    }
}
