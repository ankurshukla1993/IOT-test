package com.facebook.react.animation;

import android.view.View;

public class ScaleYAnimationPropertyUpdater extends AbstractSingleFloatProperyUpdater {
    public ScaleYAnimationPropertyUpdater(float toValue) {
        super(toValue);
    }

    public ScaleYAnimationPropertyUpdater(float fromValue, float toValue) {
        super(fromValue, toValue);
    }

    protected float getProperty(View view) {
        return view.getScaleY();
    }

    protected void setProperty(View view, float propertyValue) {
        view.setScaleY(propertyValue);
    }
}
