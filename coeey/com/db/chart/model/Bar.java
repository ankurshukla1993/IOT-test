package com.db.chart.model;

import android.support.annotation.NonNull;
import android.support.annotation.Size;
import com.db.chart.util.Preconditions;

public class Bar extends ChartEntry {
    private static final String TAG = "chart.model.Bar";
    private int[] mGradientColors;
    private float[] mGradientPositions;
    private boolean mHasGradientColor;

    public Bar(String label, float value) {
        super(label, value);
        this.isVisible = true;
        this.mHasGradientColor = false;
    }

    public boolean hasGradientColor() {
        return this.mHasGradientColor;
    }

    public int[] getGradientColors() {
        return this.mGradientColors;
    }

    public float[] getGradientPositions() {
        return this.mGradientPositions;
    }

    public Bar setGradientColor(@Size(min = 1) @NonNull int[] colors, float[] positions) {
        if (colors.length == 0) {
            throw new IllegalArgumentException("Colors list cannot be empty");
        }
        this.mHasGradientColor = true;
        this.mGradientColors = (int[]) Preconditions.checkNotNull(colors);
        this.mGradientPositions = positions;
        return this;
    }
}
