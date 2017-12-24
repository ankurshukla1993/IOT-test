package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;

final class zzbm implements zzbo<R, T> {
    private /* synthetic */ Response zzfyq;

    zzbm(Response response) {
        this.zzfyq = response;
    }

    public final /* synthetic */ Object zzb(Result result) {
        this.zzfyq.setResult(result);
        return this.zzfyq;
    }
}
