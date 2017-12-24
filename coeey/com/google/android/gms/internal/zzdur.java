package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

final class zzdur extends zzdvr<AuthResult, zzdwu> {
    public zzdur() {
        super(2);
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmau);
    }

    public final void zzbpt() {
        FirebaseUser zzb = zzdtw.zza(this.zzlyo, this.zzmbf, true);
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzb);
        zzbc(new zzdwy(zzb));
    }
}
