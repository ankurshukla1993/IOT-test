package com.facebook.react.animation;

import android.view.View;

public abstract class AbstractSingleFloatProperyUpdater implements AnimationPropertyUpdater {
    private boolean mFromSource = true;
    private float mFromValue;
    private float mToValue;

    protected abstract float getProperty(View view);

    protected abstract void setProperty(View view, float f);

    protected AbstractSingleFloatProperyUpdater(float toValue) {
        this.mToValue = toValue;
    }

    protected AbstractSingleFloatProperyUpdater(float fromValue, float toValue) {
        this(toValue);
        this.mFromValue = fromValue;
    }

    public final void prepare(View view) {
        if (this.mFromSource) {
            this.mFromValue = getProperty(view);
        }
    }

    public final void onUpdate(View view, float progress) {
        setProperty(view, this.mFromValue + ((this.mToValue - this.mFromValue) * progress));
    }

    public void onFinish(View view) {
        setProperty(view, this.mToValue);
    }
}
