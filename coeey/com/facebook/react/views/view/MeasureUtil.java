package com.facebook.react.views.view;

import android.view.View.MeasureSpec;
import com.facebook.yoga.YogaMeasureMode;
import com.google.common.primitives.Ints;

public class MeasureUtil {
    public static int getMeasureSpec(float size, YogaMeasureMode mode) {
        if (mode == YogaMeasureMode.EXACTLY) {
            return MeasureSpec.makeMeasureSpec((int) size, Ints.MAX_POWER_OF_TWO);
        }
        if (mode == YogaMeasureMode.AT_MOST) {
            return MeasureSpec.makeMeasureSpec((int) size, Integer.MIN_VALUE);
        }
        return MeasureSpec.makeMeasureSpec(0, 0);
    }
}
