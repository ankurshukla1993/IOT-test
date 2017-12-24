package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public final class zzh<T> extends zzb<T> {
    private T zzftw;

    public zzh(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public final T next() {
        if (hasNext()) {
            this.zzftb++;
            if (this.zzftb == 0) {
                this.zzftw = this.zzfta.get(0);
                if (!(this.zzftw instanceof zzc)) {
                    String valueOf = String.valueOf(this.zzftw.getClass());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 44).append("DataBuffer reference of type ").append(valueOf).append(" is not movable").toString());
                }
            }
            ((zzc) this.zzftw).zzbx(this.zzftb);
            return this.zzftw;
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zzftb);
    }
}
