package com.google.android.gms.internal;

import android.database.ContentObserver;
import android.os.Handler;

final class zzcsu extends ContentObserver {
    private /* synthetic */ zzcss zzjts;

    zzcsu(zzcss com_google_android_gms_internal_zzcss, Handler handler) {
        this.zzjts = com_google_android_gms_internal_zzcss;
        super(null);
    }

    public final void onChange(boolean z) {
        synchronized (this.zzjts.zzjtp) {
            this.zzjts.zzjtq = null;
        }
    }
}
