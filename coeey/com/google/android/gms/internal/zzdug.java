package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

final class zzdug extends zzdvr<AuthResult, zzdwu> implements zzdvt {
    @NonNull
    private final PhoneAuthCredential zzmah;

    public zzdug(@NonNull PhoneAuthCredential phoneAuthCredential) {
        super(2);
        this.zzmah = (PhoneAuthCredential) zzbq.checkNotNull(phoneAuthCredential, "credential cannot be null");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmav.zzbpo(), this.zzmah, this.zzmau);
    }

    public final void zza(@NonNull Status status, @NonNull PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        zzbq.zza(this.zzmat == 2, "Unexpected response type " + this.zzmat);
        this.zzmbk = false;
        this.zzmbj = phoneAuthCredential;
        if (this.zzmay != null) {
            this.zzmay.onError(status);
        }
        zzap(status);
    }

    public final void zzbpt() {
        FirebaseUser zzb = zzdtw.zza(this.zzlyo, this.zzmbf);
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzb);
        zzbc(new zzdwy(zzb));
    }
}
