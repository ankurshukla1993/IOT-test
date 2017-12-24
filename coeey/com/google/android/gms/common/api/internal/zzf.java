package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzf extends zzb<Boolean> {
    private zzcn<?> zzfla;

    public zzf(zzcn<?> com_google_android_gms_common_api_internal_zzcn_, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zzfla = com_google_android_gms_common_api_internal_zzcn_;
    }

    public final /* bridge */ /* synthetic */ void zza(@NonNull zzah com_google_android_gms_common_api_internal_zzah, boolean z) {
    }

    public final void zzb(zzbr<?> com_google_android_gms_common_api_internal_zzbr_) throws RemoteException {
        zzcu com_google_android_gms_common_api_internal_zzcu = (zzcu) com_google_android_gms_common_api_internal_zzbr_.zzaim().remove(this.zzfla);
        if (com_google_android_gms_common_api_internal_zzcu != null) {
            com_google_android_gms_common_api_internal_zzcu.zzfkx.zzc(com_google_android_gms_common_api_internal_zzbr_.zzahd(), this.zzeay);
            com_google_android_gms_common_api_internal_zzcu.zzfkw.zzajd();
            return;
        }
        this.zzeay.trySetResult(Boolean.valueOf(false));
    }

    public final /* bridge */ /* synthetic */ void zzs(@NonNull Status status) {
        super.zzs(status);
    }
}
