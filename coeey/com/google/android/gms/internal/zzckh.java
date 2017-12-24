package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;

final class zzckh {
    private long mStartTime;
    private final zzd zzasd;

    public zzckh(zzd com_google_android_gms_common_util_zzd) {
        zzbq.checkNotNull(com_google_android_gms_common_util_zzd);
        this.zzasd = com_google_android_gms_common_util_zzd;
    }

    public final void clear() {
        this.mStartTime = 0;
    }

    public final void start() {
        this.mStartTime = this.zzasd.elapsedRealtime();
    }

    public final boolean zzt(long j) {
        return this.mStartTime == 0 || this.zzasd.elapsedRealtime() - this.mStartTime >= 3600000;
    }
}
