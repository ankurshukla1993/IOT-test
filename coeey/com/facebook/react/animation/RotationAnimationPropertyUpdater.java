package com.facebook.react.animation;

import android.view.View;

public class RotationAnimationPropertyUpdater extends AbstractSingleFloatProperyUpdater {
    public RotationAnimationPropertyUpdater(float toValue) {
        super(toValue);
    }

    protected float getProperty(View view) {
        return view.getRotation();
    }

    protected void setProperty(View view, float propertyValue) {
        view.setRotation((float) Math.toDegrees((double) propertyValue));
    }
}
