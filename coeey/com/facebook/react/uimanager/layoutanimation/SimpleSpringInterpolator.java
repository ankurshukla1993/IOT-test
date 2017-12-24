package com.facebook.react.uimanager.layoutanimation;

import android.view.animation.Interpolator;

class SimpleSpringInterpolator implements Interpolator {
    private static final float FACTOR = 0.5f;

    SimpleSpringInterpolator() {
    }

    public float getInterpolation(float input) {
        return (float) (1.0d + (Math.pow(2.0d, (double) (-10.0f * input)) * Math.sin(((((double) (input - 0.125f)) * 3.141592653589793d) * 2.0d) / 0.5d)));
    }
}
