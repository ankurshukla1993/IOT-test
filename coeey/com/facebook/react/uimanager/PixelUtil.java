package com.facebook.react.uimanager;

import android.util.TypedValue;

public class PixelUtil {
    public static float toPixelFromDIP(float value) {
        return TypedValue.applyDimension(1, value, DisplayMetricsHolder.getWindowDisplayMetrics());
    }

    public static float toPixelFromDIP(double value) {
        return toPixelFromDIP((float) value);
    }

    public static float toPixelFromSP(float value) {
        return TypedValue.applyDimension(2, value, DisplayMetricsHolder.getWindowDisplayMetrics());
    }

    public static float toPixelFromSP(double value) {
        return toPixelFromSP((float) value);
    }

    public static float toDIPFromPixel(float value) {
        return value / DisplayMetricsHolder.getWindowDisplayMetrics().density;
    }
}
