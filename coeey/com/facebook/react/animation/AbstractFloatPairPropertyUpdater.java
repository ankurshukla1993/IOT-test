package com.facebook.react.animation;

import android.view.View;

public abstract class AbstractFloatPairPropertyUpdater implements AnimationPropertyUpdater {
    private boolean mFromSource;
    private final float[] mFromValues;
    private final float[] mToValues;
    private final float[] mUpdateValues;

    protected abstract void getProperty(View view, float[] fArr);

    protected abstract void setProperty(View view, float[] fArr);

    protected AbstractFloatPairPropertyUpdater(float toFirst, float toSecond) {
        this.mFromValues = new float[2];
        this.mToValues = new float[2];
        this.mUpdateValues = new float[2];
        this.mToValues[0] = toFirst;
        this.mToValues[1] = toSecond;
        this.mFromSource = true;
    }

    protected AbstractFloatPairPropertyUpdater(float fromFirst, float fromSecond, float toFirst, float toSecond) {
        this(toFirst, toSecond);
        this.mFromValues[0] = fromFirst;
        this.mFromValues[1] = fromSecond;
        this.mFromSource = false;
    }

    public void prepare(View view) {
        if (this.mFromSource) {
            getProperty(view, this.mFromValues);
        }
    }

    public void onUpdate(View view, float progress) {
        this.mUpdateValues[0] = this.mFromValues[0] + ((this.mToValues[0] - this.mFromValues[0]) * progress);
        this.mUpdateValues[1] = this.mFromValues[1] + ((this.mToValues[1] - this.mFromValues[1]) * progress);
        setProperty(view, this.mUpdateValues);
    }

    public void onFinish(View view) {
        setProperty(view, this.mToValues);
    }
}
