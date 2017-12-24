package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class ScalingUtils$ScaleTypeFitCenter extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeFitCenter();

    private ScalingUtils$ScaleTypeFitCenter() {
    }

    public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
        float scale = Math.min(scaleX, scaleY);
        float dx = ((float) parentRect.left) + ((((float) parentRect.width()) - (((float) childWidth) * scale)) * 0.5f);
        float dy = ((float) parentRect.top) + ((((float) parentRect.height()) - (((float) childHeight) * scale)) * 0.5f);
        outTransform.setScale(scale, scale);
        outTransform.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (dy + 0.5f)));
    }
}
