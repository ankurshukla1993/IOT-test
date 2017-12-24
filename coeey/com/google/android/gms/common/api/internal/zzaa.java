package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

final class zzaa implements zzcg {
    private /* synthetic */ zzy zzfna;

    private zzaa(zzy com_google_android_gms_common_api_internal_zzy) {
        this.zzfna = com_google_android_gms_common_api_internal_zzy;
    }

    public final void zzc(@NonNull ConnectionResult connectionResult) {
        this.zzfna.zzfmy.lock();
        try {
            this.zzfna.zzfmv = connectionResult;
            this.zzfna.zzagz();
        } finally {
            this.zzfna.zzfmy.unlock();
        }
    }

    public final void zzf(int i, boolean z) {
        this.zzfna.zzfmy.lock();
        try {
            if (this.zzfna.zzfmx || this.zzfna.zzfmw == null || !this.zzfna.zzfmw.isSuccess()) {
                this.zzfna.zzfmx = false;
                this.zzfna.zze(i, z);
                return;
            }
            this.zzfna.zzfmx = true;
            this.zzfna.zzfmq.onConnectionSuspended(i);
            this.zzfna.zzfmy.unlock();
        } finally {
            this.zzfna.zzfmy.unlock();
        }
    }

    public final void zzj(@Nullable Bundle bundle) {
        this.zzfna.zzfmy.lock();
        try {
            this.zzfna.zzi(bundle);
            this.zzfna.zzfmv = ConnectionResult.zzfhy;
            this.zzfna.zzagz();
        } finally {
            this.zzfna.zzfmy.unlock();
        }
    }
}
