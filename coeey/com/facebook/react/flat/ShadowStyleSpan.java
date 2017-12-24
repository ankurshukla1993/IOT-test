package com.facebook.react.flat;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

final class ShadowStyleSpan extends CharacterStyle {
    static final ShadowStyleSpan INSTANCE = new ShadowStyleSpan(0.0f, 0.0f, 0.0f, 0, true);
    private int mColor;
    private float mDx;
    private float mDy;
    private boolean mFrozen;
    private float mRadius;

    private ShadowStyleSpan(float dx, float dy, float radius, int color, boolean frozen) {
        this.mDx = dx;
        this.mDy = dy;
        this.mRadius = radius;
        this.mColor = color;
        this.mFrozen = frozen;
    }

    public boolean offsetMatches(float dx, float dy) {
        return this.mDx == dx && this.mDy == dy;
    }

    public void setOffset(float dx, float dy) {
        this.mDx = dx;
        this.mDy = dy;
    }

    public float getRadius() {
        return this.mRadius;
    }

    public void setRadius(float radius) {
        this.mRadius = radius;
    }

    public int getColor() {
        return this.mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    ShadowStyleSpan mutableCopy() {
        return new ShadowStyleSpan(this.mDx, this.mDy, this.mRadius, this.mColor, false);
    }

    boolean isFrozen() {
        return this.mFrozen;
    }

    void freeze() {
        this.mFrozen = true;
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setShadowLayer(this.mRadius, this.mDx, this.mDy, this.mColor);
    }
}
