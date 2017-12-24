package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;

public final class zzcl<L> {
    private final zzcm zzfrp;
    private volatile L zzfrq;
    private final zzcn<L> zzfrr;

    zzcl(@NonNull Looper looper, @NonNull L l, @NonNull String str) {
        this.zzfrp = new zzcm(this, looper);
        this.zzfrq = zzbq.checkNotNull(l, "Listener must not be null");
        this.zzfrr = new zzcn(l, zzbq.zzgh(str));
    }

    public final void clear() {
        this.zzfrq = null;
    }

    public final void zza(zzco<? super L> com_google_android_gms_common_api_internal_zzco__super_L) {
        zzbq.checkNotNull(com_google_android_gms_common_api_internal_zzco__super_L, "Notifier must not be null");
        this.zzfrp.sendMessage(this.zzfrp.obtainMessage(1, com_google_android_gms_common_api_internal_zzco__super_L));
    }

    public final boolean zzadx() {
        return this.zzfrq != null;
    }

    @NonNull
    public final zzcn<L> zzajc() {
        return this.zzfrr;
    }

    final void zzb(zzco<? super L> com_google_android_gms_common_api_internal_zzco__super_L) {
        Object obj = this.zzfrq;
        if (obj == null) {
            com_google_android_gms_common_api_internal_zzco__super_L.zzahn();
            return;
        }
        try {
            com_google_android_gms_common_api_internal_zzco__super_L.zzt(obj);
        } catch (RuntimeException e) {
            com_google_android_gms_common_api_internal_zzco__super_L.zzahn();
            throw e;
        }
    }
}
