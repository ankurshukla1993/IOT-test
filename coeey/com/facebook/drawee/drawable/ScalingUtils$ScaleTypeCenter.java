package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class ScalingUtils$ScaleTypeCenter extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeCenter();

    private ScalingUtils$ScaleTypeCenter() {
    }

    public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
        outTransform.setTranslate((float) ((int) ((((float) parentRect.left) + (((float) (parentRect.width() - childWidth)) * 0.5f)) + 0.5f)), (float) ((int) ((((float) parentRect.top) + (((float) (parentRect.height() - childHeight)) * 0.5f)) + 0.5f)));
    }
}
