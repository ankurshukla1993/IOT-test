package com.db.chart.model;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import com.db.chart.util.Tools;

public class Point extends ChartEntry {
    private static final int DEFAULT_COLOR = -16777216;
    private static final float DOTS_RADIUS = 3.0f;
    private static final float DOTS_THICKNESS = 4.0f;
    private static final String TAG = "chart.model.Point";
    private Drawable mDrawable;
    private boolean mHasStroke;
    private float mRadius;
    private int mStrokeColor;
    private float mStrokeThickness;

    public Point(String label, float value) {
        super(label, value);
        this.isVisible = false;
        this.mRadius = Tools.fromDpToPx(DOTS_THICKNESS);
        this.mHasStroke = false;
        this.mStrokeThickness = Tools.fromDpToPx(DOTS_RADIUS);
        this.mStrokeColor = -16777216;
        this.mDrawable = null;
    }

    public boolean hasStroke() {
        return this.mHasStroke;
    }

    public float getStrokeThickness() {
        return this.mStrokeThickness;
    }

    public Point setStrokeThickness(@FloatRange(from = 0.0d) float thickness) {
        if (thickness < 0.0f) {
            throw new IllegalArgumentException("Grid thickness < 0.");
        }
        this.isVisible = true;
        this.mHasStroke = true;
        this.mStrokeThickness = thickness;
        return this;
    }

    public float getRadius() {
        return this.mRadius;
    }

    public Point setRadius(@FloatRange(from = 0.0d) float radius) {
        if (radius < 0.0f) {
            throw new IllegalArgumentException("Dot radius can't be < 0.");
        }
        this.isVisible = true;
        this.mRadius = radius;
        return this;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public Point setStrokeColor(@ColorInt int color) {
        this.isVisible = true;
        this.mHasStroke = true;
        this.mStrokeColor = color;
        return this;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public Point setDrawable(@NonNull Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable argument can't be null.");
        }
        this.isVisible = true;
        this.mDrawable = drawable;
        return this;
    }
}
