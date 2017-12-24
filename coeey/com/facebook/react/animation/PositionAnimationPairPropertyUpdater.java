package com.facebook.react.animation;

import android.view.View;

public class PositionAnimationPairPropertyUpdater extends AbstractFloatPairPropertyUpdater {
    public PositionAnimationPairPropertyUpdater(float toFirst, float toSecond) {
        super(toFirst, toSecond);
    }

    public PositionAnimationPairPropertyUpdater(float fromFirst, float fromSecond, float toFirst, float toSecond) {
        super(fromFirst, fromSecond, toFirst, toSecond);
    }

    protected void getProperty(View view, float[] returnValues) {
        returnValues[0] = view.getX() + (((float) view.getWidth()) * 0.5f);
        returnValues[1] = view.getY() + (((float) view.getHeight()) * 0.5f);
    }

    protected void setProperty(View view, float[] propertyValues) {
        view.setX(propertyValues[0] - (((float) view.getWidth()) * 0.5f));
        view.setY(propertyValues[1] - (((float) view.getHeight()) * 0.5f));
    }
}
