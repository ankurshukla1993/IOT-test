package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public class zzam implements Releasable, Result {
    private Status mStatus;
    protected final DataHolder zzfnz;

    protected zzam(DataHolder dataHolder, Status status) {
        this.mStatus = status;
        this.zzfnz = dataHolder;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public void release() {
        if (this.zzfnz != null) {
            this.zzfnz.close();
        }
    }
}
