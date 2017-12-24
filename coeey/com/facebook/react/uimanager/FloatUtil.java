package com.facebook.react.uimanager;

public class FloatUtil {
    private static final float EPSILON = 1.0E-5f;

    public static boolean floatsEqual(float f1, float f2) {
        if (Float.isNaN(f1) || Float.isNaN(f2)) {
            if (Float.isNaN(f1) && Float.isNaN(f2)) {
                return true;
            }
            return false;
        } else if (Math.abs(f2 - f1) >= EPSILON) {
            return false;
        } else {
            return true;
        }
    }
}
