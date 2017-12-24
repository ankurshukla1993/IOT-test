package com.facebook.react.uimanager;

import android.view.View.MeasureSpec;

public class MeasureSpecAssertions {
    public static final void assertExplicitMeasureSpec(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == 0 || heightMode == 0) {
            throw new IllegalStateException("A catalyst view must have an explicit width and height given to it. This should normally happen as part of the standard catalyst UI framework.");
        }
    }
}
