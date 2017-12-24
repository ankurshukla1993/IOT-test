package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzbq;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb<T> implements Iterator<T> {
    protected final DataBuffer<T> zzfta;
    protected int zzftb = -1;

    public zzb(DataBuffer<T> dataBuffer) {
        this.zzfta = (DataBuffer) zzbq.checkNotNull(dataBuffer);
    }

    public boolean hasNext() {
        return this.zzftb < this.zzfta.getCount() + -1;
    }

    public T next() {
        if (hasNext()) {
            DataBuffer dataBuffer = this.zzfta;
            int i = this.zzftb + 1;
            this.zzftb = i;
            return dataBuffer.get(i);
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zzftb);
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
