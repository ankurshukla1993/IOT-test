package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.ProviderQueryResult;

final class zzduc extends zzdvr<ProviderQueryResult, zzdwu> {
    @NonNull
    private final String zzedt;

    public zzduc(@NonNull String str) {
        super(3);
        this.zzedt = zzbq.zzh(str, "email cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zzc(this.zzedt, this.zzmau);
    }

    public final void zzbpt() {
        zzbc(new zzdxd(this.zzmbg));
    }
}
