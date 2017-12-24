package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.internal.zzbq;

public final class zzdvn extends zzdtq implements HasOptions {
    private final String zzlwt;

    private zzdvn(@NonNull String str) {
        this.zzlwt = zzbq.zzh(str, "A valid API key must be provided");
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new zzdvo(this.zzlwt).zzbpy();
    }

    public final String getApiKey() {
        return this.zzlwt;
    }

    public final /* synthetic */ zzdtq zzbpr() {
        return (zzdvn) clone();
    }
}
