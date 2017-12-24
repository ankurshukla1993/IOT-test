package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

final class zzab implements zzcg {
    private /* synthetic */ zzy zzfna;

    private zzab(zzy com_google_android_gms_common_api_internal_zzy) {
        this.zzfna = com_google_android_gms_common_api_internal_zzy;
    }

    public final void zzc(@NonNull ConnectionResult connectionResult) {
        this.zzfna.zzfmy.lock();
        try {
            this.zzfna.zzfmw = connectionResult;
            this.zzfna.zzagz();
        } finally {
            this.zzfna.zzfmy.unlock();
        }
    }

    public final void zzf(int i, boolean z) {
        this.zzfna.zzfmy.lock();
        try {
            if (this.zzfna.zzfmx) {
                this.zzfna.zzfmx = false;
                this.zzfna.zze(i, z);
                return;
            }
            this.zzfna.zzfmx = true;
            this.zzfna.zzfmp.onConnectionSuspended(i);
            this.zzfna.zzfmy.unlock();
        } finally {
            this.zzfna.zzfmy.unlock();
        }
    }

    public final void zzj(@Nullable Bundle bundle) {
        this.zzfna.zzfmy.lock();
        try {
            this.zzfna.zzfmw = ConnectionResult.zzfhy;
            this.zzfna.zzagz();
        } finally {
            this.zzfna.zzfmy.unlock();
        }
    }
}
