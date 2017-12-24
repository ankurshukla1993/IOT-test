package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.ActionCodeResult;

final class zzdty extends zzdvr<ActionCodeResult, zzdwu> {
    @NonNull
    private final String zzhnj;

    public zzdty(@NonNull String str) {
        super(4);
        this.zzhnj = zzbq.zzh(str, "code cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zzg(this.zzhnj, this.zzmau);
    }

    public final void zzbpt() {
        zzbc(new zzdww(this.zzmbh));
    }
}
