package com.appeaser.sublimepickerlibrary.drawables;

import android.animation.TypeEvaluator;
import android.graphics.RectF;

public class CRectFEvaluator implements TypeEvaluator<RectF> {
    private RectF mRectF;

    public CRectFEvaluator(RectF reuseRect) {
        this.mRectF = reuseRect;
    }

    public RectF evaluate(float fraction, RectF startValue, RectF endValue) {
        float left = startValue.left + ((endValue.left - startValue.left) * fraction);
        float top = startValue.top + ((endValue.top - startValue.top) * fraction);
        float right = startValue.right + ((endValue.right - startValue.right) * fraction);
        float bottom = startValue.bottom + ((endValue.bottom - startValue.bottom) * fraction);
        if (this.mRectF == null) {
            return new RectF(left, top, right, bottom);
        }
        this.mRectF.set(left, top, right, bottom);
        return this.mRectF;
    }
}
