package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

final class zzduv extends zzdvr<AuthResult, zzdwu> {
    private PhoneAuthCredential zzmah;

    public zzduv(PhoneAuthCredential phoneAuthCredential) {
        super(2);
        this.zzmah = (PhoneAuthCredential) zzbq.checkNotNull(phoneAuthCredential);
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmah, this.zzmau);
    }

    public final void zzbpt() {
        FirebaseUser zzb = zzdtw.zza(this.zzlyo, this.zzmbf);
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzb);
        zzbc(new zzdwy(zzb));
    }
}
