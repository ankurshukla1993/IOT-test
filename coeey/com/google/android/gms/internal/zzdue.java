package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseUser;

final class zzdue extends zzdvr<AuthResult, zzdwu> {
    @NonNull
    private final EmailAuthCredential zzmaf;

    public zzdue(@NonNull EmailAuthCredential emailAuthCredential) {
        super(2);
        this.zzmaf = (EmailAuthCredential) zzbq.checkNotNull(emailAuthCredential, "credential cannot be null");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmaf.getEmail(), this.zzmaf.getPassword(), this.zzmav.zzbpo(), this.zzmau);
    }

    public final void zzbpt() {
        FirebaseUser zzb = zzdtw.zza(this.zzlyo, this.zzmbf);
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzb);
        zzbc(new zzdwy(zzb));
    }
}
