package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

final class zzduw extends zzdvr<AuthResult, zzdwu> {
    public zzduw() {
        super(2);
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zzd(this.zzmav.zzbpo(), this.zzmau);
    }

    public final void zzbpt() {
        FirebaseUser zzb = zzdtw.zza(this.zzlyo, this.zzmbf);
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzb);
        zzbc(new zzdwy(zzb));
    }
}
