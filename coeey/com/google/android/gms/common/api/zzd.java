package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.zzdb;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbq;

public final class zzd {
    private Looper zzakm;
    private zzdb zzfjn;

    public final zzd zza(Looper looper) {
        zzbq.checkNotNull(looper, "Looper must not be null.");
        this.zzakm = looper;
        return this;
    }

    public final zzd zza(zzdb com_google_android_gms_common_api_internal_zzdb) {
        zzbq.checkNotNull(com_google_android_gms_common_api_internal_zzdb, "StatusExceptionMapper must not be null.");
        this.zzfjn = com_google_android_gms_common_api_internal_zzdb;
        return this;
    }

    public final GoogleApi$zza zzagd() {
        if (this.zzfjn == null) {
            this.zzfjn = new zzg();
        }
        if (this.zzakm == null) {
            this.zzakm = Looper.getMainLooper();
        }
        return new GoogleApi$zza(this.zzfjn, this.zzakm);
    }
}
