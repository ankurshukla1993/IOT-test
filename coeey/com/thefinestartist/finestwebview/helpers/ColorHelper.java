package com.thefinestartist.finestwebview.helpers;

import android.graphics.Color;

public class ColorHelper {
    public static int disableColor(int color) {
        return Color.argb((int) (((float) Color.alpha(color)) * 0.2f), Color.red(color), Color.green(color), Color.blue(color));
    }
}
