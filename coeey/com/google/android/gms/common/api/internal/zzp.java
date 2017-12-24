package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbq;

final class zzp {
    private final int zzfls;
    private final ConnectionResult zzflt;

    zzp(ConnectionResult connectionResult, int i) {
        zzbq.checkNotNull(connectionResult);
        this.zzflt = connectionResult;
        this.zzfls = i;
    }

    final int zzags() {
        return this.zzfls;
    }

    final ConnectionResult zzagt() {
        return this.zzflt;
    }
}
