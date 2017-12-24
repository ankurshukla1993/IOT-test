package com.db.chart.model;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import com.db.chart.util.Preconditions;

public abstract class ChartEntry implements Comparable<ChartEntry> {
    private static final int DEFAULT_COLOR = -16777216;
    boolean isVisible;
    private int mColor = -16777216;
    private final String mLabel;
    private int[] mShadowColor = new int[4];
    private float mShadowDx = 0.0f;
    private float mShadowDy = 0.0f;
    private float mShadowRadius = 0.0f;
    private float mValue;
    private float mX;
    private float mY;

    class C10391 implements AnimatorUpdateListener {
        C10391() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            ChartEntry.this.mX = ((Float) animation.getAnimatedValue("x")).floatValue();
            ChartEntry.this.mY = ((Float) animation.getAnimatedValue("y")).floatValue();
        }
    }

    class C10402 implements AnimatorUpdateListener {
        C10402() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            ChartEntry.this.mColor = ((Integer) animation.getAnimatedValue()).intValue();
        }
    }

    ChartEntry(String label, float value) {
        this.mLabel = label;
        this.mValue = value;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public boolean hasShadow() {
        return this.mShadowRadius != 0.0f;
    }

    public ValueAnimator animateXY(float x0, float y0, float x1, float y1) {
        r1 = new PropertyValuesHolder[2];
        r1[0] = PropertyValuesHolder.ofFloat("x", new float[]{x0, x1});
        r1[1] = PropertyValuesHolder.ofFloat("y", new float[]{y0, y1});
        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(r1);
        animator.addUpdateListener(new C10391());
        this.mX = x0;
        this.mY = y0;
        return animator;
    }

    @RequiresApi(api = 21)
    public ValueAnimator animateColor(int color0, int color1) {
        ValueAnimator animator = ValueAnimator.ofArgb(new int[]{color0, color1});
        animator.addUpdateListener(new C10402());
        this.mColor = color0;
        return animator;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public float getValue() {
        return this.mValue;
    }

    public void setValue(float value) {
        this.mValue = value;
    }

    public float getX() {
        return this.mX;
    }

    public float getY() {
        return this.mY;
    }

    public int getColor() {
        return this.mColor;
    }

    public void setColor(@ColorInt int color) {
        this.isVisible = true;
        this.mColor = color;
    }

    public float getShadowRadius() {
        return this.mShadowRadius;
    }

    public float getShadowDx() {
        return this.mShadowDx;
    }

    public float getShadowDy() {
        return this.mShadowDy;
    }

    public int[] getShadowColor() {
        return this.mShadowColor;
    }

    public void setCoordinates(float x, float y) {
        this.mX = x;
        this.mY = y;
    }

    public void setShadow(float radius, float dx, float dy, @ColorInt int color) {
        this.mShadowRadius = radius;
        this.mShadowDx = dx;
        this.mShadowDy = dy;
        this.mShadowColor[0] = Color.alpha(color);
        this.mShadowColor[1] = Color.red(color);
        this.mShadowColor[2] = Color.blue(color);
        this.mShadowColor[3] = Color.green(color);
    }

    public String toString() {
        return "Label=" + this.mLabel + " \nValue=" + this.mValue + "\nX = " + this.mX + "\nY = " + this.mY;
    }

    public int compareTo(@NonNull ChartEntry other) {
        Preconditions.checkNotNull(other);
        return Float.compare(getValue(), other.getValue());
    }
}
