package com.github.lzyzsd.circleprogress;

import android.content.res.Resources;

public final class Utils {
    private Utils() {
    }

    public static float dp2px(Resources resources, float dp) {
        return (dp * resources.getDisplayMetrics().density) + 0.5f;
    }

    public static float sp2px(Resources resources, float sp) {
        return sp * resources.getDisplayMetrics().scaledDensity;
    }
}
