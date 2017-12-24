package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;

final class zzduz extends zzdvr<Void, zzdwu> {
    @NonNull
    private final String zzebz;

    public zzduz(@NonNull String str) {
        super(2);
        this.zzebz = zzbq.zzh(str, "password cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zzb(this.zzmav.zzbpo(), this.zzebz, this.zzmau);
    }

    public final void zzbpt() {
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzdtw.zza(this.zzlyo, this.zzmbf));
        zzbc(null);
    }
}
