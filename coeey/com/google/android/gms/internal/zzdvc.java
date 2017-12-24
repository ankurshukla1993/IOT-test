package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;

final class zzdvc extends zzdvr<String, zzdwu> {
    @NonNull
    private final String zzhnj;

    public zzdvc(@NonNull String str) {
        super(4);
        this.zzhnj = zzbq.zzh(str, "code cannot be null or empty");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zzg(this.zzhnj, this.zzmau);
    }

    public final void zzbpt() {
        if (new zzdww(this.zzmbh).getOperation() != 0) {
            zzap(new Status(17499));
        } else {
            zzbc(this.zzmbh.getEmail());
        }
    }
}
