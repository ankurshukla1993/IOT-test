package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzh<O extends ApiOptions> {
    private final Api<O> zzffv;
    private final O zzfjk;
    private final boolean zzflb = true;
    private final int zzflc;

    private zzh(Api<O> api) {
        this.zzffv = api;
        this.zzfjk = null;
        this.zzflc = System.identityHashCode(this);
    }

    private zzh(Api<O> api, O o) {
        this.zzffv = api;
        this.zzfjk = o;
        this.zzflc = Arrays.hashCode(new Object[]{this.zzffv, this.zzfjk});
    }

    public static <O extends ApiOptions> zzh<O> zza(Api<O> api, O o) {
        return new zzh(api, o);
    }

    public static <O extends ApiOptions> zzh<O> zzb(Api<O> api) {
        return new zzh(api);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh com_google_android_gms_common_api_internal_zzh = (zzh) obj;
        return !this.zzflb && !com_google_android_gms_common_api_internal_zzh.zzflb && zzbg.equal(this.zzffv, com_google_android_gms_common_api_internal_zzh.zzffv) && zzbg.equal(this.zzfjk, com_google_android_gms_common_api_internal_zzh.zzfjk);
    }

    public final int hashCode() {
        return this.zzflc;
    }

    public final String zzagl() {
        return this.zzffv.getName();
    }
}
