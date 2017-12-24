package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.PhoneAuthCredential;

final class zzdva extends zzdvr<Void, zzdwu> {
    @NonNull
    private final PhoneAuthCredential zzmah;

    public zzdva(PhoneAuthCredential phoneAuthCredential) {
        super(2);
        this.zzmah = (PhoneAuthCredential) zzbq.checkNotNull(phoneAuthCredential);
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmav.zzbpo(), this.zzmah, this.zzmau);
    }

    public final void zzbpt() {
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzdtw.zza(this.zzlyo, this.zzmbf));
        zzbc(null);
    }
}
