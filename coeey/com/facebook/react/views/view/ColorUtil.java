package com.facebook.react.views.view;

public class ColorUtil {
    public static int multiplyColorAlpha(int color, int alpha) {
        if (alpha == 255) {
            return color;
        }
        if (alpha == 0) {
            return color & 16777215;
        }
        return ((((color >>> 24) * (alpha + (alpha >> 7))) >> 8) << 24) | (16777215 & color);
    }

    public static int getOpacityFromColor(int color) {
        int colorAlpha = color >>> 24;
        if (colorAlpha == 255) {
            return -1;
        }
        if (colorAlpha == 0) {
            return -2;
        }
        return -3;
    }
}
