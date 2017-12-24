package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;

public final class zzbz<O extends ApiOptions> extends zzan {
    private final GoogleApi<O> zzfrc;

    public zzbz(GoogleApi<O> googleApi) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.zzfrc = googleApi;
    }

    public final Context getContext() {
        return this.zzfrc.getApplicationContext();
    }

    public final Looper getLooper() {
        return this.zzfrc.getLooper();
    }

    public final void zza(zzdi com_google_android_gms_common_api_internal_zzdi) {
    }

    public final void zzb(zzdi com_google_android_gms_common_api_internal_zzdi) {
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T t) {
        return this.zzfrc.zza(t);
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T t) {
        return this.zzfrc.zzb(t);
    }
}
