package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

final class zzdux extends zzdvr<AuthResult, zzdwu> {
    @NonNull
    private String zzmam;

    public zzdux(@NonNull String str) {
        super(2);
        this.zzmam = zzbq.zzh(str, "provider cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zze(this.zzmam, this.zzmav.zzbpo(), this.zzmau);
    }

    public final void zzbpt() {
        FirebaseUser zzb = zzdtw.zza(this.zzlyo, this.zzmbf);
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzb);
        zzbc(new zzdwy(zzb));
    }
}
