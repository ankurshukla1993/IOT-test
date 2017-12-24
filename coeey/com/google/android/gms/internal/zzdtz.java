package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;

final class zzdtz extends zzdvr<Void, zzdwu> {
    @NonNull
    private final String zzhnj;
    @NonNull
    private final String zzmad;

    public zzdtz(@NonNull String str, @NonNull String str2) {
        super(4);
        this.zzhnj = zzbq.zzh(str, "code cannot be null or empty");
        this.zzmad = zzbq.zzh(str2, "new password cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zzf(this.zzhnj, this.zzmad, this.zzmau);
    }

    public final void zzbpt() {
        zzbc(null);
    }
}
