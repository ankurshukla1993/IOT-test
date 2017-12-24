package com.facebook.react.common;

public class LongArray {
    private static final double INNER_ARRAY_GROWTH_FACTOR = 1.8d;
    private long[] mArray;
    private int mLength = 0;

    public static LongArray createWithInitialCapacity(int initialCapacity) {
        return new LongArray(initialCapacity);
    }

    private LongArray(int initialCapacity) {
        this.mArray = new long[initialCapacity];
    }

    public void add(long value) {
        growArrayIfNeeded();
        long[] jArr = this.mArray;
        int i = this.mLength;
        this.mLength = i + 1;
        jArr[i] = value;
    }

    public long get(int index) {
        if (index < this.mLength) {
            return this.mArray[index];
        }
        throw new IndexOutOfBoundsException("" + index + " >= " + this.mLength);
    }

    public void set(int index, long value) {
        if (index >= this.mLength) {
            throw new IndexOutOfBoundsException("" + index + " >= " + this.mLength);
        }
        this.mArray[index] = value;
    }

    public int size() {
        return this.mLength;
    }

    public boolean isEmpty() {
        return this.mLength == 0;
    }

    public void dropTail(int n) {
        if (n > this.mLength) {
            throw new IndexOutOfBoundsException("Trying to drop " + n + " items from array of length " + this.mLength);
        }
        this.mLength -= n;
    }

    private void growArrayIfNeeded() {
        if (this.mLength == this.mArray.length) {
            long[] newArray = new long[Math.max(this.mLength + 1, (int) (((double) this.mLength) * INNER_ARRAY_GROWTH_FACTOR))];
            System.arraycopy(this.mArray, 0, newArray, 0, this.mLength);
            this.mArray = newArray;
        }
    }
}
