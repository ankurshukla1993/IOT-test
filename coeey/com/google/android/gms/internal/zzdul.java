package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

final class zzdul extends zzdvr<AuthResult, zzdwu> {
    @NonNull
    private final String zzebz;
    @NonNull
    private final String zzedt;

    public zzdul(@NonNull String str, @NonNull String str2) {
        super(2);
        this.zzedt = zzbq.zzh(str, "email cannot be null or empty");
        this.zzebz = zzbq.zzh(str2, "password cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zzd(this.zzedt, this.zzebz, this.zzmau);
    }

    public final void zzbpt() {
        FirebaseUser zzb = zzdtw.zza(this.zzlyo, this.zzmbf);
        if (this.zzmav.getUid().equalsIgnoreCase(zzb.getUid())) {
            ((zzdwu) this.zzmax).zza(this.zzmbe, zzb);
            zzbc(new zzdwy(zzb));
            return;
        }
        zzap(zzdxe.zzbqj());
    }
}
