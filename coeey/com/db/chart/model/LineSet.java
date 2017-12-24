package com.db.chart.model;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import com.db.chart.util.Preconditions;
import com.db.chart.util.Tools;
import java.util.Iterator;

public class LineSet extends ChartSet {
    private static final int DEFAULT_COLOR = -16777216;
    private static final float LINE_THICKNESS = 4.0f;
    private static final String TAG = "chart.model.LineSet";
    private int mBegin;
    private int mColor;
    private float[] mDashedIntervals;
    private int mEnd;
    private int mFillColor;
    private int[] mGradientColors;
    private float[] mGradientPositions;
    private boolean mHasFill;
    private boolean mHasGradientFill;
    private boolean mIsDashed;
    private boolean mIsSmooth;
    private int[] mShadowColor;
    private float mShadowDx;
    private float mShadowDy;
    private float mShadowRadius;
    private float mThickness;

    public LineSet() {
        init();
    }

    public LineSet(@NonNull String[] labels, @NonNull float[] values) {
        init();
        if (labels.length != values.length) {
            throw new IllegalArgumentException("Arrays size doesn't match.");
        }
        Preconditions.checkNotNull(labels);
        Preconditions.checkNotNull(values);
        int nEntries = labels.length;
        for (int i = 0; i < nEntries; i++) {
            addPoint(labels[i], values[i]);
        }
    }

    private void init() {
        this.mThickness = Tools.fromDpToPx(LINE_THICKNESS);
        this.mColor = -16777216;
        this.mIsDashed = false;
        this.mDashedIntervals = null;
        this.mIsSmooth = false;
        this.mHasFill = false;
        this.mFillColor = -16777216;
        this.mHasGradientFill = false;
        this.mGradientColors = null;
        this.mGradientPositions = null;
        this.mBegin = 0;
        this.mEnd = 0;
        this.mShadowRadius = 0.0f;
        this.mShadowDx = 0.0f;
        this.mShadowDy = 0.0f;
        this.mShadowColor = new int[4];
    }

    public void addPoint(String label, float value) {
        addPoint(new Point(label, value));
    }

    public void addPoint(@NonNull Point point) {
        addEntry((ChartEntry) Preconditions.checkNotNull(point));
    }

    public boolean isDashed() {
        return this.mIsDashed;
    }

    public LineSet setDashed(@NonNull float[] intervals) {
        this.mIsDashed = true;
        this.mDashedIntervals = (float[]) Preconditions.checkNotNull(intervals);
        return this;
    }

    public boolean isSmooth() {
        return this.mIsSmooth;
    }

    public LineSet setSmooth(boolean bool) {
        this.mIsSmooth = bool;
        return this;
    }

    public boolean hasFill() {
        return this.mHasFill;
    }

    public boolean hasGradientFill() {
        return this.mHasGradientFill;
    }

    public boolean hasShadow() {
        return this.mShadowRadius != 0.0f;
    }

    public float getThickness() {
        return this.mThickness;
    }

    public LineSet setThickness(@FloatRange(from = 0.0d) float thickness) {
        if (thickness < 0.0f) {
            throw new IllegalArgumentException("Line thickness can't be <= 0.");
        }
        this.mThickness = thickness;
        return this;
    }

    public int getColor() {
        return this.mColor;
    }

    public LineSet setColor(@ColorInt int color) {
        this.mColor = color;
        return this;
    }

    public int getFillColor() {
        return this.mFillColor;
    }

    public int[] getGradientColors() {
        return this.mGradientColors;
    }

    public float[] getGradientPositions() {
        return this.mGradientPositions;
    }

    public int getBegin() {
        return this.mBegin;
    }

    public int getEnd() {
        if (this.mEnd == 0) {
            return size();
        }
        return this.mEnd;
    }

    public float[] getDashedIntervals() {
        return this.mDashedIntervals;
    }

    public int getDashedPhase() {
        return 0;
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

    public LineSet setFill(@ColorInt int color) {
        this.mHasFill = true;
        this.mFillColor = color;
        if (this.mColor == -16777216) {
            this.mColor = color;
        }
        return this;
    }

    public LineSet setGradientFill(@Size(min = 1) @NonNull int[] colors, float[] positions) {
        if (colors.length == 0) {
            throw new IllegalArgumentException("Colors argument can't be null or empty.");
        }
        this.mHasGradientFill = true;
        this.mGradientColors = (int[]) Preconditions.checkNotNull(colors);
        this.mGradientPositions = positions;
        if (this.mColor == -16777216) {
            this.mColor = colors[0];
        }
        return this;
    }

    public LineSet beginAt(@IntRange(from = 0) int index) {
        this.mBegin = Preconditions.checkPositionIndex(index, size());
        return this;
    }

    public LineSet endAt(@IntRange(from = 0) int index) {
        if (index < this.mBegin) {
            throw new IllegalArgumentException("Index cannot be lesser than the start entry defined in beginAt(index).");
        }
        this.mEnd = Preconditions.checkPositionIndex(index, size());
        return this;
    }

    public LineSet setDotsColor(@ColorInt int color) {
        Iterator it = getEntries().iterator();
        while (it.hasNext()) {
            ((ChartEntry) it.next()).setColor(color);
        }
        return this;
    }

    public LineSet setDotsRadius(@FloatRange(from = 0.0d) float radius) {
        if (radius < 0.0f) {
            throw new IllegalArgumentException("Dots radius can't be < 0.");
        }
        Iterator it = getEntries().iterator();
        while (it.hasNext()) {
            ((Point) ((ChartEntry) it.next())).setRadius(radius);
        }
        return this;
    }

    public LineSet setDotsStrokeThickness(@FloatRange(from = 0.0d) float thickness) {
        if (thickness < 0.0f) {
            throw new IllegalArgumentException("Dots thickness can't be < 0.");
        }
        Iterator it = getEntries().iterator();
        while (it.hasNext()) {
            ((Point) ((ChartEntry) it.next())).setStrokeThickness(thickness);
        }
        return this;
    }

    public LineSet setDotsStrokeColor(@ColorInt int color) {
        Iterator it = getEntries().iterator();
        while (it.hasNext()) {
            ((Point) ((ChartEntry) it.next())).setStrokeColor(color);
        }
        return this;
    }

    public LineSet setDotsDrawable(@NonNull Drawable drawable) {
        Preconditions.checkNotNull(drawable);
        Iterator it = getEntries().iterator();
        while (it.hasNext()) {
            ((Point) ((ChartEntry) it.next())).setDrawable(drawable);
        }
        return this;
    }

    public void setShadow(float radius, float dx, float dy, int color) {
        super.setShadow(radius, dx, dy, color);
        this.mShadowRadius = radius;
        this.mShadowDx = dx;
        this.mShadowDy = dy;
        this.mShadowColor[0] = Color.alpha(color);
        this.mShadowColor[1] = Color.red(color);
        this.mShadowColor[2] = Color.blue(color);
        this.mShadowColor[3] = Color.green(color);
    }
}
