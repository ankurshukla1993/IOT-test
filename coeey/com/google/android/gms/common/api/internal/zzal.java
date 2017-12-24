package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.data.DataHolder;

public abstract class zzal<L> implements zzco<L> {
    private final DataHolder zzfnz;

    protected zzal(DataHolder dataHolder) {
        this.zzfnz = dataHolder;
    }

    protected abstract void zza(L l, DataHolder dataHolder);

    public final void zzahn() {
        if (this.zzfnz != null) {
            this.zzfnz.close();
        }
    }

    public final void zzt(L l) {
        zza(l, this.zzfnz);
    }
}
