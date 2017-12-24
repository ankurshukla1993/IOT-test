package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class ScalingUtils$ScaleTypeFitStart extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeFitStart();

    private ScalingUtils$ScaleTypeFitStart() {
    }

    public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
        float scale = Math.min(scaleX, scaleY);
        float dx = (float) parentRect.left;
        float dy = (float) parentRect.top;
        outTransform.setScale(scale, scale);
        outTransform.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (0.5f + dy)));
    }
}
