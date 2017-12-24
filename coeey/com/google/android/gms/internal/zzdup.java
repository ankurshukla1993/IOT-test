package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.ActionCodeSettings;

final class zzdup extends zzdvr<Void, zzdwu> {
    @NonNull
    private String zzedt;
    @NonNull
    private ActionCodeSettings zzmak;

    public zzdup(@NonNull String str, @NonNull ActionCodeSettings actionCodeSettings) {
        super(4);
        this.zzedt = zzbq.zzh(str, "email cannot be null or empty");
        this.zzmak = actionCodeSettings;
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzedt, this.zzmak, this.zzmau);
    }

    public final void zzbpt() {
        zzbc(null);
    }
}
