package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;
import com.google.android.flexbox.FlexItem;

class ScalingUtils$ScaleTypeCenterInside extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeCenterInside();

    private ScalingUtils$ScaleTypeCenterInside() {
    }

    public void getTransformImpl(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY, float scaleX, float scaleY) {
        float scale = Math.min(Math.min(scaleX, scaleY), FlexItem.FLEX_SHRINK_DEFAULT);
        float dx = ((float) parentRect.left) + ((((float) parentRect.width()) - (((float) childWidth) * scale)) * 0.5f);
        float dy = ((float) parentRect.top) + ((((float) parentRect.height()) - (((float) childHeight) * scale)) * 0.5f);
        outTransform.setScale(scale, scale);
        outTransform.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (dy + 0.5f)));
    }
}
