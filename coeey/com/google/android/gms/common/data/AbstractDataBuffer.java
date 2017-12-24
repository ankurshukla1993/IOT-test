package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
    protected final DataHolder zzfnz;

    protected AbstractDataBuffer(DataHolder dataHolder) {
        this.zzfnz = dataHolder;
    }

    @Deprecated
    public final void close() {
        release();
    }

    public abstract T get(int i);

    public int getCount() {
        return this.zzfnz == null ? 0 : this.zzfnz.zzftm;
    }

    @Deprecated
    public boolean isClosed() {
        return this.zzfnz == null || this.zzfnz.isClosed();
    }

    public Iterator<T> iterator() {
        return new zzb(this);
    }

    public void release() {
        if (this.zzfnz != null) {
            this.zzfnz.close();
        }
    }

    public Iterator<T> singleRefIterator() {
        return new zzh(this);
    }

    public final Bundle zzafx() {
        return this.zzfnz.zzafx();
    }
}
