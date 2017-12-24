package com.github.mikephil.charting.data;

public class Entry {
    private Object mData;
    private float mVal;
    private int mXIndex;

    public Entry(float val, int xIndex) {
        this.mVal = 0.0f;
        this.mXIndex = 0;
        this.mData = null;
        this.mVal = val;
        this.mXIndex = xIndex;
    }

    public Entry(float val, int xIndex, Object data) {
        this(val, xIndex);
        this.mData = data;
    }

    public int getXIndex() {
        return this.mXIndex;
    }

    public void setXIndex(int x) {
        this.mXIndex = x;
    }

    public float getVal() {
        return this.mVal;
    }

    public void setVal(float val) {
        this.mVal = val;
    }

    public Object getData() {
        return this.mData;
    }

    public void setData(Object data) {
        this.mData = data;
    }

    public Entry copy() {
        return new Entry(this.mVal, this.mXIndex, this.mData);
    }

    public boolean equalTo(Entry e) {
        if (e != null && e.mData == this.mData && e.mXIndex == this.mXIndex && Math.abs(e.mVal - this.mVal) <= 1.0E-5f) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Entry, xIndex: " + this.mXIndex + " val (sum): " + getVal();
    }
}
