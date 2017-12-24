package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;

final class zzdtx extends zzdvr<Void, zzdwu> {
    @NonNull
    private final String zzhnj;

    public zzdtx(@NonNull String str) {
        super(7);
        this.zzhnj = zzbq.zzh(str, "code cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zzh(this.zzhnj, this.zzmau);
    }

    public final void zzbpt() {
        zzbc(null);
    }
}
