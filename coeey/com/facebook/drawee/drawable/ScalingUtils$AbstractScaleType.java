package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

public abstract class ScalingUtils$AbstractScaleType implements ScalingUtils$ScaleType {
    public abstract void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4);

    public Matrix getTransform(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY) {
        getTransformImpl(outTransform, parentRect, childWidth, childHeight, focusX, focusY, ((float) parentRect.width()) / ((float) childWidth), ((float) parentRect.height()) / ((float) childHeight));
        return outTransform;
    }
}
