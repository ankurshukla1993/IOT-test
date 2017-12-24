package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.ActionCodeSettings;

final class zzduo extends zzdvr<Void, zzdwu> {
    @NonNull
    private String zzdzn;
    @Nullable
    private ActionCodeSettings zzmak;

    public zzduo(@NonNull String str, @Nullable ActionCodeSettings actionCodeSettings) {
        super(6);
        this.zzdzn = zzbq.zzh(str, "token cannot be null or empty");
        this.zzmak = actionCodeSettings;
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zzb(this.zzdzn, this.zzmak, this.zzmau);
    }

    public final void zzbpt() {
        zzbc(null);
    }
}
