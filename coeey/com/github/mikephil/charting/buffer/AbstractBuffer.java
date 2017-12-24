package com.github.mikephil.charting.buffer;

import com.google.android.flexbox.FlexItem;
import java.util.List;

public abstract class AbstractBuffer<T> {
    public final float[] buffer;
    protected int index;
    protected int mFrom;
    protected int mTo;
    protected float phaseX;
    protected float phaseY;

    public abstract void feed(List<T> list);

    public AbstractBuffer(int size) {
        this.index = 0;
        this.phaseX = FlexItem.FLEX_SHRINK_DEFAULT;
        this.phaseY = FlexItem.FLEX_SHRINK_DEFAULT;
        this.mFrom = 0;
        this.mTo = 0;
        this.index = 0;
        this.buffer = new float[size];
    }

    public void limitFrom(int from) {
        if (from < 0) {
            from = 0;
        }
        this.mFrom = from;
    }

    public void limitTo(int to) {
        if (to < 0) {
            to = 0;
        }
        this.mTo = to;
    }

    public void reset() {
        this.index = 0;
    }

    public int size() {
        return this.buffer.length;
    }

    public void setPhases(float phaseX, float phaseY) {
        this.phaseX = phaseX;
        this.phaseY = phaseY;
    }
}
