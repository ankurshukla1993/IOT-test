package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;

final class zzduy extends zzdvr<Void, zzdwu> {
    @NonNull
    private final String zzedt;

    public zzduy(String str) {
        super(2);
        this.zzedt = zzbq.zzh(str, "email cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmav.zzbpo(), this.zzedt, this.zzmau);
    }

    public final void zzbpt() {
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzdtw.zza(this.zzlyo, this.zzmbf));
        zzbc(null);
    }
}
