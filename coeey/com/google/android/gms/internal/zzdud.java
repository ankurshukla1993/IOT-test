package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.GetTokenResult;

final class zzdud extends zzdvr<GetTokenResult, zzdwu> {
    @NonNull
    private final String zzmae;

    public zzdud(@NonNull String str) {
        super(1);
        this.zzmae = zzbq.zzh(str, "refresh token cannot be null");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmae, this.zzmau);
    }

    public final void zzbpt() {
        this.zzmbe.zzoj(this.zzmae);
        ((zzdwu) this.zzmax).zza(this.zzmbe, this.zzmav);
        zzbc(new GetTokenResult(this.zzmbe.getAccessToken()));
    }
}
