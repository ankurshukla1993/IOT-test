package com.facebook.yoga;

public class YogaMeasureOutput {
    public static long make(float width, float height) {
        return (((long) Float.floatToRawIntBits(width)) << 32) | ((long) Float.floatToRawIntBits(height));
    }

    public static long make(int width, int height) {
        return make((float) width, (float) height);
    }

    public static float getWidth(long measureOutput) {
        return Float.intBitsToFloat((int) (-1 & (measureOutput >> 32)));
    }

    public static float getHeight(long measureOutput) {
        return Float.intBitsToFloat((int) (-1 & measureOutput));
    }
}
