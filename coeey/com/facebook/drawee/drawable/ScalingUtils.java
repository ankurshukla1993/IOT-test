package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;
import javax.annotation.Nullable;

public class ScalingUtils {

    public static class InterpolatingScaleType implements ScaleType, StatefulScaleType {
        @Nullable
        private final Rect mBoundsFrom;
        @Nullable
        private final Rect mBoundsTo;
        private float mInterpolatingValue;
        private final float[] mMatrixValuesFrom;
        private final float[] mMatrixValuesInterpolated;
        private final float[] mMatrixValuesTo;
        private final ScaleType mScaleTypeFrom;
        private final ScaleType mScaleTypeTo;

        public InterpolatingScaleType(ScaleType scaleTypeFrom, ScaleType scaleTypeTo, @Nullable Rect boundsFrom, @Nullable Rect boundsTo) {
            this.mMatrixValuesFrom = new float[9];
            this.mMatrixValuesTo = new float[9];
            this.mMatrixValuesInterpolated = new float[9];
            this.mScaleTypeFrom = scaleTypeFrom;
            this.mScaleTypeTo = scaleTypeTo;
            this.mBoundsFrom = boundsFrom;
            this.mBoundsTo = boundsTo;
        }

        public InterpolatingScaleType(ScaleType scaleTypeFrom, ScaleType scaleTypeTo) {
            this(scaleTypeFrom, scaleTypeTo, null, null);
        }

        public ScaleType getScaleTypeFrom() {
            return this.mScaleTypeFrom;
        }

        public ScaleType getScaleTypeTo() {
            return this.mScaleTypeTo;
        }

        @Nullable
        public Rect getBoundsFrom() {
            return this.mBoundsFrom;
        }

        @Nullable
        public Rect getBoundsTo() {
            return this.mBoundsTo;
        }

        public void setValue(float value) {
            this.mInterpolatingValue = value;
        }

        public float getValue() {
            return this.mInterpolatingValue;
        }

        public Object getState() {
            return Float.valueOf(this.mInterpolatingValue);
        }

        public Matrix getTransform(Matrix transform, Rect parentBounds, int childWidth, int childHeight, float focusX, float focusY) {
            Rect boundsFrom;
            Rect boundsTo;
            if (this.mBoundsFrom != null) {
                boundsFrom = this.mBoundsFrom;
            } else {
                boundsFrom = parentBounds;
            }
            if (this.mBoundsTo != null) {
                boundsTo = this.mBoundsTo;
            } else {
                boundsTo = parentBounds;
            }
            this.mScaleTypeFrom.getTransform(transform, boundsFrom, childWidth, childHeight, focusX, focusY);
            transform.getValues(this.mMatrixValuesFrom);
            this.mScaleTypeTo.getTransform(transform, boundsTo, childWidth, childHeight, focusX, focusY);
            transform.getValues(this.mMatrixValuesTo);
            for (int i = 0; i < 9; i++) {
                this.mMatrixValuesInterpolated[i] = (this.mMatrixValuesFrom[i] * (FlexItem.FLEX_SHRINK_DEFAULT - this.mInterpolatingValue)) + (this.mMatrixValuesTo[i] * this.mInterpolatingValue);
            }
            transform.setValues(this.mMatrixValuesInterpolated);
            return transform;
        }
    }

    @Deprecated
    public static Matrix getTransform(Matrix transform, Rect parentBounds, int childWidth, int childHeight, float focusX, float focusY, ScaleType scaleType) {
        return scaleType.getTransform(transform, parentBounds, childWidth, childHeight, focusX, focusY);
    }
}
