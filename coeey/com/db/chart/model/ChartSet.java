package com.db.chart.model;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import com.db.chart.util.Preconditions;
import com.google.android.flexbox.FlexItem;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public abstract class ChartSet {
    private static final String TAG = "chart.model.ChartSet";
    private float mAlpha = FlexItem.FLEX_SHRINK_DEFAULT;
    private final ArrayList<ChartEntry> mEntries = new ArrayList();
    private boolean mIsVisible = false;

    class C10411 implements AnimatorUpdateListener {
        C10411() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            ChartSet.this.mAlpha = ((Float) animation.getAnimatedValue()).floatValue();
        }
    }

    ChartSet() {
    }

    void addEntry(@NonNull ChartEntry e) {
        this.mEntries.add(Preconditions.checkNotNull(e));
    }

    public void updateValues(@NonNull float[] newValues) {
        Preconditions.checkNotNull(newValues);
        if (newValues.length != size()) {
            throw new IllegalArgumentException("New set values given doesn't match previous number of entries.");
        }
        int nEntries = size();
        for (int i = 0; i < nEntries; i++) {
            setValue(i, newValues[i]);
        }
    }

    public ValueAnimator animateAlpha(float from, float to) {
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{this.mAlpha, FlexItem.FLEX_SHRINK_DEFAULT});
        animator.addUpdateListener(new C10411());
        this.mAlpha = from;
        return animator;
    }

    public int size() {
        return this.mEntries.size();
    }

    public ArrayList<ChartEntry> getEntries() {
        return this.mEntries;
    }

    public ChartEntry getEntry(int index) {
        return (ChartEntry) this.mEntries.get(Preconditions.checkPositionIndex(index, size()));
    }

    public float getValue(int index) {
        return ((ChartEntry) this.mEntries.get(Preconditions.checkPositionIndex(index, size()))).getValue();
    }

    public String getLabel(int index) {
        return ((ChartEntry) this.mEntries.get(Preconditions.checkPositionIndex(index, size()))).getLabel();
    }

    public ChartEntry getMax() {
        return (ChartEntry) Collections.max(this.mEntries);
    }

    public ChartEntry getMin() {
        return (ChartEntry) Collections.min(this.mEntries);
    }

    public float[][] getScreenPoints() {
        int nEntries = size();
        float[][] result = (float[][]) Array.newInstance(Float.TYPE, new int[]{nEntries, 2});
        for (int i = 0; i < nEntries; i++) {
            result[i][0] = ((ChartEntry) this.mEntries.get(i)).getX();
            result[i][1] = ((ChartEntry) this.mEntries.get(i)).getY();
        }
        return result;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public void setAlpha(@FloatRange(from = 0.0d, to = 1.0d) float alpha) {
        if (alpha >= FlexItem.FLEX_SHRINK_DEFAULT) {
            alpha = FlexItem.FLEX_SHRINK_DEFAULT;
        }
        this.mAlpha = alpha;
    }

    public boolean isVisible() {
        return this.mIsVisible;
    }

    public void setVisible(boolean visible) {
        this.mIsVisible = visible;
    }

    private void setValue(int index, float value) {
        ((ChartEntry) this.mEntries.get(Preconditions.checkPositionIndex(index, size()))).setValue(value);
    }

    void setShadow(float radius, float dx, float dy, int color) {
        Iterator it = getEntries().iterator();
        while (it.hasNext()) {
            ((ChartEntry) it.next()).setShadow(radius, dx, dy, color);
        }
    }

    public String toString() {
        return this.mEntries.toString();
    }
}
