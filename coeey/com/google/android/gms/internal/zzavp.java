package com.google.android.gms.internal;

import com.google.android.gms.auth.api.proxy.ProxyApi.ProxyResult;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.Status;

final class zzavp implements ProxyResult {
    private Status mStatus;
    private ProxyResponse zzedr;

    public zzavp(ProxyResponse proxyResponse) {
        this.zzedr = proxyResponse;
        this.mStatus = Status.zzfko;
    }

    public zzavp(Status status) {
        this.mStatus = status;
    }

    public final ProxyResponse getResponse() {
        return this.zzedr;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
