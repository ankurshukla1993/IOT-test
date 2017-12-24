package com.google.android.gms.common.api.internal;

final class zzz implements Runnable {
    private /* synthetic */ zzy zzfna;

    zzz(zzy com_google_android_gms_common_api_internal_zzy) {
        this.zzfna = com_google_android_gms_common_api_internal_zzy;
    }

    public final void run() {
        this.zzfna.zzfmy.lock();
        try {
            this.zzfna.zzagz();
        } finally {
            this.zzfna.zzfmy.unlock();
        }
    }
}
