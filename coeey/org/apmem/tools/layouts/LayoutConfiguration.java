package org.apmem.tools.layouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

class LayoutConfiguration {
    private boolean debugDraw = false;
    private int gravity = 51;
    private int layoutDirection = 0;
    private int orientation = 0;
    private float weightDefault = 0.0f;

    public LayoutConfiguration(Context context, AttributeSet attributeSet) {
        TypedArray a = context.obtainStyledAttributes(attributeSet, C2542R.styleable.FlowLayout);
        try {
            setOrientation(a.getInteger(C2542R.styleable.FlowLayout_android_orientation, 0));
            setDebugDraw(a.getBoolean(C2542R.styleable.FlowLayout_debugDraw, false));
            setWeightDefault(a.getFloat(C2542R.styleable.FlowLayout_weightDefault, 0.0f));
            setGravity(a.getInteger(C2542R.styleable.FlowLayout_android_gravity, 0));
            setLayoutDirection(a.getInteger(C2542R.styleable.FlowLayout_layoutDirection, 0));
        } finally {
            a.recycle();
        }
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void setOrientation(int orientation) {
        if (orientation == 1) {
            this.orientation = orientation;
        } else {
            this.orientation = 0;
        }
    }

    public boolean isDebugDraw() {
        return this.debugDraw;
    }

    public void setDebugDraw(boolean debugDraw) {
        this.debugDraw = debugDraw;
    }

    public float getWeightDefault() {
        return this.weightDefault;
    }

    public void setWeightDefault(float weightDefault) {
        this.weightDefault = Math.max(0.0f, weightDefault);
    }

    public int getGravity() {
        return this.gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getLayoutDirection() {
        return this.layoutDirection;
    }

    public void setLayoutDirection(int layoutDirection) {
        if (layoutDirection == 1) {
            this.layoutDirection = layoutDirection;
        } else {
            this.layoutDirection = 0;
        }
    }
}
