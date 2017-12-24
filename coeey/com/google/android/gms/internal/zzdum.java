package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

final class zzdum extends zzdvr<Void, zzdwu> {
    @NonNull
    private final PhoneAuthCredential zzmah;

    public zzdum(@NonNull PhoneAuthCredential phoneAuthCredential) {
        super(2);
        zzbq.checkNotNull(phoneAuthCredential, "credential cannot be null");
        this.zzmah = phoneAuthCredential.zzcd(false);
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmah, this.zzmau);
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
