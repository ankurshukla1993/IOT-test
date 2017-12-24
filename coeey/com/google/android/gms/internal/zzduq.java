package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzduq extends zzdvr<Void, zzdwu> {
    private String zzmal;

    public zzduq(String str) {
        super(9);
        this.zzmal = str;
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zzi(this.zzmal, this.zzmau);
    }

    public final void zzbpt() {
        zzbc(null);
    }
}
