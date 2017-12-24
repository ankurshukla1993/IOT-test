package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

final class zzduf extends zzdvr<AuthResult, zzdwu> {
    @NonNull
    private final zzdws zzmag;

    public zzduf(@NonNull AuthCredential authCredential) {
        super(2);
        zzbq.checkNotNull(authCredential, "credential cannot be null");
        this.zzmag = zzdwv.zza(authCredential);
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmav.zzbpo(), this.zzmag, this.zzmau);
    }

    public final void zzbpt() {
        FirebaseUser zzb = zzdtw.zza(this.zzlyo, this.zzmbf);
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzb);
        zzbc(new zzdwy(zzb));
    }
}
