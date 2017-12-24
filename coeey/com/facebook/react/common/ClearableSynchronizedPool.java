package com.facebook.react.common;

import android.support.v4.util.Pools.Pool;

public class ClearableSynchronizedPool<T> implements Pool<T> {
    private final Object[] mPool;
    private int mSize = 0;

    public ClearableSynchronizedPool(int maxSize) {
        this.mPool = new Object[maxSize];
    }

    public synchronized T acquire() {
        T t = null;
        synchronized (this) {
            if (this.mSize != 0) {
                this.mSize--;
                int lastIndex = this.mSize;
                t = this.mPool[lastIndex];
                this.mPool[lastIndex] = null;
            }
        }
        return t;
    }

    public synchronized boolean release(T obj) {
        boolean z;
        if (this.mSize == this.mPool.length) {
            z = false;
        } else {
            this.mPool[this.mSize] = obj;
            this.mSize++;
            z = true;
        }
        return z;
    }

    public synchronized void clear() {
        for (int i = 0; i < this.mSize; i++) {
            this.mPool[i] = null;
        }
        this.mSize = 0;
    }
}
