package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;

final class zzdui extends zzdvr<Void, zzdwu> {
    @NonNull
    private final zzdws zzmag;

    public zzdui(@NonNull AuthCredential authCredential) {
        super(2);
        zzbq.checkNotNull(authCredential, "credential cannot be null");
        this.zzmag = zzdwv.zza(authCredential).zzce(false);
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmag, this.zzmau);
    }

    public final void zzbpt() {
        FirebaseUser zzb = zzdtw.zza(this.zzlyo, this.zzmbf);
        if (this.zzmav.getUid().equalsIgnoreCase(zzb.getUid())) {
            ((zzdwu) this.zzmax).zza(this.zzmbe, zzb);
            zzbc(null);
            return;
        }
        zzap(zzdxe.zzbqj());
    }
}
