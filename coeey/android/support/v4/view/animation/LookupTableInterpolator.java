package android.support.v4.view.animation;

import android.view.animation.Interpolator;

abstract class LookupTableInterpolator implements Interpolator {
    private final float mStepSize = (FlexItem.FLEX_SHRINK_DEFAULT / ((float) (this.mValues.length - 1)));
    private final float[] mValues;

    public LookupTableInterpolator(float[] values) {
        this.mValues = values;
    }

    public float getInterpolation(float input) {
        if (input >= FlexItem.FLEX_SHRINK_DEFAULT) {
            return FlexItem.FLEX_SHRINK_DEFAULT;
        }
        if (input <= 0.0f) {
            return 0.0f;
        }
        int position = Math.min((int) (((float) (this.mValues.length - 1)) * input), this.mValues.length - 2);
        return this.mValues[position] + ((this.mValues[position + 1] - this.mValues[position]) * ((input - (((float) position) * this.mStepSize)) / this.mStepSize));
    }
}
