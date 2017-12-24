package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

final class zzdut extends zzdvr<AuthResult, zzdwu> {
    @NonNull
    private final String zzdzn;

    public zzdut(@NonNull String str) {
        super(2);
        this.zzdzn = zzbq.zzh(str, "token cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zzb(this.zzdzn, this.zzmau);
    }

    public final void zzbpt() {
        FirebaseUser zzb = zzdtw.zza(this.zzlyo, this.zzmbf);
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzb);
        zzbc(new zzdwy(zzb));
    }
}
