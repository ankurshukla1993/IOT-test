package com.facebook.fbui.textlayoutbuilder.util;

import android.os.Build.VERSION;
import android.text.Layout;
import android.text.StaticLayout;

public class LayoutMeasureUtil {
    public static int getWidth(Layout layout) {
        if (layout == null) {
            return 0;
        }
        int count = layout.getLineCount();
        int maxWidth = 0;
        for (int i = 0; i < count; i++) {
            maxWidth = Math.max(maxWidth, (int) layout.getLineRight(i));
        }
        return maxWidth;
    }

    public static int getHeight(Layout layout) {
        if (layout == null) {
            return 0;
        }
        int extra = 0;
        if (VERSION.SDK_INT < 20 && (layout instanceof StaticLayout)) {
            int above = layout.getLineAscent(layout.getLineCount() - 1);
            int below = layout.getLineDescent(layout.getLineCount() - 1);
            float ex = ((float) (below - above)) - ((((float) (below - above)) - layout.getSpacingAdd()) / layout.getSpacingMultiplier());
            extra = ex >= 0.0f ? (int) (((double) ex) + 0.5d) : -((int) (((double) (-ex)) + 0.5d));
        }
        return layout.getHeight() - extra;
    }
}
