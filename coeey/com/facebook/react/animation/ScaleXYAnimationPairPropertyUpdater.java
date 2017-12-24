package com.facebook.react.animation;

import android.view.View;

public class ScaleXYAnimationPairPropertyUpdater extends AbstractFloatPairPropertyUpdater {
    public ScaleXYAnimationPairPropertyUpdater(float toFirst, float toSecond) {
        super(toFirst, toSecond);
    }

    public ScaleXYAnimationPairPropertyUpdater(float fromFirst, float fromSecond, float toFirst, float toSecond) {
        super(fromFirst, fromSecond, toFirst, toSecond);
    }

    protected void getProperty(View view, float[] returnValues) {
        returnValues[0] = view.getScaleX();
        returnValues[1] = view.getScaleY();
    }

    protected void setProperty(View view, float[] propertyValues) {
        view.setScaleX(propertyValues[0]);
        view.setScaleY(propertyValues[1]);
    }
}
