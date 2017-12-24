package com.facebook.react.animation;

import android.view.View;

public class ScaleXAnimationPropertyUpdater extends AbstractSingleFloatProperyUpdater {
    public ScaleXAnimationPropertyUpdater(float toValue) {
        super(toValue);
    }

    public ScaleXAnimationPropertyUpdater(float fromValue, float toValue) {
        super(fromValue, toValue);
    }

    protected float getProperty(View view) {
        return view.getScaleX();
    }

    protected void setProperty(View view, float propertyValue) {
        view.setScaleX(propertyValue);
    }
}
