package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import com.google.android.gms.common.api.zze;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

final class zzdn implements DeathRecipient, zzdo {
    private final WeakReference<zzs<?>> zzfsr;
    private final WeakReference<zze> zzfss;
    private final WeakReference<IBinder> zzfst;

    private zzdn(zzs<?> com_google_android_gms_common_api_internal_zzs_, zze com_google_android_gms_common_api_zze, IBinder iBinder) {
        this.zzfss = new WeakReference(com_google_android_gms_common_api_zze);
        this.zzfsr = new WeakReference(com_google_android_gms_common_api_internal_zzs_);
        this.zzfst = new WeakReference(iBinder);
    }

    private final void zzajj() {
        zzs com_google_android_gms_common_api_internal_zzs = (zzs) this.zzfsr.get();
        zze com_google_android_gms_common_api_zze = (zze) this.zzfss.get();
        if (!(com_google_android_gms_common_api_zze == null || com_google_android_gms_common_api_internal_zzs == null)) {
            com_google_android_gms_common_api_zze.remove(com_google_android_gms_common_api_internal_zzs.zzagi().intValue());
        }
        IBinder iBinder = (IBinder) this.zzfst.get();
        if (iBinder != null) {
            try {
                iBinder.unlinkToDeath(this, 0);
            } catch (NoSuchElementException e) {
            }
        }
    }

    public final void binderDied() {
        zzajj();
    }

    public final void zzc(zzs<?> com_google_android_gms_common_api_internal_zzs_) {
        zzajj();
    }
}
